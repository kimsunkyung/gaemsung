package com.example.user.gamsung.ImgSearch;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.user.gamsung.R;
import com.example.user.gamsung.vo.ImageSearchVO;

import java.util.ArrayList;
import java.util.List;

public class ImgListAdapter extends RecyclerView.Adapter<ImgListAdapter.ImgListHolder> {
    private View.OnClickListener onClickListener;
    private Context context;
    private List<ImageSearchVO> SubImgData = new ArrayList<ImageSearchVO>();

    public ImgListAdapter(Context context,View.OnClickListener listener) {
        this.context = context;
        this.onClickListener = listener;

    }

    public void setImgData(ArrayList<ImageSearchVO> SubImgData){
        this.SubImgData = SubImgData;
    }


    @Override
    public ImgListHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_img_related, parent, false);
        return new ImgListHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ImgListHolder holder, int position) {

        if(SubImgData.size() == 0){
            return;
        }
        ImageSearchVO item = SubImgData.get(position);
        Log.d("item = ", String.valueOf(item));

        Glide.with(context).load(item.getimg()).into(holder.listImage);
        ((ImgListHolder)holder).image_url.setTag(item.geturl());
        ((ImgListHolder)holder).image_url.setOnClickListener(onClickListener);

    }

    public void setOnItemClickListener(View.OnClickListener onClickListener){
        this.onClickListener = onClickListener;
    }

    @Override
    public int getItemCount() {
        return 3;
    }

    public class ImgListHolder extends RecyclerView.ViewHolder {
        LinearLayout parent;
        ImageView listImage;
        ImageButton image_url;

        public ImgListHolder(View itemView) {
            super(itemView);
            parent = (LinearLayout)itemView.findViewById(R.id.view_parent);
            image_url = (ImageButton) itemView.findViewById(R.id.item_url);
            listImage = (ImageView) itemView.findViewById(R.id.item_image);
        }
        public void setOnItemClick(View.OnClickListener listener){
            parent.setOnClickListener(listener);

        }
    }


}

