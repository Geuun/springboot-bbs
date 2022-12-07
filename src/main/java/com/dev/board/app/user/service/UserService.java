package com.dev.board.app.user.service;

import com.dev.board.app.user.dao.dto.UserDto;
import com.dev.board.app.user.dao.dto.UserJoinRequest;
import com.dev.board.app.user.dao.entity.User;
import com.dev.board.app.user.exception.ErrorCode;
import com.dev.board.app.user.exception.UserAppException;
import com.dev.board.app.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

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
}
