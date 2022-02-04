package kr.petchin.app.ui.talk

import android.content.ContentValues
import android.content.Context
import android.graphics.Rect
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AbsListView
import android.widget.LinearLayout.VERTICAL
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.*
import androidx.recyclerview.widget.RecyclerView.ItemAnimator
import kr.petchin.app.adapter.talk_adpater_recview
import kr.petchin.app.databinding.FragmentTalkBinding
import kr.petchin.app.lib.myclass
import kr.petchin.app.repository.Repository
import kr.petchin.app.repository.RepositoryImpl


/*
class TalkFragment() : BaseFragment<FragmentTalkBinding>(FragmentTalkBinding::inflate) {

        override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
            super.onViewCreated(view, savedInstanceState)

            binding.homeText.text = "Hello view binding"
        }
    }
 */
class TalkFragment : Fragment() {

    private lateinit var talkViewModel: TalkViewModel
    private var _binding: FragmentTalkBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private val talkAdapter by lazy { talk_adpater_recview() }
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
        val talkViewModelFactory = TalkViewModelFactory(repository)
        talkViewModel = ViewModelProvider(this,talkViewModelFactory).get(TalkViewModel::class.java)
        _binding = FragmentTalkBinding.inflate(inflater, container, false)
        val root: View = binding.root

        binding.talkRecView.adapter = talkAdapter


        binding.talkRecView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL,false)
        val decoration = DividerItemDecoration(context, VERTICAL)
        binding.talkRecView.addItemDecoration(decoration)
        binding.talkRecView.addOnScrollListener(scrollListener)

        //binding.talkRecView.setItemViewCacheSize(100)
        //간견조절
        //val spaceDecoration = VerticalSpaceItemDecoration(20)
        //binding.talkRecView.addItemDecoration(spaceDecoration)


        talkViewModel.getList(page)
        talkViewModel._talkResponse.observe(viewLifecycleOwner, Observer {

            talkAdapter.setData(it!!)
            // 한 페이지당 게시물이 10개씩 들어있음.
            // 새로운 게시물이 추가되었다는 것을 알려줌 (추가된 부분만 새로고침)
            talkAdapter.notifyItemRangeInserted((page - 1) * 10, 10)
        })
        talkViewModel._totalCnt.observe(viewLifecycleOwner, Observer {
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
                        //myClass.MessageBox(context,recordCount.toString())
                        page++
                        talkViewModel.getList(page)
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

private fun RecyclerView.setOnScrollChangeListener(scrollListener: RecyclerView.OnScrollListener) {

}
