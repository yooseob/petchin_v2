package kr.petchin.app.lib

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.databinding.ObservableArrayList
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kr.petchin.app.R
import kr.petchin.app.adapter.talk_adpater_recview
import kr.petchin.app.data.TalkListResultResponse

object DatabindingUtils {

    @BindingAdapter("bind_Image")
    @JvmStatic
    fun bindImage(view: ImageView, imageUrl : String){
        Glide.with(view.context)
            .load(imageUrl)
            .placeholder(R.drawable.noimg)  //로딩중
            .error(R.drawable.noimg)        //오류
            .fallback(R.drawable.noimg)     //url오류
            .into(view)
    }

    @BindingAdapter("bind_text")
    @JvmStatic
    fun bindText(textView: TextView, id:Long){
        textView.setText(id.toString())
    }
}