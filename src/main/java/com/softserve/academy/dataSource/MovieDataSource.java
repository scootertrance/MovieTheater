package com.softserve.academy.dataSource;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JSR310Module;
import com.softserve.academy.model.Movie;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MovieDataSource {
    private ArrayList<Movie> movieList = new ArrayList<>();

    public MovieDataSource() throws IOException {
        initialMovieData();
    }

    public void initialMovieData() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JSR310Module());
        movieList = mapper.readValue(new File(
                        "src/main/java/com/softserve/academy/resources/movieList.json"),
                new TypeReference<>() {});

    }
    public void addMovies(){
        movieList.add(new Movie(1,"Godfather", List.of("Drama"),"Crime drama",
                List.of("Francis Ford Coppola"), List.of("Marlon Brando", "Al Pacino"), 9,175 ));
        movieList.add(new Movie(2, "The Shawshank Redemption", List.of("Drama"), "Crime Drama",
                List.of("Frank Darabont"), List.of("Tim Robbins", "Morgan Freeman"), 9, 142));
        movieList.add(new Movie(3, "The Dark Knight", List.of("Action", "Crime", "Drama", "Thriller"),
                "When the menace known as the Joker wreaks havoc and chaos on the people of Gotham," +
                        " Batman must accept one of the greatest psychological and physical tests of his ability to " +
                        "fight injustice.",List.of("Christopher Nolan"),List.of("Christian Bale", "Heath Ledger"),
                9, 152));
    }


    public void updateMoviesJSON() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JSR310Module());
        mapper.writeValue(new File(
                        "src/main/java/com/softserve/academy/resources/movieList.json"),
                movieList);
    }

    public Movie getMovieById(int id) {
        for(Movie movie : movieList){
            if(movie.getId() == id) {
                return movie;
            }
        }
        return null;
    }
    public ArrayList<Movie> getMovies() {return movieList;}
}
