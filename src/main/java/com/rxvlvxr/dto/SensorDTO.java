package com.rxvlvxr.dto;


public class SensorDTO {
    private String name;

    // пустой класс обязателен
    public SensorDTO() {
    }

    public SensorDTO(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
