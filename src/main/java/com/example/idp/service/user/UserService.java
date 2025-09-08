package com.example.idp.service.user;
import com.example.idp.requests.UserVerificationRequest;
import lombok.RequiredArgsConstructor;
import com.example.idp.entity.User;
import com.example.idp.repository.UserRepository;
import com.example.idp.requests.UserRegistrationRequest;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Random;


@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public User createUser(UserRegistrationRequest request) {

        Random random = new Random();

        User user = User.builder()
                .firstName(request.firstName())
                .lastName(request.lastName())
                .password(passwordEncoder.encode(request.password()))
                .email(request.email())
                .role("user")
                .verificationCode(100000 + random.nextInt(900000))
                .verified(false)
                .build();
        return userRepository.save(user);
    }

    public User getUserByEmail(String email) {

        return userRepository.findByEmail(email);
    }

    public User verifyUser(UserVerificationRequest request) {
        User user = userRepository.findByEmail(request.email());
        if(user == null){
           throw new UsernameNotFoundException("User not found");
        }
        System.out.println(user.getVerificationCode());
        System.out.println(request.code());
        user.setVerified(Objects.equals(user.getVerificationCode(), request.code()));

        return userRepository.save(user);
    }
}
