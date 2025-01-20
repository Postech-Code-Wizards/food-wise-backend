package br.com.foodwise.platform.service;

import br.com.foodwise.platform.model.entities.User;
import br.com.foodwise.platform.model.entities.enums.UserType;
import br.com.foodwise.platform.model.repositories.UserRepository;
import br.com.foodwise.platform.rest.controller.exception.BusinessException;
import br.com.foodwise.platform.rest.controller.exception.ResourceNotFoundException;
import br.com.foodwise.platform.rest.converter.common.UserRequestToEntityConverter;
import br.com.foodwise.platform.rest.dtos.request.register.UserRequest;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;
    private final UserRequestToEntityConverter userRequestToEntityConverter;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository.findByEmail(email);
    }

    public User createUser(String email, String password, UserType role) {
        if (userRepository.existsByEmail(email)) {
            throw new BusinessException("EMAIL_ALREADY_EXISTS", HttpStatus.CONFLICT, "");
        }

        var newUser = User
                .builder()
                .email(email)
                .createdAt(ZonedDateTime.now())
                .updatedAt(ZonedDateTime.now())
                .isActive(true)
                .userType(role)
                .password(getEncryptedPassword(password))
                .build();

        this.userRepository.save(newUser);
        return newUser;
    }

    @Transactional
    public void updateUserEmail(UserRequest userRequest, Long id, UserType userType) {
        var existingUser = userRepository.findByIdAndUserTypeAndDeletedAtIsNull(id, userType)
                .orElseThrow(() -> new ResourceNotFoundException("USER_DOES_NOT_EXIST", ""));

        var user = convertUserRequestToUser(userRequest);

        if (ObjectUtils.isNotEmpty(user.getEmail()) && !userRequest.getPassword().equals(existingUser.getPassword())) {
            existingUser.setEmail(user.getEmail());
        }

        existingUser.setUpdatedAt(ZonedDateTime.now());
        existingUser.setActive(true);

        userRepository.save(existingUser);
    }

    private User convertUserRequestToUser(UserRequest userRequest) {
        return userRequestToEntityConverter.convert(userRequest);
    }

    private String getEncryptedPassword(String password) {
        return new BCryptPasswordEncoder().encode(password);
    }

    public void delete (long id, UserType userType) {

        var user = userRepository.findByIdAndUserTypeAndDeletedAtIsNull(id, userType);
        if(user.isEmpty()){
            throw new BusinessException("USER_DOES_NOT_EXIST", HttpStatus.NOT_FOUND, "");
        }

        User userFound = user.get();
        userFound.setActive(false);
        userFound.setDeletedAt(ZonedDateTime.now());

    }

}