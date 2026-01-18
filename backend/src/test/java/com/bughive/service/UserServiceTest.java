package com.bughive.service;

import com.bughive.dto.UserRequest;
import com.bughive.dto.UserResponse;
import com.bughive.entity.User;
import com.bughive.mapper.UserMapper;
import com.bughive.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private UserMapper userMapper;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private UserService userService;

    private UserRequest userRequest;
    private User user;
    private UserResponse userResponse;

    @BeforeEach
    void setUp() {
        userRequest = new UserRequest();
        userRequest.setEmail("test@example.com");
        userRequest.setPassword("password123");
        userRequest.setName("Test User");

        user = new User();
        user.setId(1L);
        user.setEmail("test@example.com");
        user.setPassword("encodedPassword");
        user.setName("Test User");

        userResponse = new UserResponse();
        userResponse.setId(1L);
        userResponse.setEmail("test@example.com");
        userResponse.setName("Test User");
    }

    @Test
    void createUser_shouldEncodePassword() {
        // Arrange
        when(userRepository.findByEmail(anyString())).thenReturn(Optional.empty());
        when(userMapper.toEntity(any(UserRequest.class))).thenReturn(user);
        when(passwordEncoder.encode(anyString())).thenReturn("encodedPassword");
        when(userRepository.save(any(User.class))).thenReturn(user);
        when(userMapper.toDto(any(User.class))).thenReturn(userResponse);

        // Act
        userService.createUser(userRequest);

        // Assert
        verify(passwordEncoder).encode("password123");
        verify(userRepository).save(user);
    }
}
