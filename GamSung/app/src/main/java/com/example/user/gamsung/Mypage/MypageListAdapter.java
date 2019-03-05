package com.example.user.gamsung.Mypage;

import android.content.Context;
import android.media.Image;
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
import com.example.user.gamsung.ImgSearch.ImgListAdapter;
import com.example.user.gamsung.R;
import com.example.user.gamsung.vo.ImageSearchVO;
import com.example.user.gamsung.vo.MypageImgVO;

import java.util.ArrayList;
import java.util.List;

public class MypageListAdapter extends RecyclerView.Adapter<MypageListAdapter.MypageImgListHolder> {
    private View.OnClickListener onClickListener;
    private Context context;
    private List<MypageImgVO> ImgData = new ArrayList<MypageImgVO>();

    public MypageListAdapter(Context context,View.OnClickListener listener) {
        this.context = context;
        this.onClickListener = listener;

    }
    public void setImgData(ArrayList<MypageImgVO> ImgData){
        this.ImgData = ImgData;
    }


    @Override
    public MypageListAdapter.MypageImgListHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.mypage_img_related, parent, false);
        return new MypageListAdapter.MypageImgListHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MypageListAdapter.MypageImgListHolder holder, int position) {

        if(ImgData.size() == 0){
            return;
        }
        Log.d("ImgData", String.valueOf(ImgData));
        if(position>ImgData.size()){}
        MypageImgVO item = ImgData.get(position);
        ((MypageImgListHolder)holder).date.setText(item.getDate().split("T")[0]);
        Glide.with(context).load(item.getMain_img()).into(holder.mainImage);
        Glide.with(context).load(item.getSub_img_info().get(0).getimg()).into(holder.subImage1);
        Glide.with(context).load(item.getSub_img_info().get(1).getimg()).into(holder.subImage2);
        Glide.with(context).load(item.getSub_img_info().get(2).getimg()).into(holder.subImage3);


    }

    public void setOnItemClickListener(View.OnClickListener onClickListener){
        this.onClickListener = onClickListener;
    }

    @Override
    public int getItemCount() {
        return ImgData.size();
    }

    public class MypageImgListHolder extends RecyclerView.ViewHolder {
        LinearLayout parent;
        TextView date;
        ImageView mainImage;
        ImageView subImage1,subImage2,subImage3,sub_url1,sub_url2,sub_url3;

        public MypageImgListHolder(View itemView) {
            super(itemView);
            parent = (LinearLayout)itemView.findViewById(R.id.mypage_parent);
            date = (TextView)itemView.findViewById(R.id.date);
            mainImage = (ImageView) itemView.findViewById(R.id.main_img);
            subImage1 = (ImageView) itemView.findViewById(R.id.sub_img1);
            subImage2 = (ImageView) itemView.findViewById(R.id.sub_img2);
            subImage3 = (ImageView) itemView.findViewById(R.id.sub_img3);

        }
        public void setOnItemClick(View.OnClickListener listener){
            parent.setOnClickListener(listener);
        }
    }


}

