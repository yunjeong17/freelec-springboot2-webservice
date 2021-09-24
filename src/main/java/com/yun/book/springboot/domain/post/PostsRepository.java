package com.yun.book.springboot.domain.post;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PostsRepository extends JpaRepository<Posts,Long> { //jpa레파지토리 (엔티티 클래스, pk 타입) 상속
    //엔티티 클래스와 엔티티 레파지토리는 함께 위치. 둘은 아주 밀접한 관계. 엔티티 클래스는 기본 레파지토리 없이는 제대로 역할할 수 없음.
}
