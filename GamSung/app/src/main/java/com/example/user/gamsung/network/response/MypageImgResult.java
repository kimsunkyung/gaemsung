package com.example.user.gamsung.network.response;

        import com.example.user.gamsung.vo.ImageSearchVO;
        import com.example.user.gamsung.vo.MypageImgVO;
        import com.google.gson.annotations.SerializedName;

        import java.util.ArrayList;

public class MypageImgResult {

    @SerializedName("mypage")
    public ArrayList<MypageImgVO> mypageImginfo = new ArrayList<MypageImgVO>();

    public ArrayList<MypageImgVO> getMypageImginfo() {
        return mypageImginfo;
    }

    public void setMypageImginfo(ArrayList<MypageImgVO> mypageImginfo) {
        this.mypageImginfo = mypageImginfo;
    }

}


