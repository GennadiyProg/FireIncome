package com.example.fireincome.service.impl;

import com.example.fireincome.model.Role;
import com.example.fireincome.model.User;
import com.example.fireincome.repos.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user;
        user = userRepo.findByUsername(username);
        if (user.isEmpty()){
            throw new UsernameNotFoundException(String.format("User %s doesn't exists", username));
        }
        return new org.springframework.security.core.userdetails.User(username, user.get().getPassword(), getRole(user.get().getRole()));
    }

    private Collection<? extends GrantedAuthority> getRole(Role role){
        Collection<GrantedAuthority> roles = new ArrayList<>();
        roles.add(new SimpleGrantedAuthority("ROLE_" + role.name()));
        return roles;
    }
}
