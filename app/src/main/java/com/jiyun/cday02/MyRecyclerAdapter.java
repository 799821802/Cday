package com.jiyun.cday02;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jiyun.cday02.modle.Bean;

import java.util.List;

/**
 * Created by lenovo on 2017/6/9.
 */

public class MyRecyclerAdapter extends RecyclerView.Adapter  {

    private Context context;
    private List<Bean.MoviesBean> list;
    private CallBack callBack;

    public MyRecyclerAdapter(Context context, List<Bean.MoviesBean> list,CallBack callBack) {
        this.context = context;
        this.list = list;
        this.callBack = callBack;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.item_recycler_movies,null);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        ViewHolder holder1 = (ViewHolder) holder;


        holder1.movie_title.setText(list.get(position).getTitleCn());
        holder1.movie_time.setText(list.get(position).getRMonth()+"月"+list.get(position).getRDay()+"日上映");
        holder1.movie_info.setText("今日"+list.get(position).getNearestShowtime().getNearestCinemaCount()+"家影院上映"+list.get(position).getNearestShowtime().getNearestShowtimeCount()+"场");
        holder1.movie_commonSpecial.setText(list.get(position).getCommonSpecial());
        Glide.with(context).load(list.get(position).getImg()).into(holder1.move_img);



    }

    @Override
    public int getItemCount() {
        return list.size();
    }



    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private ImageView move_img;
        private TextView movie_title,movie_time,movie_info,movie_commonSpecial;
        private Button buy_ticket,bt_moviesinfo;


        public ViewHolder(View itemView) {
            super(itemView);

            move_img = (ImageView) itemView.findViewById(R.id.item_recycler_img);
            movie_title = (TextView) itemView.findViewById(R.id.item_recycler_title);
            movie_time = (TextView) itemView.findViewById(R.id.item_recycler_time);
            movie_info = (TextView) itemView.findViewById(R.id.item_recycler_info);
            movie_commonSpecial = (TextView) itemView.findViewById(R.id.item_recycler_commonSpecial);
            buy_ticket = (Button) itemView.findViewById(R.id.item_recycler_buy_ticket);
            bt_moviesinfo = (Button) itemView.findViewById(R.id.item_recycler_bt_moviesinfo);

            buy_ticket.setOnClickListener(this);
            bt_moviesinfo.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.item_recycler_buy_ticket:

                    if(callBack != null){
                        callBack.buy_ticket(getAdapterPosition());
                    }

                    break;
                case R.id.item_recycler_bt_moviesinfo:

                    if(callBack != null){
                        callBack.movies_info(getAdapterPosition());
                    }

                    break;
            }

        }
    }

    public interface CallBack {
        void buy_ticket(int position);
        void movies_info(int position);
    }
}
