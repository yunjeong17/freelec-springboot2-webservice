package com.yun.book.springboot.service.posts;

import com.yun.book.springboot.domain.post.Posts;
import com.yun.book.springboot.domain.post.PostsRepository;
import com.yun.book.springboot.web.dto.PostsListResponseDto;
import com.yun.book.springboot.web.dto.PostsResponseDto;
import com.yun.book.springboot.web.dto.PostsSaveRequestDto;
import javafx.geometry.Pos;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class PostsService {
    private final PostsRepository postsRepository;

    @Transactional
    public Long save(PostsSaveRequestDto requestDto){
        return postsRepository.save(requestDto.toEntity()).getId();
    }

    @Transactional
    public long update(Long id, PostsSaveRequestDto requestDto) {
        Posts post = postsRepository.findById(id).orElseThrow(()->new IllegalArgumentException("해당 게시글이 없습니다. id="+id));
        post.update(requestDto.getTitle(),requestDto.getContent());
        return id;
    }

    @Transactional
    public void delete(Long id){
        Posts post = postsRepository.findById(id).orElseThrow(()->new IllegalArgumentException("해당 게시글이 없습니다. id="+id));
        postsRepository.delete(post);
    }

    public PostsResponseDto findById(Long id){
        Posts entity = postsRepository.findById(id).orElseThrow(()->new IllegalArgumentException("해당 게시글이 없습니다. id="+id));
        return new PostsResponseDto(entity);
    }

    @Transactional(readOnly=true)
    public List<PostsListResponseDto> findAllDesc(){
        return postsRepository.findAllDesc().stream().map(PostsListResponseDto::new).collect(Collectors.toList());
        // postsRepository 결과로 넘어온 Posts의 Stream을 map을 통해 PostsListResponseDto로 변환 > List로 반환하는 메세ㅗ드
        //.map(PostsListResponseDto::new) == .map(posts->new PostsListResponseDto(posts))

    }

}
