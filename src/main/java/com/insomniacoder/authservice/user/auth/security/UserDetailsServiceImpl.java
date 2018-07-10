package com.insomniacoder.authservice.user.auth.security;

import com.insomniacoder.authservice.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private BCryptPasswordEncoder encoder;

    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        com.insomniacoder.authservice.user.entity.User appUser = userRepository.findByEmail(username);

        if (appUser != null) {

            List<GrantedAuthority> grantedAuthorities = AuthorityUtils
                    //Spring security must have ROLE_
                    .commaSeparatedStringToAuthorityList("ROLE_" + appUser.getRole());
            return new User(
                    appUser.getEmail(),
                    encoder.encode(appUser.getPassword()), grantedAuthorities);
        }
        throw new UsernameNotFoundException("Username: " + username + " not found");
    }

}