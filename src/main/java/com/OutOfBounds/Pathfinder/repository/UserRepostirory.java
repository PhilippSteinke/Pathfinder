package com.OutOfBounds.Pathfinder.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.OutOfBounds.Pathfinder.model.ApplicationUser;

public interface UserRepostirory extends MongoRepository<ApplicationUser, String> {

	ApplicationUser findByUsername(String username);

}
