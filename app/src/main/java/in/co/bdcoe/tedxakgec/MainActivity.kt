package `in`.co.bdcoe.tedxakgec

import `in`.co.bdcoe.tedxakgec.Fragments.AboutUsFragment
import `in`.co.bdcoe.tedxakgec.Fragments.HomeFragment
import `in`.co.bdcoe.tedxakgec.Fragments.SpeakersFragment
import `in`.co.bdcoe.tedxakgec.Network.ServiceGenerator
import `in`.co.bdcoe.tedxakgec.Network.TedxAkgecClient
import `in`.co.bdcoe.tedxakgec.POJOs.TedxResult
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.support.design.widget.AppBarLayout
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Toast
import com.aurelhubert.ahbottomnavigation.AHBottomNavigation
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationItem
import com.bdcoe.saksham.Adapters.BottomBarAdapter
import com.flaviofaria.kenburnsview.KenBurnsView
import com.flaviofaria.kenburnsview.Transition
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private val backdrops: ArrayList<Drawable> = ArrayList()
    private var currentBackdrop = 0
    private lateinit var tedxAkgecClient: TedxAkgecClient
    private lateinit var tedxResult:TedxResult

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        tedxAkgecClient = ServiceGenerator.createTedxAkgecService(TedxAkgecClient::class.java)

        supportActionBar?.apply {
            setDisplayShowCustomEnabled(true) // enable overriding the default toolbar layout
            setDisplayShowTitleEnabled(false)
        }

        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN

//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//            window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
//            window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
//            window.statusBarColor = resources.getColor(R.color.colorPrimary)
//        } else window.statusBarColor = resources.getColor(R.color.colorAccent)
//
//        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
//        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
//        window.statusBarColor = resources.getColor(R.color.colorPrimary)
        window.navigationBarColor = resources.getColor(R.color.colorPrimary)

        backdrops.add(resources.getDrawable(R.drawable.akgec_backdrop_one))
        backdrops.add(resources.getDrawable(R.drawable.akgec_backdrop_two))
        if (main_swipe_refresh != null)
            main_swipe_refresh.isRefreshing = true
        initListners()
        getAllData()
//        initializeNavigationBar()
//        initializeViewPager()

    }

    private fun initListners() {
        backdrop_view.setTransitionListener(object : KenBurnsView.TransitionListener {
            override fun onTransitionEnd(transition: Transition?) {
                backdrop_view.setImageDrawable(backdrops[++currentBackdrop % 2])
            }

            override fun onTransitionStart(transition: Transition?) {}
        })
        app_bar.addOnOffsetChangedListener(AppBarLayout.OnOffsetChangedListener { appBarLayout, verticalOffset ->
            toolbar_image.alpha = Math.abs(verticalOffset / appBarLayout.totalScrollRange.toFloat())
        })

//        app_bar.addOnOffsetChangedListener(object : AppBarStateChangeListener() {
//            override fun onStateChanged(appBarLayout: AppBarLayout, state: State) {
//
//                when (state) {
//                    AppBarStateChangeListener.State.COLLAPSED -> toolbar_image.visibility = View.VISIBLE
//                    AppBarStateChangeListener.State.EXPANDED -> toolbar_image.visibility = View.GONE
//                    AppBarStateChangeListener.State.IDLE -> toolbar_image.visibility = View.GONE
//                }
//
//            }
//        })
    }


    private fun initializeViewPager() {
        view_pager.setPagingEnabled(false)
        val pagerAdapter = BottomBarAdapter(supportFragmentManager)

        pagerAdapter.addFragments(HomeFragment.newInstance(tedxResult.data.eventDate))
        pagerAdapter.addFragments(SpeakersFragment.newInstance(tedxResult.data.speakers))
        pagerAdapter.addFragments(AboutUsFragment.newInstance(tedxResult.data.aboutUs[0]))
//        pagerAdapter.addFragments(RegisterFragment())
//        pagerAdapter.addFragments(AboutUsFragment())

        view_pager.adapter = pagerAdapter
        view_pager.currentItem = 0

    }

    private fun initializeNavigationBar() {
        val item0 = AHBottomNavigationItem(resources.getString(R.string.home), resources.getDrawable(R.drawable.ic_home_black_48dp))
        val item1 = AHBottomNavigationItem(resources.getString(R.string.speakers), resources.getDrawable(R.drawable.ic_record_voice_over_black_48dp))
        val item2 = AHBottomNavigationItem(resources.getString(R.string.about_us), resources.getDrawable(R.drawable.ic_info_outline_black_48dp))
//        val item3 = AHBottomNavigationItem(resources.getString(R.string.register), resources.getDrawable(R.drawable.ic_person_add_black_24dp))
//        val item4 = AHBottomNavigationItem(resources.getString(R.string.about_us), resources.getDrawable(R.drawable.ic_info_outline_black_48dp))
        val bottomNavigationBar: AHBottomNavigation = bottom_navigation
        bottomNavigationBar.addItem(item0)
        bottomNavigationBar.addItem(item1)
        bottomNavigationBar.addItem(item2)
//        bottomNavigationBar.addItem(item3)
//        bottomNavigationBar.addItem(item4)

        bottomNavigationBar.defaultBackgroundColor = resources.getColor(R.color.colorPrimary)
        bottomNavigationBar.accentColor = resources.getColor(R.color.colorAccent)
        bottomNavigationBar.inactiveColor = resources.getColor(R.color.secondary_text)
        bottomNavigationBar.isBehaviorTranslationEnabled = true
        bottomNavigationBar.currentItem = 0

        bottomNavigationBar.setOnTabSelectedListener { position, wasSelected ->

            if (!wasSelected)
                view_pager.currentItem = position
//            when (position){
//                0 -> toolbar_layout.title = resources.getString(R.string.news)
//                1 -> toolbar_layout.title = resources.getString(R.string.schedule)
//                2 -> toolbar_layout.title = resources.getString(R.string.saksham)
//                3 -> toolbar_layout.title = resources.getString(R.string.register)
//                4 -> toolbar_layout.title = resources.getString(R.string.about_us)
//            }
            return@setOnTabSelectedListener true
        }


    }

    private fun getAllData() {
        val call = callGetAll()
        call.enqueue(object : Callback<TedxResult> {
            override fun onFailure(call: Call<TedxResult>?, t: Throwable?) {}
            override fun onResponse(call: Call<TedxResult>?, response: Response<TedxResult>?) {
                val tempResult = response?.body()

                if (tempResult != null) {
                    Toast.makeText(this@MainActivity,"Got Data",Toast.LENGTH_LONG).show()
                    tedxResult = tempResult
                    if (main_swipe_refresh != null) {
                        main_swipe_refresh.isRefreshing = false
                        main_swipe_refresh.isEnabled = false
                    }
                    initializeNavigationBar()
                    initializeViewPager()
                } else {
                    Toast.makeText(this@MainActivity,"Crash Data",Toast.LENGTH_LONG).show()

                }
            }
        })

    }
    private fun callGetAll(): Call<TedxResult> = tedxAkgecClient.getAll()


}


