package com.bookstudy.springboot.domain.posts;

import org.aspectj.lang.annotation.After;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PostsRepositoryTest {

    @Autowired
    PostsRepository postsRepository;

    @AfterEach
    public void cleanup() {
        postsRepository.deleteAll();
    }

    @Test
    public void 게시글저장_불러오기() {
        //given
        String title = "테스트 게시글";
        String content = "테스트 본문";

        postsRepository.save(Posts.builder()
                .title(title)
                .content(content)
                .author("kmh@hanmail.net")
                .build());

        //when
        List<Posts> postsList = postsRepository.findAll();

        //then
        Posts posts = postsList.get(0);
        org.assertj.core.api.Assertions.assertThat(posts.getTitle()).isEqualTo(title);
        org.assertj.core.api.Assertions.assertThat(posts.getContent()).isEqualTo(content);
    }

    @Test
    public void BastTimeEntity_등록() {
        //given
        LocalDateTime now = LocalDateTime.of(2019, 6, 4, 0, 0, 0);
        postsRepository.save(Posts.builder()
                .title("title")
                .content("content")
                .author("author")
                .build());

        //when
        List<Posts> postsList = postsRepository.findAll();

        //then
        Posts posts = postsList.get(0);

        System.out.println(">>>>> createDate =" + posts.getCreatedDate() + ", modifiedDate = " + posts.getModifiedDate());

        org.assertj.core.api.Assertions.assertThat(posts.getCreatedDate()).isAfter(now);
        org.assertj.core.api.Assertions.assertThat(posts.getModifiedDate()).isAfter(now);
    }
}