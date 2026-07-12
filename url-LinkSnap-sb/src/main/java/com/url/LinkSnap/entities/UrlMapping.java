package com.url.LinkSnap.models;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@Table(name = "url_mapping")
public class UrlMapping {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String longUrl;
    private String shortUrl;
    private Integer clickCounts = 0;
    private LocalDateTime createdDate;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "urlMapping")

    private List<ClickEvent> clickEvents;


}
