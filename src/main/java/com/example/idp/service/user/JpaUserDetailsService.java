package com.example.idp.service.user;

import lombok.RequiredArgsConstructor;
import com.example.idp.adapter.UserDetailsAdapter;
import com.example.idp.entity.User;
import com.example.idp.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JpaUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email);

        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        if (!user.getVerified()) {
            throw new UsernameNotFoundException("User's email is not verified");
        }
        return new UserDetailsAdapter(user);
    }
}


