package br.com.foodwise.platform.application.usecase.user;

import br.com.foodwise.platform.domain.entities.User;
import br.com.foodwise.platform.infrastructure.rest.controller.exception.BusinessException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ValidateUserIsAuthenticatedUseCase {

    public void execute(long id) {
        var authentication = SecurityContextHolder.getContext().getAuthentication();
        var userAuthenticated = (User) authentication.getPrincipal();
        if (id != userAuthenticated.getId()) {
            throw new BusinessException("DELETION_OF_UNAUTHENTICATED", HttpStatus.CONFLICT, "");
        }
    }

}
