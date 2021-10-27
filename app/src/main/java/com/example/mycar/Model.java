package com.example.mycar;

public class Model {
    int id;
    byte[] image;
    String name;
    String color;
    String c_model;
    String cc;
    String price;



    public Model(byte[] image, String name, String color, String c_model, String cc, String price) {
        this.image = image;
        this.name = name;
        this.color = color;
        this.c_model = c_model;
        this.cc = cc;
        this.price = price;
    }

    public Model() {

    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getC_model() {
        return c_model;
    }

    public void setC_model(String c_model) {
        this.c_model = c_model;
    }

    public String getCc() {
        return cc;
    }

    public void setCc(String cc) {
        this.cc = cc;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
