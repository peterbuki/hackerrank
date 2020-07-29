package com.peterbuki.hackerrank;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Arrays;
import java.util.Scanner;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.stream.Collectors;

public class MovieTitles {

    public static final String METHOD = "GET";
    public static final String TOTAL_PAGES_TAG = "total_pages";
    public static final String DATA_TAG = "data";
    private static final String TITLE_TAG = "Title";
    private final String SERVICE_URL = "https://jsonmock.hackerrank.com/api/movies/search/?Title=%s&page=%d";

    public static void main(String[] args) {
        MovieTitles movieTitles = new MovieTitles();

        Scanner scanner = new Scanner(System.in);
        scanner.useDelimiter(System.getProperty("line.separator"));
        while (true) {
            System.out.print("Movie name: ");
            if (! scanner.hasNextLine()) {
                break;
            }
            String searchString = scanner.nextLine();
            String[] movies = movieTitles.getMovieTitles(searchString);
            Arrays.stream(movies).forEach(title -> System.out.println(String.format("Title: %s", title)));

            System.out.println(String.format("%d movies fetched.",movies.length));
        }

    }

    private String[] getMovieTitles(String substring) {
        System.out.println("Fetching movies " + substring);
        int numberOfPages = 1;
        SortedSet<String> listOfMovies = new TreeSet<>();

        for (int currentPageNumber = 1; currentPageNumber <= numberOfPages; currentPageNumber++) {
            String jsonResponse = fetchPage(substring, currentPageNumber);
            if (!jsonResponse.isEmpty()) {
                JsonObject responseObject = new Gson().fromJson(
                        jsonResponse,
                        JsonObject.class
                );
                numberOfPages = responseObject.get(TOTAL_PAGES_TAG).getAsInt();
                for (JsonElement jsonElement : responseObject.getAsJsonArray(DATA_TAG)) {
                    String title = jsonElement.getAsJsonObject().get(TITLE_TAG).getAsString();
                    listOfMovies.add(title);
                }
            }
        }
        String[] titlesArray = new String[listOfMovies.size()];
        listOfMovies.toArray(titlesArray);
        return titlesArray;
    }

    private String fetchPage(String substring, int pageNumber) {
        try {
            String serviceUrl = String.format(SERVICE_URL, substring, pageNumber);
            HttpURLConnection connection = (HttpsURLConnection) new URL(serviceUrl).openConnection();
            connection.setInstanceFollowRedirects(true);
            connection.setRequestMethod(METHOD);
            if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                return new BufferedReader(
                        new InputStreamReader(connection.getInputStream()))
                        .lines()
                        .collect(Collectors.joining());
            }
        } catch (IOException ignored) {
        }
        return "";
    }

}
