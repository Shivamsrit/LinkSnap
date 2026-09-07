package com.url.LinkSnap.repositories;

import com.url.LinkSnap.dto.ClickEventDto;
import com.url.LinkSnap.entities.ClickEvent;
import com.url.LinkSnap.entities.UrlMapping;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ClickEventRepository extends JpaRepository<ClickEvent, Long> {
    List<ClickEvent> findByUrlMappingAndClickDateBetween(UrlMapping mapping, LocalDateTime start, LocalDateTime end);
    List<ClickEvent> findByUrlMappingInAndClickDateBetween(List<UrlMapping> mapping, LocalDateTime start, LocalDateTime end);
}
