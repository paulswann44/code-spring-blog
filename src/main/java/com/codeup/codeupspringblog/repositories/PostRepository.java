package com.codeup.codeupspringblog.repositories;

import com.codeup.codeupspringblog.models.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

public interface PostRepository extends JpaRepository<Post, Long> {
}
