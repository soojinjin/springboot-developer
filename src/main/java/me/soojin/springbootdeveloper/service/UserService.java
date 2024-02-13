package me.soojin.springbootdeveloper.service;

import lombok.RequiredArgsConstructor;
import me.soojin.springbootdeveloper.domain.User;
import me.soojin.springbootdeveloper.dto.AddUserRequest;
import me.soojin.springbootdeveloper.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;


    public Long save(AddUserRequest dto) {
      BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    return userRepository.save(User.builder()
            .email(dto.getEmail())
            .password(encoder.encode(dto.getPassword()))
            .build()).getId();
    }
    //메서드 추가
public User findById(Long userId) {
    return userRepository.findById(userId)
            .orElseThrow(() -> new IllegalArgumentException("unexpected user"));
    }
    public User findByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("Unexpected user"));
    }
}