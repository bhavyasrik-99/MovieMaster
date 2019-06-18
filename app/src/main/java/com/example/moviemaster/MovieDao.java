package com.example.moviemaster;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

@Dao
public interface MovieDao {
    @Insert
    public void Insert(MoviePojo moviePojo);
    @Update
    public void update(MoviePojo moviePojo);
    @Delete
    public  void Delete(MoviePojo moviePojo);

    @Query("select *from MoviePojo")
    LiveData<List<MoviePojo>> getAllMovies();
    @Query("select id from MoviePojo where id=:mid")
    int isFavourite(int mid);
}
