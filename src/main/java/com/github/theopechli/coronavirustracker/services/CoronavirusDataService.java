package com.github.theopechli.coronavirustracker.services;

import com.github.theopechli.coronavirustracker.modules.LocationStats;
import com.univocity.parsers.common.processor.RowListProcessor;
import com.univocity.parsers.csv.CsvParser;
import com.univocity.parsers.csv.CsvParserSettings;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.StringReader;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

@Service
public class CoronavirusDataService {

    private final static String COVID_DATA_URL = "https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/csse_covid_19_data/csse_covid_19_time_series/time_series_covid19_confirmed_global.csv";
    private List<LocationStats> allLocationStats = new ArrayList<>();

    public List<LocationStats> getAllLocationStats() {
        return allLocationStats;
    }

    @PostConstruct
    @Scheduled(cron = "* * 1 * * *")
    public void fetchCovidData() throws IOException, InterruptedException {
        List<LocationStats> newLocationStats = new ArrayList<>();

        HttpClient httpClient = HttpClient.newHttpClient();
        HttpRequest httpRequest = HttpRequest.newBuilder()
                .uri(URI.create(COVID_DATA_URL))
                .build();
        HttpResponse<String> httpResponse = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
        StringReader csvBodyReader = new StringReader(httpResponse.body());

        RowListProcessor rowProcessor = new RowListProcessor();

        CsvParserSettings csvParserSettings = new CsvParserSettings();
        csvParserSettings.getFormat().setLineSeparator("\n");
        csvParserSettings.setHeaderExtractionEnabled(true);
        csvParserSettings.setProcessor(rowProcessor);

        CsvParser csvParser = new CsvParser(csvParserSettings);
        csvParser.parse(csvBodyReader);

        List<String[]> rows = rowProcessor.getRows();

        for (String[] row : rows) {
            LocationStats locationStats = new LocationStats();
            int rowLength = row.length;

            locationStats.setProvince(row[0]);
            locationStats.setRegion(row[1]);
            if (row[2] != null) {
                locationStats.setLatitude(Double.parseDouble(row[2]));
            }
            if (row[3] != null) {
                locationStats.setLongitude(Double.parseDouble(row[3]));
            }
            locationStats.setTotalCases(Long.parseLong(row[rowLength - 1]));
            locationStats.setDiffFromPrevDay(Math
                    .abs(locationStats.getTotalCases() - Long.parseLong(row[rowLength - 2])));

            newLocationStats.add(locationStats);
        }

        this.allLocationStats = newLocationStats;
    }

}
