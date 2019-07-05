package com.travelneer.post;

import com.travelneer.controller.api.PostsController;
import com.travelneer.controller.api.v1.CountryPostsController;
import com.travelneer.jwt.JwtValidator;
import com.travelneer.repository.CountryRepository;
import com.travelneer.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@Component
public class PostFactory {

    private final JwtValidator validator;
    private final PostRepository postRepository;
    private final CountryRepository countryRepository;


    @Autowired
    public PostFactory(JwtValidator validator, PostRepository postRepository, CountryRepository countryRepository) {
        this.validator = validator;
        this.postRepository = postRepository;
        this.countryRepository = countryRepository;
    }

    public Post createPost(String content, Short countryId) throws Exception {

        Post post = new Post();
        post.setContent(content);
        post.setCountryId(countryId);
        post.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        post.setAuthorId(validator.getUserId());

        post.validate();

        if(!countryRepository.isCountryFollowedByUser(validator.getUserId(), countryId)){
            throw new Exception("You must be followed to post");
        }

        postRepository.save(post);

        return post;
    }

    public PostResource getPost(int postId) throws Exception {
        Post post =  postRepository.getOneById(postId);
        post.calculateTimeDifference();
        var postResource = post.toResource();
        postResource.setFavourite(postRepository.isPostFavouriteByUser(postId, validator.getUserId()));

        return postResource;
    }

    public FeedResource getFeed(int page) throws Exception {

        List<Post> posts = postRepository.getFeed(validator.getUserId(), page);

        List<PostResource> postResources = checkFavourites(posts);

        FeedResource feedResource = new FeedResource(postResources, page);
        feedResource.add(linkTo(methodOn(PostsController.class).getFeed(page)).withSelfRel());
        feedResource.add(linkTo(methodOn(PostsController.class).getFeed(page+10)).withRel("next"));

        return feedResource;
    }

    public FeedResource getCountryPosts(short countryId, int page) throws Exception {
        List<Post> posts = postRepository.getPostsByCountryId(countryId, page);

        List<PostResource> postResources = checkFavourites(posts);

        FeedResource feedResource = new FeedResource(postResources, page);
        feedResource.add(linkTo(methodOn(CountryPostsController.class).getCountryPosts(countryId, page)).withSelfRel());;
        feedResource.add(linkTo(methodOn(CountryPostsController.class).getCountryPosts(countryId, page+10)).withRel("next"));

        return feedResource;
    }

    public FeedResource getMyPosts(int page) throws SQLException {

        List<Post> posts = postRepository.getPostsByAuthorId(validator.getUserId(), page);

        List<PostResource> postResources = checkFavourites(posts);

        FeedResource feedResource = new FeedResource(postResources, page);
        feedResource.add(linkTo(methodOn(PostsController.class).getMyPosts(page)).withSelfRel());
        feedResource.add(linkTo(methodOn(PostsController.class).getMyPosts(page+10)).withRel("next"));

        return feedResource;

    }

    public List<PostResource> checkFavourites(List<Post> posts) {

        posts.forEach(Post::calculateTimeDifference);
        List<PostResource> postResources = posts.stream().map(Post::toResource)
                .collect(Collectors.toList());

        postResources.forEach(e ->
                e.setFavourite(postRepository.isPostFavouriteByUser(e.getPostId(), validator.getUserId())));

        return postResources;
    }
}
