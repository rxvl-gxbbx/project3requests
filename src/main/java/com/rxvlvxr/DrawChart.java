package com.rxvlvxr;

import com.rxvlvxr.dto.MeasurementDTO;
import com.rxvlvxr.dto.MeasurementsResponse;
import org.knowm.xchart.QuickChart;
import org.knowm.xchart.SwingWrapper;
import org.knowm.xchart.XYChart;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static com.rxvlvxr.Client.MEASURE_COUNT;

// класс, который строит график
public class DrawChart {
    public static void main(String[] args) {
        createChart(getValues());
    }

    private static List<Double> getValues() {
        final List<Double> lastValues;
        // получаем список объектов DTO с помощью метода getRequest()
        final List<MeasurementDTO> measurements = getRequest().getMeasurements();

        // условия нужны для корретного отображения ПОСЛЕДНИХ измерений
        if (measurements.size() > MEASURE_COUNT) {
            int a = measurements.size() - MEASURE_COUNT;

            // получаем список из последних добавленных строк таблицы
            lastValues = IntStream.range(a, MEASURE_COUNT + a)
                    .mapToObj(i -> measurements.get(i).getValue())
                    .toList();

        } else lastValues = measurements.stream()
                .map(MeasurementDTO::getValue)
                .collect(Collectors.toList());

        return lastValues;
    }

    // строим график
    private static void createChart(List<Double> values) {
        final XYChart chart = QuickChart.getChart("Temperature", "X", "Y", "Data",
                IntStream.range(0, MEASURE_COUNT).boxed().toList(), values);
        new SwingWrapper<>(chart).displayChart();
    }

    // GET запрос для получения объекта MeasurementsResponse
    private static MeasurementsResponse getRequest() {
        final RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject("http://localhost:8080/measurements", MeasurementsResponse.class);
    }
}
