package kr.petchin.app.ui.friend

import android.graphics.Rect
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import kr.petchin.app.adapter.friend_adpater_recview
import kr.petchin.app.adapter.talk_adpater_recview
import kr.petchin.app.databinding.FragmentFriendBinding
import kr.petchin.app.databinding.FragmentTalkBinding
import kr.petchin.app.lib.myclass
import kr.petchin.app.repository.Repository
import kr.petchin.app.repository.RepositoryImpl
import kr.petchin.app.ui.talk.TalkViewModel
import kr.petchin.app.ui.talk.TalkViewModelFactory

class FriendsFragment : Fragment() {

    private lateinit var friendsViewModel: FriendsViewModel
    private var _binding: FragmentFriendBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private val friendAdapter by lazy { friend_adpater_recview(requireContext()) }
    private var recordCount : Int = 0
    private var page : Int = 1
    private var isLoading : Boolean = false
    private var keyword : String = ""
    private val myClass : myclass = myclass()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //talkViewModel = ViewModelProvider(this,).get(TalkViewModel::class.java)

        val repository : Repository = RepositoryImpl()
        val friendsViewModelFactory = FriendsViewModelFactory(repository)
        friendsViewModel = ViewModelProvider(this,friendsViewModelFactory).get(FriendsViewModel::class.java)
        _binding = FragmentFriendBinding.inflate(inflater, container, false)
        val root: View = binding.root

        binding.friendRecView.adapter = friendAdapter
        binding.friendRecView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL,false)
        val decoration = DividerItemDecoration(context, LinearLayout.VERTICAL)
        binding.friendRecView.addItemDecoration(decoration)
        binding.friendRecView.addOnScrollListener(scrollListener)
        friendAdapter.setItemClickListener(object : friend_adpater_recview.OnItemClickListener {
            override fun onClick(v: View, position: Int) {
                myClass.MessageBox(context,position.toString())
            }
        })
        //binding.talkRecView.setItemViewCacheSize(100)
        //간견조절
        //val spaceDecoration = VerticalSpaceItemDecoration(20)
        //binding.talkRecView.addItemDecoration(spaceDecoration)


        friendsViewModel.getList(page)
        friendsViewModel._friendResponse.observe(viewLifecycleOwner, Observer {

            friendAdapter.setData(it!!)
            // 한 페이지당 게시물이 10개씩 들어있음.
            // 새로운 게시물이 추가되었다는 것을 알려줌 (추가된 부분만 새로고침)
            friendAdapter.notifyItemRangeInserted((page - 1) * 10, 10)
        })
        friendsViewModel._totalCnt.observe(viewLifecycleOwner, Observer {
            recordCount = it
        })
        return root
    }
    /*
    fun setHasStableIds(hasStableIds: Boolean) {
        check(!hasObservers()) { "" }
        mHasStableIds = hasStableIds
    }
    */
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    fun rowClick(){
        myClass.MessageBox(context,"ddd")
    }

    private val scrollListener: RecyclerView.OnScrollListener =
        object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                // 마지막 스크롤된 항목 위치
                val lastVisibleItemPosition = (recyclerView.layoutManager as LinearLayoutManager?)!!.findLastCompletelyVisibleItemPosition()
                // 항목 전체 개수
                val itemTotalCount = recyclerView.adapter!!.itemCount - 1
                if (lastVisibleItemPosition == itemTotalCount) {
                    if(recordCount>0){
                        page++
                        friendsViewModel.getList(page)
                    }
                }

            }
        }




    inner class VerticalSpaceItemDecoration(private val verticalSpaceHeight: Int) :
        RecyclerView.ItemDecoration() {

        override fun getItemOffsets(
            outRect: Rect, view: View, parent: RecyclerView,
            state: RecyclerView.State
        ) {
            outRect.bottom = verticalSpaceHeight
        }
    }
}