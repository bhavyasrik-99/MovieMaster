package com.example.moviemaster;

import android.arch.lifecycle.LiveData;
import android.content.Context;
import android.os.AsyncTask;

import java.util.List;

public class MovieRepository {
    LiveData<List<MoviePojo>> getAll;
    public static MovieDao movieDao;

    public MovieRepository(Context context) {
        MovieDb movieDb = MovieDb.getMovieDb(context);
        movieDao = movieDb.movieDao();
        getAll = movieDao.getAllMovies();

    }

    LiveData<List<MoviePojo>> getAll() {
        return getAll;
    }

    public void insert(MoviePojo moviePojo) {
        new InsertAtask().execute(moviePojo);
    }

    public void delete(MoviePojo moviePojo) {
        new DeleteAtask().execute(moviePojo);
    }

    public class InsertAtask extends AsyncTask<MoviePojo, Void, Void> {

        @Override
        protected Void doInBackground(MoviePojo... moviePojos) {
            movieDao.Insert(moviePojos[0]);
            return null;
        }
    }

    public class DeleteAtask extends AsyncTask<MoviePojo, Void, Void> {
        @Override
        protected Void doInBackground(MoviePojo... moviePojos) {
            movieDao.Delete(moviePojos[0]);

            return null;
        }
    }
    public int checkMovie(int mid){
        return movieDao.isFavourite(mid);
    }
}