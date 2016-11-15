package com.psa.Service;

import com.psa.domain.users.UserInfo;
import org.springframework.security.core.userdetails.User;
import com.psa.domain.users.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class AuthenticationService implements UserDetailsService {
    @Autowired
    private UserDao userDAO;
    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {
        UserInfo userInfo = userDAO.getUserInfo(username);
        GrantedAuthority authority = new SimpleGrantedAuthority(userInfo.getRole());
        UserDetails userDetails = new User(userInfo.getUsername(),
                userInfo.getPassword(), Arrays.asList(authority));
        return userDetails;
    }
}
