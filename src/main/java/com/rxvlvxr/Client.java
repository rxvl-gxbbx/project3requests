package com.rxvlvxr;

import com.rxvlvxr.dto.MeasurementDTO;
import com.rxvlvxr.dto.SensorDTO;
import org.springframework.http.HttpEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.Random;
import java.util.Scanner;

public class Client {
    protected static final int MEASURE_COUNT = 100;

    public static void main(String[] args) {
        final Scanner scanner = new Scanner(System.in);

        System.out.print("Напишите название для сенсора, который хотите зарегистрировать: ");
        String value = scanner.nextLine();

        while (!value.strip().equalsIgnoreCase("exit")) {
            final SensorDTO sensorDTO = new SensorDTO(value);

            try {
                registerSensor(sensorDTO);
                measure(sensorDTO);
            } catch (HttpClientErrorException e) {
                System.out.print("Ошибка! Повторите ввод еще раз: ");
                value = scanner.nextLine();
                continue;
            }

            System.out.print("Для выхода напишите \"exit\" в консоль или зарегистрируйте новый сенсор: ");
            value = scanner.nextLine();
        }
    }

    private static void postRequest(String url, HttpEntity<?> request) throws HttpClientErrorException {
        final RestTemplate restTemplate = new RestTemplate();
        restTemplate.postForObject(url, request, String.class);
    }

    private static void registerSensor(SensorDTO sensorDTO) throws HttpClientErrorException {
        final String url = "http://localhost:8080/sensors/registration";
        final HttpEntity<SensorDTO> request = new HttpEntity<>(sensorDTO);
        postRequest(url, request);
    }

    private static void measure(SensorDTO sensorDTO) throws HttpClientErrorException {
        final Random random = new Random();
        final String url = "http://localhost:8080/measurements/add";
        final double minTemperature = 0.0;
        final double maxTemperature = 50.0;

        for (int i = 0; i < MEASURE_COUNT; i++) {
            MeasurementDTO measurementDTO = new MeasurementDTO(random.nextDouble() * maxTemperature - minTemperature, random.nextBoolean(), sensorDTO);
            HttpEntity<MeasurementDTO> request = new HttpEntity<>(measurementDTO);
            postRequest(url, request);
        }
    }
}
