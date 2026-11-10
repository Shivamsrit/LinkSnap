package com.url.LinkSnap.repositories;

import com.url.LinkSnap.entities.UrlMapping;
import com.url.LinkSnap.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.List;

@Repository
public interface UrlMappingRepository extends JpaRepository<UrlMapping, Integer> {
    UrlMapping findByShortUrl(String shortUrl);
    List<UrlMapping> findByUser(User user);
}
