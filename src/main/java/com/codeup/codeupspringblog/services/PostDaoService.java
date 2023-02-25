package com.codeup.codeupspringblog.services;

import com.codeup.codeupspringblog.models.Post;
import com.codeup.codeupspringblog.repositories.PostRepository;
import com.codeup.codeupspringblog.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostDaoService {

    // We declare two dependencies here:
    private final PostRepository postRepository;
    private final UserRepository userRepository;

    // Constructor-based dependency injection:
    public PostDaoService(PostRepository postRepository, UserRepository userRepository) {
        // We assign the dependencies to our instance variables:
        this.postRepository = postRepository;
        this.userRepository = userRepository;
    }

    // CREATE
    public void savePost(Post post) {
        // We set the user of the post to be the user with ID 1 (in this example).
        // This is just for illustration purposes, and in a real application,
        // we would get the current user from the session or from a JWT token.
        post.setUser(userRepository.findById(1L).get());
        // We save the post to the database using the PostRepository:
        postRepository.save(post);
    }

    // READ
    public List<Post> getAllPosts() {
        // We retrieve all posts from the database using the PostRepository:
        return postRepository.findAll();
    }

    public Post getPostById(long id) {
        // We retrieve the post with the given ID from the database using the PostRepository:
        if (postRepository.findById(id).isPresent()) {
            return postRepository.findById(id).get();
        } else {
            // If the post is not found, we throw a RuntimeException:
            throw new RuntimeException("Post was not found.");
        }
    }

    // DELETE
    public void deletePostById(long id) {
        // We delete the post with the given ID from the database using the PostRepository:
        postRepository.deleteById(id);
    }

}
