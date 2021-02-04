package com.github.theopechli.coronavirustracker.services;

import com.univocity.parsers.common.record.Record;
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
import java.util.List;

@Service
public class CoronavirusDataService {

    private final static String COVID_DATA_URL = "https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/csse_covid_19_data/csse_covid_19_time_series/time_series_covid19_confirmed_global.csv";

    @PostConstruct
    @Scheduled(cron = "* * 1 * * *")
    public void fetchCovidData() throws IOException, InterruptedException {
        HttpClient httpClient = HttpClient.newHttpClient();
        HttpRequest httpRequest = HttpRequest.newBuilder()
                .uri(URI.create(COVID_DATA_URL))
                .build();
        HttpResponse<String> httpResponse = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
        StringReader csvBodyReader = new StringReader(httpResponse.body());

        CsvParserSettings csvParserSettings = new CsvParserSettings();
        csvParserSettings.getFormat().setLineSeparator("\n");
        csvParserSettings.setHeaderExtractionEnabled(true);

        CsvParser csvParser = new CsvParser(csvParserSettings);

        List<Record> allRecords = csvParser.parseAllRecords(csvBodyReader);
        for (Record record: allRecords) {
            String area = record.getString("Province/State");
            System.out.println(area);
        }
    }

}
