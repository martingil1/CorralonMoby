package com.practice.mobydigital.models;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Customers")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_customer")
    private Integer id;

    @NotNull
    @Column()
    private String name;

    @NotNull
    @Column()
    private String surname;

    @CreationTimestamp
    @Column()
    private LocalDateTime birth;

    @NotNull
    @Column()
    private String phone;

    @NotNull
    @Column()
    private String dni;

    @Column(name="is_final_consumer")
    private Boolean isFinalConsumer;

}
