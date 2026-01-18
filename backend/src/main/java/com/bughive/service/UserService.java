package com.bughive.service;

import com.bughive.dto.UserRequest;
import com.bughive.dto.UserResponse;
import com.bughive.entity.User;
import com.bughive.mapper.UserMapper;
import com.bughive.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    private final PasswordEncoder passwordEncoder;

    public List<UserResponse> findAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(userMapper::toDto).toList();
    }

    public Optional<UserResponse> findByUserId(Long userId) {
        return userRepository.findById(userId)
                .map(userMapper::toDto);
    }

    public UserResponse createUser(UserRequest userRequest) {
        if (userRequest.getEmail() == null) {
            throw new IllegalArgumentException("Email is required");
        }
        if (userRequest.getPassword() == null) {
            throw new IllegalArgumentException("Password is required");
        }
        if (userRequest.getPassword().length() < 6) {
            throw new IllegalArgumentException("Password length is required");
        }
        userRepository.findByEmail(userRequest.getEmail())
                .ifPresent(u -> {
                    throw new IllegalStateException("Email already in use");
                });
        User user = userMapper.toEntity(userRequest);
        user.setPassword(passwordEncoder.encode(userRequest.getPassword()));
        User saved = userRepository.save(user);
        return userMapper.toDto(saved);
    }
}
