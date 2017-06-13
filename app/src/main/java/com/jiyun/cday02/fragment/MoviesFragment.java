package com.jiyun.cday02.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.gson.Gson;
import com.jiyun.cday02.MyRecyclerAdapter;
import com.jiyun.cday02.R;
import com.jiyun.cday02.modle.Bean;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by lenovo on 2017/6/9.
 */

public class MoviesFragment extends Fragment implements MyRecyclerAdapter.CallBack {

    private RecyclerView recyclerView;
    private List<Bean.MoviesBean> list = new ArrayList<Bean.MoviesBean>();
    private MyRecyclerAdapter adapter;
    private List<Bean.MoviesBean> movies;
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if(msg.what == 88 ){
                adapter.notifyDataSetChanged();
            }
        }
    };


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.movies_fragment,null);
        recyclerView = (RecyclerView) view.findViewById(R.id.movies_recyclerview);
        show();
        RecyclerView.LayoutManager manager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(manager);

        adapter = new MyRecyclerAdapter(getActivity(),list,this);
        recyclerView.setAdapter(adapter);


        return view;
    }

    private void show() {

        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url("https://api-m.mtime.cn/PageSubArea/HotPlayMovies.api?locationId=290").build();
        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                String s = response.body().string();
                Log.e("TAG",s);
                Gson gson = new Gson();
                Bean bean = gson.fromJson(s, Bean.class);
                movies = bean.getMovies();

                list.addAll(movies);
                Log.e("TAG",list.size()+"");
                handler.sendEmptyMessage(88);
            }
        });
    }


    @Override
    public void buy_ticket(int position) {
        Toast.makeText(getActivity(),position+"",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void movies_info(int position) {

        AlertDialog.Builder dialog = new AlertDialog.Builder(getContext());
        dialog.setTitle(list.get(position).getTitleCn());
        dialog.setMessage(list.get(position).getCommonSpecial());
        dialog.create();
        dialog.show();

    }
}
