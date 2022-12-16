package com.springboot.api.domain.dto;

import com.springboot.api.domain.Role;
import com.springboot.api.domain.User;
import lombok.*;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserCreateRequest {

    private String username;
    private String password;
    private String nickname;
    private String email;
    private String createdDate;
    private String modifiedDate;
    private Role role;

    public User toEntity() {
        return User.builder()
                .username(username)
                .password(password)
                .nickname(nickname)
                .email(email)
                .role(Role.USER)
                .build();
    }
}
