package com.travelneer.api.noauth;

import com.travelneer.api.auth.CommentsController;
import com.travelneer.api.auth.v1.FavouritesController;
import com.travelneer.post.Post;
import com.travelneer.post.PostResource;
import com.travelneer.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RestController
@CrossOrigin(origins = {"http://localhost:3000", "http://localhost:5000"})
public class PostsPublicController {

    private final PostRepository postRepository;

    @Autowired
    public PostsPublicController(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @RequestMapping(value = "/posts/{postId}", method = RequestMethod.GET)
    public ResponseEntity<?> getPost(@PathVariable("postId") int postId) {

        try {

            Post post =  postRepository.getOneById(postId);
            post.calculateTimeDifference();
            PostResource postResource = post.toResource();

            postResource.add(linkTo(methodOn(PostsPublicController.class).getPost(postId)).withSelfRel());
            postResource.add(linkTo(methodOn(FavouritesController.class).getFavouritesCount(postId)).withRel("favouritesCount"));
            postResource.add(linkTo(methodOn(CommentsPublicController.class).getCommentsCount(postId)).withRel("commentsCount"));
            postResource.add(linkTo(methodOn(CommentsController.class).newComment(postId, null)).withRel("newComment"));
            postResource.add(linkTo(methodOn(CommentsPublicController.class).getMainComments(postId, 0)).withRel("comments"));


            return new ResponseEntity<>(postResource, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

}
