package com.cognizant.authentication.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.cognizant.authentication.model.User;
import com.cognizant.authentication.repository.UserRepository;

@Service
public class AppUserDetailsService implements UserDetailsService {

                @Autowired
                UserRepository userRepository;

                @Override
                public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
                                // TODO Auto-generated method stub
                                User user = userRepository.findByUsername(username);
                                
                                if(user==null){
                                                throw new UsernameNotFoundException("Exception user name not found");
                                }
                                else
                                {
                                                AppUser appUser=new AppUser(user);
                                                return appUser;
                                }
                                
                }

}

