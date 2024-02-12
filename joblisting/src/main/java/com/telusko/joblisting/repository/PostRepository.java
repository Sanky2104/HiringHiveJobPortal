package com.telusko.joblisting.repository;

import java.util.List;

import com.telusko.joblisting.model.Post;

public interface PostRepository {


	Post save(Post post);

	List<Post> findAll();


}
