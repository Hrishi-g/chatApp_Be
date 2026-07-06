package com.app.chatApp.config;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class CustomUserDetailsService implements UserDetailsService {

    @org.springframework.beans.factory.annotation.Autowired
    private com.app.chatApp.repository.RegisteredUsersRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String mblNo) throws UsernameNotFoundException {
        return userRepo.findUserByMblNo(mblNo)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with mobile number: " + mblNo));
    }

}
