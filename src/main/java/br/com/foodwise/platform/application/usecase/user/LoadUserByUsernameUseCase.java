package br.com.foodwise.platform.application.usecase.user;

import br.com.foodwise.platform.gateway.UserGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoadUserByUsernameUseCase implements UserDetailsService  {

    private final UserGateway userGateway;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userGateway.findByEmail(email);
    }
}
