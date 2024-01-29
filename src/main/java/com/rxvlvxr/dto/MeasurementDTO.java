package com.rxvlvxr.dto;

// класс, который описывает все значения, которые будут отображаться (или передаваться) в теле ответа/запроса
public class MeasurementDTO {
    private Double value;
    private Boolean raining;
    private SensorDTO sensor;

    // пустор конструктор обязателен для Jackson
    public MeasurementDTO() {
    }

    public MeasurementDTO(Double value, Boolean raining, SensorDTO sensor) {
        this.value = value;
        this.raining = raining;
        this.sensor = sensor;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public Boolean getRaining() {
        return raining;
    }

    public void setRaining(Boolean raining) {
        this.raining = raining;
    }

    public SensorDTO getSensor() {
        return sensor;
    }

    public void setSensor(SensorDTO sensor) {
        this.sensor = sensor;
    }
}
