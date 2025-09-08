package com.example.idp.service.user;
import lombok.RequiredArgsConstructor;
import com.example.idp.entity.User;
import com.example.idp.repository.UserRepository;
import com.example.idp.requests.UserRegistrationRequest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public void createUser(UserRegistrationRequest request) {
        User user = User.builder()
                .firstName(request.firstName())
                .lastName(request.lastName())
                .password(passwordEncoder.encode(request.password()))
                .email(request.email())
                .role("user")
                .build();
        userRepository.save(user);
    }
}
