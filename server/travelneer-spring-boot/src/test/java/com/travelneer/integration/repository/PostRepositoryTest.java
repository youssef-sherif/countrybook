package com.travelneer.integration.repository;

import com.travelneer.post.Post;
import com.travelneer.repository.PostRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.SQLException;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@TestPropertySource(locations="classpath:application-integration-test.properties")
public class PostRepositoryTest {

    @Autowired
    PostRepository postRepository;

    @Test
    public void whenGettingFeed_ItShouldNotReturnMoreThan10Posts() throws SQLException {
    }

}
