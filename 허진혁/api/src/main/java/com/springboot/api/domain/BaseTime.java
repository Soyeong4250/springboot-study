package com.springboot.api.domain;

import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Getter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseTime {

    @Column(name = "created_date")
    @CreatedDate
    private String createdDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy/mm/dd HH:mm"));

    @Column(name = "modified_date")
    @LastModifiedDate
    private String modifiedDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy/mm/dd HH:mm"));
}
