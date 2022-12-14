package com.cifolio.cifolio.models.city;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity(name = "travel_guides")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class TravelGuide {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @ManyToMany
    @JoinTable(
            name = "guides_cities",
            joinColumns = { @JoinColumn(name = "guide_id") },
            inverseJoinColumns = { @JoinColumn(name = "city_id") }
    )
    Set<City> cities = new HashSet<>();
}
