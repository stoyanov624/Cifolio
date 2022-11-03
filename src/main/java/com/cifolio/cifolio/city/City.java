package com.cifolio.cifolio.city;

import javax.persistence.*;

@Entity
@Table(name = "cities")
public class City {
    @Id
    private Long id;
    private String name;
    private String photoUrl;

    public City() {}
    public City(String name, String photoUrl) {
        this.name = name;
        this.photoUrl = photoUrl;
    }

    @Override
    public String toString() {
        return "City{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", photoUrl='" + photoUrl + '\'' +
                '}';
    }
}
