package me.kursaDarbs.app.multiforms;

import org.springframework.web.multipart.MultipartFile;

public class CinemaForm {

    private Integer id;
    private String email;
    private String cinemaName;
    private Integer city;
    private String address;
    private String phoneNumber;
    private Double latitude;
    private Double longitude;
    private MultipartFile img;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getcinemaName() {
        return cinemaName;
    }

    public void setcinemaName(String cinemaName) {
        this.cinemaName = cinemaName;
    }

    public Integer getCity() {
        return city;
    }

    public void setCity(Integer city) {
        this.city = city;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public MultipartFile getImg() {
        return img;
    }

    public void setImg(MultipartFile img) {
        this.img = img;
    }
}