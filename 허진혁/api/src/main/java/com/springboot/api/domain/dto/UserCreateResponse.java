package com.springboot.api.domain.dto;

import com.springboot.api.domain.User;
import lombok.Builder;
import lombok.Getter;

@Getter
public class UserCreateResponse {

    private Long id;
    private String username;
    private String password;
    private String nickname;
    private String email;
    private String createdDate;
    private String modifiedDate;

    @Builder
    public UserCreateResponse(Long id, String username, String password, String nickname, String email, String createdDate, String modifiedDate) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.nickname = nickname;
        this.email = email;
        this.createdDate = createdDate;
        this.modifiedDate = modifiedDate;
    }

    public static UserCreateResponse of(User user) {
        return UserCreateResponse.builder()
                .id(user.getId())
                .username(user.getUsername())
                .password(user.getPassword())
                .nickname(user.getNickname())
                .email(user.getEmail())
                .createdDate(user.getCreatedDate())
                .modifiedDate(user.getModifiedDate())
                .build();
    }

}
