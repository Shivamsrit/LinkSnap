package com.url.LinkSnap.repositories;

import com.url.LinkSnap.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
}
