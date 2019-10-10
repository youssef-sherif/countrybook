package com.travelneer.post;

import org.junit.Test;

import java.sql.Timestamp;

import static org.assertj.core.api.Assertions.assertThat;

public class PostTest {

    @Test
    public void withEmptyPosts_ItShouldThrowException() {

        Post post = new Post();
        post.setAuthorId(1);
        post.setCountryCode("EG");
        post.setContent("");
        post.setCreatedAt(new Timestamp(System.currentTimeMillis()));

        try {
            post.validate();
        } catch (Exception e) {
            assertThat(e.getMessage()).isEqualTo("Posts cannot be empty");

        }
    }

    @Test
    public void withPostsExceeding500Chars_ItShouldThrowException() {
        StringBuilder stringBuilder = new StringBuilder();
        String content;

        for(int i = 0; i < 501; i++) {
            stringBuilder.append('a');
        }

        content = stringBuilder.toString();

        Post post = new Post();
        post.setCountryCode("EG");
        post.setAuthorId(1);
        post.setContent(content);
        post.setCreatedAt(new Timestamp(System.currentTimeMillis()));

        try {
            post.validate();
        } catch (Exception e) {
            assertThat(e.getMessage()).isEqualTo("Posts cannot exceed length of 500");
        }
    }

}
