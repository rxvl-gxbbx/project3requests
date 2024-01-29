package com.rxvlvxr.dto;

import java.util.List;

// best practice
public class MeasurementsResponse {
    private List<MeasurementDTO> measurements;

    public MeasurementsResponse() {
    }

    public List<MeasurementDTO> getMeasurements() {
        return measurements;
    }

    public void setMeasurements(List<MeasurementDTO> measurements) {
        this.measurements = measurements;
    }
}
