package com.ex.board.entity;

import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@MappedSuperclass
@EntityListeners(value = {AuditingEntityListener.class})  //사용하기 위해서는 GuestbookApplication에 @EnableJpaAuditing  추가
@Getter
public class BaseEntity {

    @CreatedDate //JPA에서 엔티티 생성 시간 처리
    @Column(name = "regdate", updatable = false)
    private LocalDateTime regDate;

    @LastModifiedDate // 최종 수정 시간을 자동으로 처리
    @Column(name = "moddate")
    private LocalDateTime modDate;
}
