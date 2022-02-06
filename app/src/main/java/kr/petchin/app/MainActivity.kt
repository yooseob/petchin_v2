package kr.petchin.app

import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import kr.petchin.app.data.FriendsResponse
import kr.petchin.app.databinding.ActivityMainBinding
import kr.petchin.app.ui.friend.FriendsFragment
import kr.petchin.app.ui.home.HomeFragment
import kr.petchin.app.ui.myinfo.MyinfoFragment
import kr.petchin.app.ui.talk.TalkFragment

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val TAG_TALK = "navigation_talk"
    private val TAG_FRIENDS = "navigation_friends"
    private val TAG_HOME = "navigation_home"
    private val TAG_MY_PAGE = "navigation_myInfo"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val navView: BottomNavigationView = binding.navView
        setFragment(TAG_HOME, HomeFragment())

        //네비 항목 클릭 시 프래그먼트 변경하는 함수 호출
        navView.setOnItemSelectedListener { item->
            when(item.itemId){
                R.id.navigation_talk -> setFragment(TAG_TALK, TalkFragment())
                R.id.navigation_home -> setFragment(TAG_HOME, HomeFragment())
                R.id.navigation_myInfo -> setFragment(TAG_MY_PAGE, MyinfoFragment())
                R.id.navigation_friends -> setFragment(TAG_FRIENDS,FriendsFragment())
            }
            true
        }

        /*        //프래그먼트 설정
       val resultFragmentId = intent.getIntExtra("selectFragmentId", 0)
       binding.mainNavi.selectedItemId = resultFragmentId
       */

        /*        // jetPack 예제
         val navController = findNavController(R.id.nav_host_fragment_activity_main)

         val appBarConfiguration = AppBarConfiguration(
             setOf(
                 R.id.navigation_home,
                 R.id.navigation_talk,
                 R.id.navigation_friends,
                 R.id.navigation_myInfo
             )
         )
         setupActionBarWithNavController(navController, appBarConfiguration)
         navView.setupWithNavController(navController)
         */
        val actionBar = supportActionBar
        if (actionBar != null) {
            actionBar.hide()
        }
    }

    //프래그먼트 컨트롤 함수
    fun setFragment(tag: String, fragment: Fragment){
        val manager: FragmentManager = supportFragmentManager
        val ft: FragmentTransaction = manager.beginTransaction()

        //트랜잭션에 tag로 전달된 fragment가 없을 경우 add
        if(manager.findFragmentByTag(tag) == null){
            ft.add(R.id.nav_host_fragment_activity_main, fragment, tag)
        }

        //작업이 수월하도록 manager에 add되어있는 fragment들을 변수로 할당해둠
        val myTalk = manager.findFragmentByTag(TAG_TALK)
        val home = manager.findFragmentByTag(TAG_HOME)
        val myPage = manager.findFragmentByTag(TAG_MY_PAGE)
        val myFriend = manager.findFragmentByTag(TAG_FRIENDS)

        //모든 프래그먼트 hide
        if(myTalk!=null){
            ft.hide(myTalk)
        }
        if(home!=null){
            ft.hide(home)
        }
        if(myPage!=null){
            ft.hide(myPage)
        }
        if(myFriend!=null){
            ft.hide(myFriend)
        }

        //선택한 항목에 따라 그에 맞는 프래그먼트만 show
        if(tag == TAG_TALK){
            if(myTalk!=null){
                ft.show(myTalk)
            }
        }
        else if(tag == TAG_HOME){
            if(home!=null){
                ft.show(home)
            }
        }
        else if(tag == TAG_MY_PAGE){
            if(myPage!=null){
                ft.show(myPage)
            }
        }
        else if(tag == TAG_FRIENDS){
            if(myFriend!=null){
                ft.show(myFriend)
            }
        }

        ft.commitAllowingStateLoss()
        //ft.commit()
    }
}