package com.kemtech.springsec6demo.security;

import com.kemtech.springsec6demo.entity.Role;
import com.kemtech.springsec6demo.entity.User;
import com.kemtech.springsec6demo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if (userRepository.findByUsername(username).isPresent()) {
            return userRepository.findByUsername(username).get();

//            return org.springframework.security.core.userdetails.User.builder()
//                            .username(username)
//                            .password(dbUser.getPassword())
//                            .roles(dbUser.getRoles().stream().map(Role::getName).toArray(String[]::new))
//                            .build();

        }
        throw new UsernameNotFoundException("User " + username + " Not Found");
    }
}
