package com.springboot.api.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Posts {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String content;
    private String writer;
    private String viewCount;

    @Embedded
    private Period period;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Builder
    public Posts(String title, String content, String writer) {
        this.title = title;
        this.content = content;
        this.writer = writer;
    }
}
