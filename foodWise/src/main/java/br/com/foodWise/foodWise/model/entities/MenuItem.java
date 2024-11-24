package br.com.foodWise.foodWise.model.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.OffsetDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "menu_item")
public class MenuItem {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int id;

    @ManyToOne
    @JoinColumn(name = "menu_id")
    private Menu menu;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "text")
    private String text;

    @Column(name = "price", nullable = false)
    private float price;

    @Column(name= "category", nullable = false)
    private String category;

    @Column(name = "is_avaliable")
    private boolean isAvaliable;

    @Column(name = "image_url")
    private String imageUrl;

    @Column(name = "created_at")
    private OffsetDateTime createdAt;

    @Column(name = "updated_at")
    private OffsetDateTime updatedAt;
}
