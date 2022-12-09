package com.dev.board.app.user.config;

import com.dev.board.app.user.data.entity.User;
import com.dev.board.app.user.service.UserService;
import com.dev.board.global.utils.JwtTokenUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
public class JwtTokenFilter extends OncePerRequestFilter {

    private final UserService userService;
    private final String secretKey;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        // Token을 어떻게 관리할 것인지 정의

        // Token 가져오기
        final String authorizationHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        log.info("authorizationHeader: {}", authorizationHeader);

        // Token 검증
        if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer")) {
            filterChain.doFilter(request, response);
        }

        // Token 분리
        String token;
        try {
            assert authorizationHeader != null;
            token = authorizationHeader.split(" ")[1];
        } catch (Exception e) {
            log.error("Token extraction failed.");
            filterChain.doFilter(request, response);
            return;
        }

        // Token 만료 확인
        if (JwtTokenUtils.isExpired(token, secretKey)) {
            filterChain.doFilter(request, response);
            return;
        }

        // Token에서 Claim 에서 UserName 꺼내기
        String userName = JwtTokenUtils.getUserName(token, secretKey);
        log.info("userName: {}", userName);

        // UserDetail 가져오기
        User user = userService.getUserByUserName(userName);
        log.info("userRole: {}", user.getUserRole());

        // 권한 부여
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(user.getUserName(), null, List.of(new SimpleGrantedAuthority(user.getUserRole().name())));
        authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        filterChain.doFilter(request, response);
    }
}
