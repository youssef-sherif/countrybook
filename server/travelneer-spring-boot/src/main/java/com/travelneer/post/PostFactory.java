package com.travelneer.post;

import com.travelneer.jwt.JwtValidator;
import com.travelneer.repository.FavouritesRepository;
import com.travelneer.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class PostFactory {

    private final JwtValidator validator;
    private final FavouritesRepository favouritesRepository;
    private final PostRepository postRepository;


    @Autowired
    public PostFactory(JwtValidator validator, FavouritesRepository favouritesRepository, PostRepository postRepository) {
        this.validator = validator;
        this.favouritesRepository = favouritesRepository;
        this.postRepository = postRepository;
    }

    public Post createPost(String content, Short countryId) throws Exception {
        if(content.isEmpty()) {
            throw new Exception("Empty post");
        }

        if(countryId == null) {
            throw new Exception("No country selected");
        }

        Post post = new Post();
        post.setContent(content);
        post.setCountryId(countryId);
        post.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        post.setAuthorId(validator.getUserId());

        postRepository.save(post);

        return post;
    }

    public FeedResource getFeed() throws Exception {

        List<Post> posts = postRepository.getFeed(validator.getUserId());

        posts.forEach(Post::calculateTimeDifference);
        List<PostResource> postResources = posts.stream().map(Post::toResource)
                .collect(Collectors.toList());
        postResources.forEach(e ->
                e.setFavourite(favouritesRepository.isPostFavouriteByUser(e.getPostId(), validator.getUserId())));

        return new FeedResource(postResources);

    }


    public PostResource getPost(int postId) throws Exception {
        Post post =  postRepository.getOneById(postId);
        post.calculateTimeDifference();
        var postResource = post.toResource();
        postResource.setFavourite(favouritesRepository.isPostFavouriteByUser(postId, validator.getUserId()));

        return postResource;
    }

}
