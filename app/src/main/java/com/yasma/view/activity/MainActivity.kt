package com.yasma.view.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import com.yasma.R
import com.yasma.view.adapters.ViewPagerAdapter
import com.yasma.view.fragments.AlbumFragment
import com.yasma.view.fragments.PostFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var viewPagerAdapter: ViewPagerAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupViewPager(viewPagerMainActivity)
        toolbar.title = getString(R.string.main_activity_title)
        tabModeMainActivity.setupWithViewPager(viewPagerMainActivity)
    }

    private fun setupViewPager(viewPager: ViewPager) {
        viewPagerAdapter = ViewPagerAdapter(supportFragmentManager)
        viewPagerAdapter.addFragment(PostFragment(), getString(R.string.post_fragment_title))
        viewPagerAdapter.addFragment(AlbumFragment(), getString(R.string.album_fragment_title))
        viewPager.adapter = viewPagerAdapter
    }
}
