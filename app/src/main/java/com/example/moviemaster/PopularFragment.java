package com.example.moviemaster;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.net.ssl.HttpsURLConnection;


public class PopularFragment extends Fragment {
    ProgressBar progressBar;
    RecyclerView recyclerView;
    List<MoviePojo> movielist = null;
    static MoviePojo moviePojo;
    String str = "https://api.themoviedb.org/3/movie/popular?api_key=8f1cc455e6f99457502414adc2f218d5";
    String json_data_here = null;
    public PopularFragment() { }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v;
        v = inflater.inflate(R.layout.fragment_popular, container, false);
        progressBar = v.findViewById(R.id.prog_rotate);
        recyclerView = v.findViewById(R.id.recy_popfrag);
        movielist = new ArrayList<MoviePojo>();
        progressBar.setVisibility(View.GONE);
        if(savedInstanceState!=null && savedInstanceState.containsKey("JSON_DATA")){
            json_data_here = savedInstanceState.getString("JSON_DATA");
            parseJSon(json_data_here);
        }
        else
        {
            new MasyncTask().execute(str);
        }
        return v;
    }


    @SuppressLint("StaticFieldLeak")
    public class MasyncTask extends AsyncTask<String, Void, String> {

    @Override
    protected String doInBackground(String... strings) {
        try{

        URL url;
        url=new URL(str);
        HttpURLConnection conn;

            conn= (HttpURLConnection) url.openConnection();
            conn.connect();
            InputStream is=conn.getInputStream();
            Scanner sc;
            sc=new Scanner(is);
            sc.useDelimiter("\\A");
            if (sc.hasNext()){return sc.next();}
            else
                return null;
        } catch (IOException e) {
            e.printStackTrace();
        }


        return null;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        progressBar.setVisibility(View.GONE);
        json_data_here = s;
        parseJSon(s);
    }
}

    private void parseJSon(String s) {
        if (s!=null){
            JSONObject jb;
            try {
                jb=new JSONObject(s);
                JSONArray results;
                results=jb.getJSONArray("results");
                for(int a=0;a<results.length();a++){
                    JSONObject ob=results.getJSONObject(a);
                    String id=ob.getString("id");
                    String title=ob.getString("title");
                    String image=ob.getString("poster_path");
                    String overview=ob.getString("overview");
                    String vavg=ob.getString("vote_average");
                    String release=ob.getString("release_date");
                    moviePojo=new MoviePojo(image,title,vavg,overview,release,id);
                    movielist.add(moviePojo);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        recyclerView.setAdapter(new MovieAdapter(getActivity(), movielist));
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        if(json_data_here!=null){
            outState.putString("JSON_DATA",json_data_here);
        }
    }
}
