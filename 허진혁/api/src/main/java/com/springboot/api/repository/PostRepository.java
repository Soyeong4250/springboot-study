package com.springboot.api.repository;

import com.springboot.api.domain.Comment;
import com.springboot.api.domain.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    Page<Post> findByTitleContaining(Pageable pageable, String keyword);

    @Query("update Post p set p.viewCount = p.viewCount+1 where p.id = :id")
    int updateViewCount(Long postId);

}
