package com.yun.book.springboot.domain.posts;

import com.yun.book.springboot.domain.post.Posts;
import com.yun.book.springboot.domain.post.PostsRepository;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest //이 어노테이션을 쓰면 h2데이터베이스를 자동으로 생성
public class PostsRepositoryTest {
    @Autowired
    PostsRepository postsRepository;

    @After
    public void cleanup(){
        postsRepository.deleteAll();
    }
    @Test
    public void 게시글저장_불러오기(){
        //given
        String title="테스트게시글";
        String content="테스트본문";

        postsRepository.save(
                Posts.builder()
                        .title(title)
                        .content(content)
                        .author("zcx@naver.com")
                        .build()
        );

        //when
        List<Posts> postsList=postsRepository.findAll();

        //then
        Posts posts = postsList.get(0);
        assertThat(posts.getTitle()).isEqualTo(title);
        assertThat(posts.getContent()).isEqualTo(content);
    }

    @Test
    public void BaseTimeEntity_등록(){
        //given
        LocalDateTime now=LocalDateTime.of(2019,6,4,0,0,0);
        postsRepository.save(Posts.builder().title("title").content("content").author("author").build());

        //when
        List<Posts> postsList=postsRepository.findAll();

        //then
        Posts posts=postsList.get(0);

        System.out.println(">>>createDate="+posts.getCreatedDate()+", modifiedDate"+posts.getModifiedDate());

        assertThat(posts.getCreatedDate().isAfter(now));
        assertThat(posts.getModifiedDate().isAfter(now));


    }

}
