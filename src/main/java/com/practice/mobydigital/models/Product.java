package com.practice.mobydigital.models;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Products")
public class Product {

    @Id
    @Column(name = "id_product")
    private String id;

    @NotNull
    @Column(name = "name_product")
    private String name;

    @NotNull
    @Column()
    private String trademark;

    @NotNull
    @Column(name = "price_unit")
    private Double priceUnit;

    @NotNull
    @Column(name = "current_stock")
    private Integer currentStock;

}
