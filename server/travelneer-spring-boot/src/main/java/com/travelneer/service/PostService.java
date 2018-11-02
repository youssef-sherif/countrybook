/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.travelneer.service;

import com.travelneer.hateoas.FeedResource;
import com.travelneer.jwt.JwtValidator;
import com.travelneer.repository.PostRepository;
import com.travelneer.hateoas.PostResource;
import com.travelneer.dto.Post;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Youssef
 */
@Service
public class PostService {

	private final PostRepository postRepository;
	private final JwtValidator validator;

	@Autowired
	public PostService(PostRepository postDAO, JwtValidator validator) {
		this.postRepository = postDAO;
		this.validator = validator;
	}


	public void create(Post post) throws Exception{
		post.setCreatedAt(new Timestamp(System.currentTimeMillis()));
		post.setAuthorId(validator.getUserId());

		postRepository.save(post);
	}


	public List<PostResource> getFeed() throws Exception{
		List<Post> posts = postRepository.getFeedByUserId(validator.getUserId());
		List<PostResource> postResources = posts.stream().map(PostResource::new)
				.collect(Collectors.toList());

		return postResources;
	}


	public List<PostResource> getCountryPosts(short countryId) throws Exception{
		List<Post> posts = postRepository.getPostsByCountryId(countryId);

		List<PostResource> postResources = posts.stream().map(PostResource::new)
				.collect(Collectors.toList());

		return postResources;
	}


	public Post getPost(int postId) throws Exception{

		return postRepository.getOneById(postId);
	}

}
