package com.adrian.my_shop.entity;

import com.adrian.my_shop.enums.CategoryType;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity(name = "products")
@Data
@RequiredArgsConstructor
@NoArgsConstructor
public class ProductEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NonNull
    private String name;

    @NonNull
    private Double price;

    @NonNull
    private Integer quantity;

    @NonNull
    @Enumerated(value = EnumType.STRING)
    private CategoryType categoryType;

    @NonNull
    private String description;

    @ToString.Exclude
    @Lob
    @Column(columnDefinition = "BLOB")
    private byte[] image;

    @OneToMany(mappedBy = "product")
    private List<CartEntry> cartEntryList;

    @OneToMany(mappedBy = "product")
    private List<OrderEntry> orderEntryList;
}
