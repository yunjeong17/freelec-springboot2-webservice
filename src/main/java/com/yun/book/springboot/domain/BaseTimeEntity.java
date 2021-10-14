package com.yun.book.springboot.domain;

import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@Getter
@MappedSuperclass //jpa entity 클래스들이 BaseTimeEntity를 상속할 경우 필드들도 컬럼으로 인식
@EntityListeners(AuditingEntityListener.class) //BaseTimeEntity 클래스에 Auditing 기능(Spring Data JPA에서 시간에 대해서 자동으로 값을 넣어주는 기능)을 포함 시킴 https://webcoding-start.tistory.com/53
public abstract class BaseTimeEntity { // 모든 entity의 상위 클래스가 되어 entity들의 createDate, modifiedDate 자동으로 관리하는 역할
    @CreatedDate
    private LocalDateTime createdDate;
    
    @LastModifiedDate
    private LocalDateTime modifiedDate;
}
