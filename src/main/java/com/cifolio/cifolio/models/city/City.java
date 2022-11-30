package com.cifolio.cifolio.models.city;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "cities")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class City {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column
    private String photo;

    @JsonIgnore
    @ManyToMany(mappedBy = "cities")
    private Set<TravelGuide> guides = new HashSet<>();

    public City(String name, String photo) {
        this.name = name;
        this.photo = photo;
    }
}
