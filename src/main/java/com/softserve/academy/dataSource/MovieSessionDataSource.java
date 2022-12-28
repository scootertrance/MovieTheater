package com.softserve.academy.dataSource;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JSR310Module;
import com.softserve.academy.model.cinema.util.RoomException;
import com.softserve.academy.model.movieSession.MovieSession;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class MovieSessionDataSource{
    private ArrayList<MovieSession> movieSessionsList = new ArrayList<>();
    private MovieDataSource movieDataSource = new MovieDataSource();
    private RoomDataSource roomDataSource = new RoomDataSource();

    public MovieSessionDataSource() throws RoomException, IOException {
        initialMovieSessionData();
        updateMovieSessionJSON();
    }

    public void initialMovieSessionData() throws RoomException, IOException {

        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JSR310Module());
        movieSessionsList = mapper.readValue(new File(
                        "src/main/java/com/softserve/academy/resources/movieSessionsList.json"),
                new TypeReference<>() {});
    }

    public void addMovieSessions(){
        movieSessionsList.add(new MovieSession(1, movieDataSource.getMovieById(1),
                roomDataSource.getRoomByRoomNumber(1), LocalDateTime.of(2023,1,1,10,0),
                250));
        movieSessionsList.add(new MovieSession(2, movieDataSource.getMovieById(2),
                roomDataSource.getRoomByRoomNumber(2), LocalDateTime.of(2023,1,1,12,0),
                250));
        movieSessionsList.add(new MovieSession(3, movieDataSource.getMovieById(3),
                roomDataSource.getRoomByRoomNumber(3), LocalDateTime.of(2023,1,1,14,0),
                250));
        movieSessionsList.add(new MovieSession(4, movieDataSource.getMovieById(3),
                roomDataSource.getRoomByRoomNumber(3), LocalDateTime.of(2023,1,1,17,0),
                250));
    }

    public void updateMovieSessionJSON() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JSR310Module());
        mapper.writeValue(new File(
                        "src/main/java/com/softserve/academy/resources/movieSessionsList.json"),
                movieSessionsList);
    }

    public ArrayList<MovieSession> getMovieSessionsList() {
        return movieSessionsList;
    }

    public MovieDataSource getMovieDataSource() {
        return movieDataSource;
    }

    public RoomDataSource getRoomDataSource() {
        return roomDataSource;
    }

    public MovieSession getMovieSessionById(int id) {
        for(MovieSession movieSession : movieSessionsList){
            if(movieSession.getId() == id) {
                return movieSession;
            }
        }
        return null;
    }

    public ArrayList<MovieSession> getMovieSessionByMovieId(int id) {
        ArrayList<MovieSession> movieSessionByIdList = new ArrayList<>();
        for(MovieSession movieSession : movieSessionsList) {
            if(movieSession.getMovie().getId() == id) {
                movieSessionByIdList.add(movieSession);
            }
        }
        return movieSessionByIdList;
    }
}
