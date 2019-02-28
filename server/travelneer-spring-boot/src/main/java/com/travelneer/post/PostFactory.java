package com.travelneer.post;

import com.travelneer.controller.api.PostsController;
import com.travelneer.controller.api.v1.CountryPostsController;
import com.travelneer.country.CountryFeedResource;
import com.travelneer.jwt.JwtValidator;
import com.travelneer.repository.CountryFollowsRepository;
import com.travelneer.repository.FavouritesRepository;
import com.travelneer.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@Component
public class PostFactory {

    private final JwtValidator validator;
    private final FavouritesRepository favouritesRepository;
    private final PostRepository postRepository;
    private final CountryFollowsRepository countryFollowsRepository;


    @Autowired
    public PostFactory(JwtValidator validator, FavouritesRepository favouritesRepository, PostRepository postRepository, CountryFollowsRepository countryFollowsRepository) {
        this.validator = validator;
        this.favouritesRepository = favouritesRepository;
        this.postRepository = postRepository;
        this.countryFollowsRepository = countryFollowsRepository;
    }

    public Post createPost(String content, Short countryId) throws Exception {

        Post post = new Post();
        post.setContent(content);
        post.setCountryId(countryId);
        post.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        post.setAuthorId(validator.getUserId());

        post.validate();

        if(!countryFollowsRepository.exists(validator.getUserId(), countryId)){
            throw new Exception("You must be followed to post");
        }

        postRepository.save(post);

        return post;
    }

    public FeedResource getFeed(int page) throws Exception {

        List<Post> posts = postRepository.getFeed(validator.getUserId(), page);

        posts.forEach(Post::calculateTimeDifference);
        List<PostResource> postResources = posts.stream().map(Post::toResource)
                .collect(Collectors.toList());
        postResources.forEach(e ->
                e.setFavourite(favouritesRepository.isPostFavouriteByUser(e.getPostId(), validator.getUserId())));

        FeedResource feedResource = new FeedResource(postResources, page);

        feedResource.add(linkTo(methodOn(PostsController.class).getFeed(page+10)).withRel("next"));

        return feedResource;

    }


    public PostResource getPost(int postId) throws Exception {
        Post post =  postRepository.getOneById(postId);
        post.calculateTimeDifference();
        var postResource = post.toResource();
        postResource.setFavourite(favouritesRepository.isPostFavouriteByUser(postId, validator.getUserId()));

        return postResource;
    }

    public CountryFeedResource getCountryPosts(short countryId, int page) throws Exception {
        List<Post> posts = postRepository.getPostsByCountryId(countryId, page);

        posts.forEach(Post::calculateTimeDifference);
        List<PostResource> postResources = posts.stream().map(Post::toResource)
                .collect(Collectors.toList());
        postResources.forEach(e ->
                e.setFavourite(favouritesRepository.isPostFavouriteByUser(e.getPostId(), validator.getUserId())));

        CountryFeedResource feedResource = new CountryFeedResource(postResources, countryId, page);

        feedResource.add(linkTo(methodOn(CountryPostsController.class).getCountryPosts(countryId, page+10)).withRel("next"));

        return feedResource;
    }
}
