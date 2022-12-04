package com.springboot.api.repository;

import com.springboot.api.domain.Comment;
import com.springboot.api.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByPostOrderById(Post post);
}
