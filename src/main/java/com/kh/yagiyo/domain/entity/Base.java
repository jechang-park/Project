package com.kh.yagiyo.domain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@Data
public class Base {
  @CreationTimestamp
  @Column(name = "CREATE_TIME", updatable = false)
  private LocalDateTime CREATE_TIME;

  @UpdateTimestamp
  @Column(name="UPDATE_TIME", insertable = false)
  private LocalDateTime UPDATE_TIME;
}
