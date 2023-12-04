package org.example;

import java.util.Date;


public class Car {
    private Transmission transmission;
    private String name;
    private String manufacturer;
    private Double weight;
    private Date dateOfIssue;
    private boolean backlit;
    private Double price;
    private Integer safetyIndex;
    private String color;
    private Double length;
    private Double width;
    private Double height;

    public Car(){};
    // Конструктор класса
    public Car(String name, String manufacturer, Double weight, Date dateOfIssue, boolean backlit,
               Double price, Integer safetyIndex, String color, Double length, Double width, Double height,
               Transmission transmission) {
        this.transmission = transmission;
        this.name = name;
        this.manufacturer = manufacturer;
        this.weight = weight;
        this.dateOfIssue = dateOfIssue;
        this.backlit = backlit;
        this.price = price;
        this.safetyIndex = safetyIndex;
        this.color = color;
        this.length = length;
        this.width = width;
        this.height = height;
        this.width = width;
        this.length = length;
        this.height = height;
    }

    // Геттеры и сеттеры для доступа к полям класса
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public Date getDateOfIssue() {
        return dateOfIssue;
    }

    public void setDateOfIssue(Date dateOfIssue) {
        this.dateOfIssue = dateOfIssue;
    }

    public boolean isBacklit() {
        return backlit;
    }

    public void setBacklit(boolean backlit) {
        this.backlit = backlit;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getSafetyIndex() {
        return safetyIndex;
    }

    public void setSafetyIndex(Integer safetyIndex) {
        this.safetyIndex = safetyIndex;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Double getLength() {
        return length;
    }

    public void setLength(Double length) {
        this.length = length;
    }

    public Double getWidth() {
        return width;
    }

    public void setWidth(Double width) {
        this.width = width;
    }

    public Double getHeight() {
        return height;
    }

    public void setHeight(Double height) {
        this.height = height;
    }

    public Transmission getTransmission() {
        return transmission;
    }

    public void setTransmission(Transmission transmission) {
        this.transmission = transmission;
    }
// Другие методы класса, если необходимо
}

