package com.example.moviemaster;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

@Database(entities = {MoviePojo.class},version = 1,exportSchema = false)
public abstract class MovieDb  extends RoomDatabase {
    public abstract  MovieDao movieDao();
    public static MovieDb movieDb;
    public static MovieDb getMovieDb(Context context)
    {
        if (movieDb==null){
            movieDb= Room.databaseBuilder(context,MovieDb.class,"favourite").allowMainThreadQueries().fallbackToDestructiveMigration().build();

        }
        return movieDb;

    }
}
