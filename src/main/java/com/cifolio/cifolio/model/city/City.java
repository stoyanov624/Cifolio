package com.cifolio.cifolio.model.city;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.*;
import org.hibernate.annotations.Fetch;

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
