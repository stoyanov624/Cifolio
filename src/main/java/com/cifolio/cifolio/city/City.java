package com.cifolio.cifolio.city;

import javax.persistence.*;

@Entity
@Table(name = "cities")
public class City {
    @Id
    private Long id;
    private String name;
    private String photo;

    public City() {}
    public City(String name, String photo) {
        this.name = name;
        this.photo = photo;
    }

    @Override
    public String toString() {
        return "City{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", photoUrl='" + photo + '\'' +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }
}
