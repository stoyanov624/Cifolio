package com.cifolio.cifolio.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "cities")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class City {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column
    private String photo;

    public City(String name, String photo) {
        this.name = name;
        this.photo = photo;
    }
}
