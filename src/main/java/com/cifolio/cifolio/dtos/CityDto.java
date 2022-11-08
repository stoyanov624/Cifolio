package com.cifolio.cifolio.dtos;

public class CityDto {
    private Long id;
    private String name;
    private String photo;

    public CityDto(Long id, String name, String photo) {
        this.id = id;
        this.name = name;
        this.photo = photo;
    }

    public CityDto(String name, String photo) {
        this.name = name;
        this.photo = photo;
    }


    public CityDto() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
