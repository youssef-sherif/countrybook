package com.travelneer.post;

import com.travelneer.controller.api.PostsController;
import com.travelneer.controller.api.v1.CommentsController;
import com.travelneer.controller.api.v1.CountryPostsController;
import com.travelneer.controller.api.v1.FavouritesController;
import com.travelneer.jwt.JwtValidator;
import com.travelneer.repository.CommentRepository;
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
    private final CommentRepository commentRepository;


    @Autowired
    public PostFactory(JwtValidator validator, PostRepository postRepository, CountryRepository countryRepository, CommentRepository commentRepository) {
        this.validator = validator;
        this.postRepository = postRepository;
        this.countryRepository = countryRepository;
        this.commentRepository = commentRepository;
    }

    public Post createPost(String countryCode, String content) throws Exception {

        Post post = new Post();
        post.setContent(content);
        post.setCountryCode(countryCode);
        post.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        post.setAuthorId(validator.getUserId());

        post.validate();

        if(!countryRepository.isCountryFollowedByUser(validator.getUserId(), countryCode)){
            throw new Exception("You must be followed to post");
        }

        postRepository.save(post);

        return post;
    }

    public Post createComment(int parentPostId, String comment) throws SQLException {
        Post post = new Post();
        post.setContent(comment);
        post.setParentPostId(parentPostId);
        post.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        post.setAuthorId(validator.getUserId());

        commentRepository.save(post);

        return post;

    }

    public PostResource getPost(int postId) throws Exception {
        Post post =  postRepository.getOneById(postId);
        post.calculateTimeDifference();
        var postResource = post.toResource();
        postResource.setFavourite(postRepository.isPostFavouriteByUser(postId, validator.getUserId()));

        postResource.add(linkTo(methodOn(PostsController.class).getPost(postId)).withSelfRel());
        postResource.add(linkTo(methodOn(FavouritesController.class).getFavouritesCount(postId)).withRel("favouritesCount"));
        postResource.add(linkTo(methodOn(CommentsController.class).getCommentsCount(postId)).withRel("commentsCount"));
        postResource.add(linkTo(methodOn(CommentsController.class).newComment(postId, null)).withRel("newComment"));
        postResource.add(linkTo(methodOn(CommentsController.class).getComments(postId, 0)).withRel("comments"));

        return postResource;
    }

    public PostListResource getFeed(int page) throws Exception {

        List<Post> posts = postRepository.getFeed(validator.getUserId(), page);

        List<PostResource> postResources = transformPostListToPostResourceList(posts);

        postResources.forEach(e ->
                e.setFavourite(postRepository.isPostFavouriteByUser(e.getPostId(), validator.getUserId())));

        PostListResource postListResource = new PostListResource(postResources);
        postListResource.add(linkTo(methodOn(PostsController.class).getFeed(page)).withSelfRel());
        postListResource.add(linkTo(methodOn(PostsController.class).getFeed(page+10)).withRel("next"));

        return postListResource;
    }

    public PostListResource getComments(int postId, int page) throws SQLException {

        List<Post> comments = commentRepository.getCommentsByParentPostId(postId, page);

        comments.forEach(Post::calculateTimeDifference);
        List<PostResource> commentResources = comments.stream().map(Post::toResource)
                .collect(Collectors.toList());

        PostListResource postListResource = new PostListResource(commentResources);
        postListResource.add(linkTo(methodOn(CommentsController.class).getComments(postId, page)).withSelfRel());
        postListResource.add(linkTo(methodOn(CommentsController.class).getComments(postId, page+10)).withRel("next"));

        return postListResource;
    }

    public PostListResource getCountryPosts(String countryCode, int page) throws Exception {
        List<Post> posts = postRepository.getPostsByCountryCode(countryCode, page);

        List<PostResource> postResources = transformPostListToPostResourceList(posts);

        postResources.forEach(e ->
                e.setFavourite(postRepository.isPostFavouriteByUser(e.getPostId(), validator.getUserId())));

        PostListResource postListResource = new PostListResource(postResources);
        postListResource.add(linkTo(methodOn(CountryPostsController.class).getCountryPosts(countryCode, page)).withSelfRel());;
        postListResource.add(linkTo(methodOn(CountryPostsController.class).getCountryPosts(countryCode, page+10)).withRel("next"));

        return postListResource;
    }

    public PostListResource getMyPosts(int page) throws SQLException {

        List<Post> posts = postRepository.getPostsByAuthorId(validator.getUserId(), page);

        List<PostResource> postResources = transformPostListToPostResourceList(posts);

        postResources.forEach(e ->
                e.setFavourite(postRepository.isPostFavouriteByUser(e.getPostId(), validator.getUserId())));

        PostListResource postListResource = new PostListResource(postResources);
        postListResource.add(linkTo(methodOn(PostsController.class).getMyPosts(page)).withSelfRel());
        postListResource.add(linkTo(methodOn(PostsController.class).getMyPosts(page+10)).withRel("next"));

        return postListResource;

    }

    private List<PostResource> transformPostListToPostResourceList(List<Post> posts) {

        posts.forEach(Post::calculateTimeDifference);
        List<PostResource> postResources = posts.stream().map(Post::toResource)
                .collect(Collectors.toList());

        return postResources;
    }
}
