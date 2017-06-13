package com.jiyun.cday02;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.jiyun.cday02.fragment.CinemaFragment;
import com.jiyun.cday02.fragment.MoviesFragment;
import com.jiyun.cday02.fragment.PageAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends FragmentActivity {

    @Bind(R.id.activity_main_movies)
    RadioButton activityMainMovies;
    @Bind(R.id.activity_main_Cinema)
    RadioButton activityMainCinema;
    @Bind(R.id.main_radiogroup)
    RadioGroup mainRadiogroup;
    @Bind(R.id.viewpager)
    ViewPager viewpager;

    private List<Fragment> list = new ArrayList<Fragment>();
    private PagerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);


        MoviesFragment moviesFragment = new MoviesFragment();
        CinemaFragment cinemaFragment = new CinemaFragment();
        list.add(moviesFragment);
        list.add(cinemaFragment);

        adapter = new PageAdapter(getSupportFragmentManager(), list);
        viewpager.setAdapter(adapter);

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();

        ButterKnife.unbind(this);
    }

    @OnClick({R.id.activity_main_movies, R.id.activity_main_Cinema, R.id.main_radiogroup})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.activity_main_movies:

                viewpager.setCurrentItem(0);
                break;
            case R.id.activity_main_Cinema:

                viewpager.setCurrentItem(1);
                break;
            case R.id.main_radiogroup:
                break;
        }
    }
}
