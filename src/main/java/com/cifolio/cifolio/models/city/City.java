package com.cifolio.cifolio.models.city;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity(name = "cities")
@AllArgsConstructor
@NoArgsConstructor
@Builder
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
}
