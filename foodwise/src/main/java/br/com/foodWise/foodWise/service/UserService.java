package br.com.foodWise.foodWise.service;

import br.com.foodWise.foodWise.model.entities.User;
import br.com.foodWise.foodWise.model.repositories.UserRepository;
import br.com.foodWise.foodWise.service.exceptions.user.UserNotFound;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private UserRepository userRepository;


    public Optional<User> getUser(Long userId) {

        logger.info("Buscando usuario! ");

        return Optional.ofNullable(userRepository.findById(userId).orElseThrow(UserNotFound::new));
    }
}
