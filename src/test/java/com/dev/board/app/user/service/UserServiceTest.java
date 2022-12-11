package com.dev.board.app.user.service;

import com.dev.board.app.user.data.dto.UserDto;
import com.dev.board.app.user.data.dto.UserJoinRequest;
import com.dev.board.app.user.data.entity.User;
import com.dev.board.app.user.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

class UserServiceTest {
    
    private UserRepository userRepository = Mockito.mock(UserRepository.class);
    
    private UserService userService;

    @BeforeEach
    void setUp() { // 테스트 단위 수동으로 의존성 주입
        userService = new UserService(userRepository, null);
    }

    @Test
    @DisplayName("Test - 회원등록 성공")
    void userJoin() {
        Mockito.when(userRepository.save(any()))
                .thenReturn(new User(1L, "testUser", "testPwd", "test@email.com"));

        UserDto userJoinResponse
                = userService.joinUser(new UserJoinRequest("testUser", "testPwd", "test@gmail.com"));
        
        assertEquals("testUser", userJoinResponse.getUserName());
        assertEquals("test@email.com", userJoinResponse.getEmailAddress());
    }
}