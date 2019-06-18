package com.example.moviemaster;

import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TableLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
TextView txt;
ViewPager vp;
TabLayout tableLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        vp=findViewById(R.id.view_pager_main);
        vp.setAdapter(new TabLayAdapter(getSupportFragmentManager()));
        tableLayout=findViewById(R.id.tablay_main);
        tableLayout.setupWithViewPager(vp);

    }

    class TabLayAdapter extends FragmentPagerAdapter{

        public TabLayAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int i) {
            switch (i) {
                case 0:
                    return new PopularFragment();
                case 1:
                    return new TopRatedFragment();

                case 2:
                    return new Favourite_Fragment();


            }
            return null;
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {

            switch (position){
                case 0:
                    return "popular";
                case 1:
                    return "Top Rated";
                case 2:
                    return "Favourites";
            }


            return super.getPageTitle(position);
        }

        @Override
        public int getCount() {
            return 3;
        }
    }
}
