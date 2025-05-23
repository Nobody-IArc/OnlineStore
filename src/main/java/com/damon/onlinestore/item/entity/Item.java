package com.damon.onlinestore.item.entity;

import com.damon.onlinestore.item.dto.ItemRead;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity(name = "items")
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 50, nullable = false)
    private String name;

    @Column(length = 100, nullable = false)
    private String imgPath;

    @Column(nullable = false)
    private Integer price;

    @Column(nullable = false)
    private Integer discountPer;

    @Column(nullable = false, updatable = false)
    @CreationTimestamp
    private LocalDateTime createdAt;

    public ItemRead toRead() {
        return ItemRead.builder()
                .id(id)
                .name(name)
                .imgPath(imgPath)
                .price(price)
                .discountPer(discountPer)
                .build();
    }
}
