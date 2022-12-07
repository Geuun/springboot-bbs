package com.dev.board.app.user.repository;

import com.dev.board.app.user.dao.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {
    /**
     *  User Name 으로 찾기
     *  비즈니스 로직 -> 회원가입 아이디 중복 검사
     */
    Optional<User> findByUserName(String userName);
}
