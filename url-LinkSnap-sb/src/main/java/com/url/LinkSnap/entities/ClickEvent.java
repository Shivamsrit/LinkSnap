package com.url.LinkSnap.models;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "click_event")
public class ClickEvent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime date;

    @ManyToOne
    @JoinColumn(name ="url_mapping_id")
    private UrlMapping urlMapping;

}
