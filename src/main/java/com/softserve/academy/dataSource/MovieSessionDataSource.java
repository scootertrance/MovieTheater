package com.softserve.academy.dataSource;

import com.softserve.academy.model.Movie;
import com.softserve.academy.model.cinema.util.RoomException;
import com.softserve.academy.model.client.Client;
import com.softserve.academy.model.movieSession.MovieSession;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class MovieSessionDataSource{
    private ArrayList<MovieSession> movieSessionsList = new ArrayList<>();
    private MovieDataSource movieDataSource = new MovieDataSource();
    private RoomDataSource roomDataSource = new RoomDataSource();

    public MovieSessionDataSource() throws RoomException {
        initialMovieSessionData();
    }

    public void initialMovieSessionData() throws RoomException {


        movieSessionsList.add(new MovieSession(1, movieDataSource.getMovieById(1),
                roomDataSource.getRoomByRoomNumber(1), LocalDateTime.of(2023,1,1,10,0),
                250));
        movieSessionsList.add(new MovieSession(2, movieDataSource.getMovieById(2),
                roomDataSource.getRoomByRoomNumber(2), LocalDateTime.of(2023,1,1,12,0),
                250));
        movieSessionsList.add(new MovieSession(3, movieDataSource.getMovieById(3),
                roomDataSource.getRoomByRoomNumber(3), LocalDateTime.of(2023,1,1,14,0),
                250));
    }
    public MovieSession getMovieSessionById(int id) {
        for(MovieSession movieSession : movieSessionsList){
            if(movieSession.getId() == id) {
                return movieSession;
            }
        }
        return null;
    }
}
