package com.example.user.gamsung.Home;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.user.gamsung.R;

public class HomeListAdapter extends RecyclerView.Adapter<HomeListAdapter.HomeListHolder> {

    private Context context;


    public HomeListAdapter(Context context) {
        this.context = context;

    }
    @Override
    public HomeListAdapter.HomeListHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_home_imglist, parent, false);
        return new HomeListAdapter.HomeListHolder(itemView);
    }

    @Override
    public void onBindViewHolder(HomeListAdapter.HomeListHolder holder, int position) {

        if(position == 0){
            holder.banner.setImageResource(R.drawable.fst_theme);
            holder.firstImage.setImageResource(R.drawable.first_image);
            holder.firstletter.setImageResource(R.drawable.fst_letter);
            holder.secondImage.setImageResource(R.drawable.second_image);
            holder.secondletter.setImageResource(R.drawable.second_letter);
            holder.thirdImage.setImageResource(R.drawable.first_image);
            holder.thirdletter.setImageResource(R.drawable.third_letter);
        }
        else if(position == 1) {
            holder.banner.setImageResource(R.drawable.second_theme);
            holder.firstImage.setImageResource(R.drawable.sst_image);
            holder.firstletter.setImageResource(R.drawable.sst_letter);
            holder.secondImage.setImageResource(R.drawable.snd_image);
            holder.secondletter.setImageResource(R.drawable.snd_letter);
            holder.thirdImage.setImageResource(R.drawable.srd_image);
            holder.thirdletter.setImageResource(R.drawable.srd_letter);
        }
        else if(position == 2) {
            holder.banner.setImageResource(R.drawable.third_theme);
            holder.firstImage.setImageResource(R.drawable.third_first_image);
            holder.firstletter.setImageResource(R.drawable.third_fst_letter);
            holder.secondImage.setImageResource(R.drawable.third_send_image);
            holder.secondletter.setImageResource(R.drawable.third_snd_letter);
            holder.thirdImage.setImageResource(R.drawable.third_thrd_image);
            holder.thirdletter.setImageResource(R.drawable.third_trd_letter);
        }
    }

    @Override
    public int getItemCount() {
        return 3;
    }

    public class HomeListHolder extends RecyclerView.ViewHolder {
        ImageView banner;
        ImageView firstImage;
        ImageView firstletter;
        ImageView secondletter;
        ImageView secondImage;
        ImageView thirdImage;
        ImageView thirdletter;

        public HomeListHolder(View itemView) {
            super(itemView);

            banner = (ImageView) itemView.findViewById(R.id.first_banner);
            firstImage = (ImageView) itemView.findViewById(R.id.first_home_img);
            firstletter = (ImageView) itemView.findViewById(R.id.first_home_letter);
            secondletter = (ImageView) itemView.findViewById(R.id.second_home_letter);
            secondImage = (ImageView) itemView.findViewById(R.id.first_home_img);
            thirdImage = (ImageView) itemView.findViewById(R.id.third_home_img);
            thirdletter = (ImageView) itemView.findViewById(R.id.third_home_letter);



        }
    }


}

