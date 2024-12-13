package br.com.foodwise.foodwise.service;

import br.com.foodwise.foodwise.model.entities.User;
import br.com.foodwise.foodwise.model.repositories.UserRepository;
import br.com.foodwise.foodwise.service.exceptions.ResourceNotFound;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class UserService {

    @Autowired
    private UserRepository userRepository;


    public Optional<User> getUser(Long userId) {

        log.debug("Buscando usuario!");

        //Optional<User> user = userRepository.findById(userId);

        if (userId == 2) {
            throw new ResourceNotFound("Usuário");
        }

        return null;
    }
}
