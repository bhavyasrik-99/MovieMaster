package com.example.moviemaster;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import java.util.List;

class MovieViewHolder extends AndroidViewModel {
    LiveData<List<MoviePojo>> getAllMovie;
    public MovieRepository movieRepository;
    public MovieViewHolder(@NonNull Application application) {
        super(application);
        movieRepository=new MovieRepository(application);
        getAllMovie=movieRepository.getAll();

    }
    public int favourites(int mld)
    {
        return  movieRepository.checkMovie(mld);
    }
    public void deleteModel(MoviePojo moviePojo){
        movieRepository.delete(moviePojo);
    }
    public void insertModel(MoviePojo moviePojo){
        movieRepository.insert(moviePojo);
    }
}
