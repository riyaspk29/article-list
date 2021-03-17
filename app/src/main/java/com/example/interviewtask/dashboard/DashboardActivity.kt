package com.example.interviewtask.dashboard

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.example.interviewtask.R
import com.example.interviewtask.dashboard.adapter.ArticleAdapter
import com.example.interviewtask.dashboard.model.Article
import com.example.interviewtask.dashboard.slider.SlidePageFragment
import com.example.interviewtask.databinding.ActivityDashbordBinding

class DashboardActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding: ActivityDashbordBinding = DataBindingUtil.setContentView(
            this, R.layout.activity_dashbord
        )

        val dashboardViewModel = ViewModelProvider(this).get(DashboardViewModel::class.java)
        binding.dashboardViewModel = dashboardViewModel

        val pagerAdapter = SlidePagerAdapter(this)
        binding.viewpager.adapter = pagerAdapter

        val adapter = ArticleAdapter()
        binding.articleList.adapter = adapter

        binding.lifecycleOwner = this

        dashboardViewModel.articles.observe(this, Observer {
            it?.let {
                adapter.data = it
            }
        })

        dashboardViewModel.topStories.observe(this, Observer {
            it?.let {
                pagerAdapter.topStories = it
            }
        })

        dashboardViewModel.page.observe(this, Observer {
            it?.let {
                binding.viewpager.currentItem = it
            }
        })

        binding.viewpager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                dashboardViewModel.updatePage(position)
            }
        })
    }


    private inner class SlidePagerAdapter(fa: FragmentActivity) : FragmentStateAdapter(fa) {

        var topStories = listOf<Article>()
            set(value) {
                field = value
                notifyDataSetChanged()
            }

        override fun getItemCount(): Int = topStories.size

        override fun createFragment(position: Int): Fragment = SlidePageFragment.newInstance(position,topStories[position])
    }
}