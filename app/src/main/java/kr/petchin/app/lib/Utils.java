package kr.petchin.app.lib;

import android.content.Context;
import android.widget.ImageView;

import androidx.swiperefreshlayout.widget.CircularProgressDrawable;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.io.InputStream;
import java.io.OutputStream;


import kr.petchin.app.R;

public class Utils {
    public static void CopyStream(InputStream is, OutputStream os)
    {
        final int buffer_size=1024;
        try
        {
            byte[] bytes=new byte[buffer_size];
            for(;;)
            {
              int count=is.read(bytes, 0, buffer_size);
              if(count==-1)
                  break;
              os.write(bytes, 0, count);
            }
        }
        catch(Exception ex){}
    }

    public static void loadImage(ImageView view, String url, CircularProgressDrawable progressDrawable){
        // 이미지 로드할 때 옵션 설정
        RequestOptions options = new RequestOptions()
                .placeholder(progressDrawable) // 이미지 로딩하는 동안 보여줄 원형 프로그레스
                .error(R.drawable.noimg); // url 로드할 때 error 발생시 보여줄 이미지
        Glide.with(view.getContext())
                .setDefaultRequestOptions(options)
                .load(url)
                .into(view);
    }

    // 이미지 로딩 중에 보여줄 원형 프로그레스 만들기
    public static CircularProgressDrawable getProgressDrawable(Context context){
        CircularProgressDrawable progressDrawable = new CircularProgressDrawable(context);
        progressDrawable.setStrokeWidth(10f);
        progressDrawable.setCenterRadius(50f);
        progressDrawable.start();
        return progressDrawable;
    }
}