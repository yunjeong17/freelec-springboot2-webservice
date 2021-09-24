package com.yun.book.springboot.domain.post;

import javafx.geometry.Pos;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

// 주요 어노테이션을 클래스에 가깝게 둔다.
// entity 클래스에서는 절대 setter 메소드를 만들지 않는다!!!

@Getter //롬복 어노테이션, getter를 자동 생성
@NoArgsConstructor //롬복 어노테이션, 기본 생성자 자동 생성
@Entity //jpa 어노테이션
public class Posts {

    @Id //pk
    @GeneratedValue(strategy= GenerationType.IDENTITY) //auto increment
    private Long id;

    @Column(length = 500,nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT",nullable = false)
    private String content;

    private String author;

    @Builder
    public Posts(String title,String content,String author){
        this.title=title;
        this.content=content;
        this.author=author;
    }



}
