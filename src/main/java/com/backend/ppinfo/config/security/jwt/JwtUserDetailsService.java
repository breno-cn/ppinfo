package com.backend.ppinfo.config.security.jwt;

import com.backend.ppinfo.relational.repository.BoardUserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class JwtUserDetailsService implements UserDetailsService {

    private final BoardUserRepository boardUserRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var user = boardUserRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(username));

        return new User(
                user.getUsername(),
                user.getPassword(),
                true,
                true,
                true,
                user.isAccountNonLocked(),
                List.of(() -> "USER"));
    }

}