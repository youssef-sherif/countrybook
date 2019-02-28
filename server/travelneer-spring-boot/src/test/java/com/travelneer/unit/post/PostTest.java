package com.travelneer.unit.post;

import com.travelneer.post.Post;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.Timestamp;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@TestPropertySource(locations="classpath:application-unit-test.properties")
public class PostTest {

    @Test
    public void withEmptyPosts_ItShouldThrowException() {

        Post post = new Post();
        post.setCountryId((short) 1);
        post.setAuthorId(1);
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
        post.setCountryId((short) 1);
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
