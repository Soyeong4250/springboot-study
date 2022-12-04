package com.springboot.api.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Table(name = "users")
public class User {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @Column(length = 25, unique = true, nullable = false)
    private String username;
    @Column(length = 25)
    private String password;
    @Column(length = 25, unique = true, nullable = false)
    private String nickname;
    @Column(length = 40, unique = true, nullable = false)
    private String email;

    @Embedded
    private BaseTime baseTime;

    @OneToMany(mappedBy = "user")
    private List<Post> posts = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<Comment> comments = new ArrayList<>();
}
