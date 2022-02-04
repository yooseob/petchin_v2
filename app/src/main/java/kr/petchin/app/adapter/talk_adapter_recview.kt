package kr.petchin.app.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kr.petchin.app.R
import kr.petchin.app.data.TalkListResponse
import kr.petchin.app.data.TalkListResultResponse
import kr.petchin.app.databinding.ItemTalkBinding

class talk_adpater_recview : RecyclerView.Adapter<talk_adpater_recview.MyViewHolder>() {

    private var mList = ArrayList<TalkListResultResponse>()

    class MyViewHolder(val binding: ItemTalkBinding) : RecyclerView.ViewHolder(binding.root)


    // 어떤 xml 으로 뷰 홀더를 생성할지 지정
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = ItemTalkBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return MyViewHolder(binding)
    }

    // 뷰 홀더에 데이터 바인딩
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        val layoutParams = holder.itemView.layoutParams
        layoutParams.height = 400
        holder.itemView.requestLayout()



        var mData : TalkListResultResponse = mList.get(position)

        holder.binding.model = mData

        /*
        holder.binding.listViewRowTitle.text = mData.title
        Glide.with(holder.itemView)
            .load(mData.img)
            .placeholder(R.drawable.noimg)  //로딩중
            .error(R.drawable.noimg)        //오류
            .fallback(R.drawable.noimg)     //url오류
            .into(holder.binding.listViewRowThumbnail)
         */
    }

    // 뷰 홀더의 개수 리턴
    override fun getItemCount(): Int {
        return mList.size
    }
    override fun getItemId(position: Int): Long = position.toLong()


    // 데이터 변경시 리스트 다시 할당
    fun setData(newList : List<TalkListResultResponse>){
        //this.mList = newList
        this.mList.addAll(newList)
        // 새로고침
        //notifyDataSetChanged()
    }


}

