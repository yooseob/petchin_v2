package kr.petchin.app.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kr.petchin.app.data.FriendsResultResponse
import kr.petchin.app.databinding.ItemFriendBinding
import kr.petchin.app.lib.myclass

class friend_adpater_recview(val context: Context) : RecyclerView.Adapter<friend_adpater_recview.MyViewHolder>() {

    private var mList = ArrayList<FriendsResultResponse>()
    private val myClass : myclass = myclass()
    private var mContext : Context? = null

    inner class MyViewHolder(val binding: ItemFriendBinding) : RecyclerView.ViewHolder(binding.root)

    // 어떤 xml 으로 뷰 홀더를 생성할지 지정
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = ItemFriendBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        mContext = parent.getContext();
        return MyViewHolder(binding)
    }

    // 뷰 홀더에 데이터 바인딩
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        val layoutParams = holder.itemView.layoutParams
        layoutParams.height = 400
        holder.itemView.requestLayout()



        var mData : FriendsResultResponse = mList.get(position)


        holder.binding.model = mData
        holder.binding.listViewRowThumbnail.setOnClickListener {
            itemClickListener.onClick(it, position)
        }
        /*
        holder.binding.linearClick?.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                //TODO
                myClass.MessageBox(mContext,"click!->"+mData.idx)
            }
        })


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
    fun setData(newList : List<FriendsResultResponse>){
        //this.mList = newList
        this.mList.addAll(newList)
        // 새로고침
        //notifyDataSetChanged()
    }
    //  리스너 인터페이스
    interface OnItemClickListener {
        fun onClick(v: View, position: Int)
    }
    //  외부에서 클릭 시 이벤트 설정
    fun setItemClickListener(onItemClickListener: Any) {
        this.itemClickListener = onItemClickListener as OnItemClickListener
    }
    //  setItemClickListener로 설정한 함수 실행
    private lateinit var itemClickListener : OnItemClickListener
}