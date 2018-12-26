package com.msnishan.auth.user.service;

import com.msnishan.auth.user.domain.Grant;
import com.msnishan.auth.user.domain.User;
import com.msnishan.auth.user.domain.UserGrant;
import com.msnishan.gen.type.request.RequestContext;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {


    private UserService userService;

    public UserDetailsServiceImpl(UserService userService) {
        this.userService = userService;
    }

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        RequestContext context = new RequestContext(null, null, "1", "1");
        return userService.findUserById(context, username);
    }
}
