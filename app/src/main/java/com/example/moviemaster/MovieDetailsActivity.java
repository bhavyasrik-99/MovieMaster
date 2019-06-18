package com.example.moviemaster;

import android.arch.lifecycle.ViewModelProviders;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;

public class MovieDetailsActivity extends AppCompatActivity {

    ArrayList<Trailer> trmodel;
    ArrayList<Review>rvmodel;
    Button b;
    String[]str;
    RecyclerView trc,rrc;
    TextView titl,id2;
    TextView avg;
    TextView over,releas;
    ImageView img;
    String json_data_here = null;
    Trailer trm;
    Review rvm;
    MovieViewHolder mvh;
    static  boolean stat=false;
    String key;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);
        rrc=findViewById(R.id.reviewrcview_detail);
        titl=findViewById(R.id.title);
        trc=findViewById(R.id.trailerrcview_detail);
         img=findViewById(R.id.image_detail);
          id2=findViewById(R.id.mid);
           avg=findViewById(R.id.vote_avg);
            b=findViewById(R.id.favbutton);
             releas=findViewById(R.id.rele_date);
              over=findViewById(R.id.overview_field);
        str=getIntent().getStringArrayExtra("hai");
        Picasso.with(this).load("http://image.tmdb.org/t/p/w185/"+str[0]).into(img);
        titl.setText(str[1]);
        id2.setText(str[2]);
        avg.setText(str[3]);
        over.setText(str[4]);
        releas.setText(str[5]);
        mvh= ViewModelProviders.of(this).get(MovieViewHolder.class);
        inFav(str[2]);
        String t="https://api.themoviedb.org/3/movie/"+str[2]+"/videos?api_key=8f1cc455e6f99457502414adc2f218d5";
        String r="https://api.themoviedb.org/3/movie/"+str[2]+"/reviews?api_key=8f1cc455e6f99457502414adc2f218d5";
        rvmodel=new ArrayList<>();
        trmodel=new ArrayList<>();
        new RvTask().execute(r);
        new TrTask().execute(t);
        rrc.setLayoutManager(new LinearLayoutManager(this));
            trc.setLayoutManager(new LinearLayoutManager(this));
    }

    public void favourite(View view) {
        MoviePojo mp;
        mp=new MoviePojo();
        boolean p=inFav(str[2]);
        mp.setId(str[2]);
        if (!p){
            mp.setAvg(str[3]);
            mp.setOver(str[4]);
            mp.setTitle(str[1]);
            mp.setRelease(str[5]);
            mp.setImage(str[0]);
            mvh.insertModel(mp);
            setStats(true);
            Toast.makeText(this, "inserted", Toast.LENGTH_SHORT).show();
       b.setText("add to favorites");
        }else{
            mvh.deleteModel(mp);setStats(false);
            Toast.makeText(this, "deleted", Toast.LENGTH_SHORT).show();
       b.setText("Add to favorites");
        }


    }public boolean inFav(String id){
        int mid=mvh.favourites(Integer.parseInt(id));
        if (mid>0){setStats(true);b.setText("Added to favourites");
    }else {
            setStats(false);
            b.setText("Add to favourites");
        }
        return  stat;}
        public static void setStats(boolean stat){
        MovieDetailsActivity.stat=stat;
        }
    class  TrTask extends  AsyncTask<String,Void,String> {

        @Override
        protected String doInBackground(String... strings) {
            try {
                URL url;
                url = new URL(strings[0]);
                HttpURLConnection conn;

                conn = (HttpURLConnection) url.openConnection();
                conn.connect();
                InputStream is = conn.getInputStream();
                Scanner sc;
                sc = new Scanner(is);
                sc.useDelimiter("\\A");
                if (sc.hasNext()) {
                    return sc.next();
                } else
                    return null;
            } catch (
                    IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            if (s != null) {
                try {
                    JSONObject jb;
                    jb = new JSONObject(s);
                    JSONArray results;
                    results = jb.getJSONArray("results");
                    for (int a = 0; a < results.length(); a++) {
                        JSONObject ob = results.getJSONObject(a);
                        key = ob.getString("key");
                        trm = new Trailer(key);
                        trmodel.add(trm);

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                trc.setAdapter(new TrailerAdapter(trmodel, MovieDetailsActivity.this));
            }
        }
    }
                class RvTask extends AsyncTask<String,Void,String>{

                    @Override
                    protected String doInBackground(String... strings) {
                        try {
                            URL url;
                            url = new URL(strings[0]);
                            HttpURLConnection conn;

                            conn = (HttpURLConnection) url.openConnection();
                            conn.connect();
                            InputStream is = conn.getInputStream();
                            Scanner sc;
                            sc = new Scanner(is);
                            sc.useDelimiter("\\A");
                            if (sc.hasNext()) {
                                return sc.next();
                            } else
                                return null;
                        } catch (
                                IOException e) {
                            e.printStackTrace();
                        }
                        return null;
                    }

                    @Override
                    protected void onPostExecute(String s) {
                        super.onPostExecute(s);

                        if (s != null) {
                            try {
                                JSONObject jb;
                                jb = new JSONObject(s);
                                JSONArray results;
                                results = jb.getJSONArray("results");
                                for (int a = 0; a < results.length(); a++) {
                                    JSONObject ob = results.getJSONObject(a);
                                    String author = ob.getString("author");
                                    String comment=ob.getString("content");
                                    rvm = new Review(author,comment);
                                    rvmodel.add(rvm);

                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            rrc.setAdapter(new ReviewAdapter(rvmodel,MovieDetailsActivity.this));
                        }

                    }
                }
}

