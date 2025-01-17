package org.crazyspace_edu.api.config;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.List;

public class UserPrincipal extends User {
    private final Long userId;

    // role: 역할 -> 관리자, 사용자, 매니저
    // authority: 권한 -> 글쓰기, 글 읽기, 사용자정지시키기

    public UserPrincipal(org.crazyspace_edu.api.domain.user.User user) {
        super(user.getEmail(), user.getPassword(),
                List.of(
                        new SimpleGrantedAuthority("ROLE_ADMIN")
//                        new SimpleGrantedAuthority("WRITE")
                ));
        this.userId = user.getId();
    }

    public Long getUserId() {
        return userId;
    }

}