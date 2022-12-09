package com.dev.board.app.user.service;

import com.dev.board.app.user.dao.dto.UserDto;
import com.dev.board.app.user.dao.dto.UserJoinRequest;
import com.dev.board.app.user.dao.entity.User;
import com.dev.board.app.user.exception.ErrorCode;
import com.dev.board.app.user.exception.UserAppException;
import com.dev.board.app.user.repository.UserRepository;
import com.dev.board.global.utils.JwtTokenUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder encoder;

    @Value("${jwt.token.secret}")
    private String secretKey;

    private long expiredTimeMs = 1000 * 60 * 60; // 1 hour

    public UserDto joinUser(UserJoinRequest userJoinRequest) {
        /**
         * Join 시 UserName 중복 체크
         * 중복 -> Exception 처리 후 Client 에게 에러 내용 반환
         */
        userRepository.findByUserName(userJoinRequest.getUserName())
                .ifPresent(user -> {
                    throw new UserAppException(ErrorCode.DUPLICATED_USER_NAME, userJoinRequest.getUserName());
                });

        User savedUser =  userRepository.save(userJoinRequest.toEntity(userJoinRequest.getPassword()));
        return UserDto.builder()
                .id(savedUser.getId())
                .userName(savedUser.getUserName())
                .password(savedUser.getPassword())
                .emailAddress(savedUser.getEmailAddress())
                .build();
    }

    public User getUserByUserName(String userName) {
        return userRepository.findByUserName(userName)
                .orElseThrow(() -> new UserAppException(ErrorCode.NOT_FOUND, "해당 유저가 존재하지 않습니다."));
    }

    public String loginUser(String userName, String password) {
        /**
         * userName이 있는지 check 없다면 Exception 처리
         * Password 일치 확인 후 Token 발급
         */
        User user = userRepository.findByUserName(userName)
                .orElseThrow(() ->new UserAppException(ErrorCode.NOT_FOUND, String.format("%s User was not found.", userName)));

        if (!encoder.matches(password, user.getPassword())) {
            throw new UserAppException(ErrorCode.INVALID_PASSWORD, "The Password is wrong.");
        }

        // token 발급
        String token = JwtTokenUtils.generateToken(userName, secretKey, expiredTimeMs);

        return token;
    }
}
