package com.e.commerce.application.domain.services.custom;

import com.e.commerce.application.domain.entities.User;
import com.e.commerce.application.domain.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findUserByEmail(email).orElseThrow(
                () -> {
                    log.error("Not found user: " + email);
                    return new UsernameNotFoundException("Not found this user: " + email);
                }
        );
        log.info("load user success: {}", user);
        return user;
    }
}
