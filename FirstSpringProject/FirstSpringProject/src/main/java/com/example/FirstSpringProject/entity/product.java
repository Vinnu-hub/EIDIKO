package com.example.FirstSpringProject.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
public class product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private Long price;

    public product(int id, String name, Long price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id) {

        this.id = id;
    }

    public String getName() {

        return name;
    }

    public void setName(String name) {

        this.name = name;
    }

    public Long getPrice() {

        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "product{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", price=" + price +
                '}';
    }
}
