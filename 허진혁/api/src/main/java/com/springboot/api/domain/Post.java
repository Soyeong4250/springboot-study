package com.springboot.api.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Table(name = "Posts")
public class Post extends BaseTime {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id")
    private Long id;

    @Column(length = 100, nullable = false)
    private String title;

    @Column(length = 500, columnDefinition = "TEXT", nullable = false)
    private String content;

    @Column(nullable = false)
    private String writer;

    @Column(columnDefinition = "INTEGER",nullable = false)
    private Integer viewCount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Builder
    public Post(Long id, String title, String content, String writer, Integer viewCount) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.writer = writer;
        this.viewCount = viewCount;
    }
}
