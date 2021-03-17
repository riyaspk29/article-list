package com.example.interviewtask.dashboard.slider

import android.R.attr.data
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.interviewtask.R
import com.example.interviewtask.dashboard.model.Article
import com.example.interviewtask.databinding.FragmentSlideBinding


class SlidePageFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding:FragmentSlideBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_slide, container, false
        )
        val root: View = binding.root

        var page:Int = arguments?.getInt("someInt") as Int
        var article:Article = arguments?.getSerializable("someTitle") as Article

        val viewModelFactory = SlidePageFragmentViewModelFactory(page, article)
        val slidePageFragmentViewModel = ViewModelProvider(this, viewModelFactory).get(SlidePageFragmentViewModel::class.java)

        binding.slidePageFragmentViewModel = slidePageFragmentViewModel
        binding.lifecycleOwner = this
        

        return root
    }

    companion object {

        fun newInstance(page: Int, article: Article): Fragment {
            val fragmentFirst = SlidePageFragment()
            val args = Bundle()
            args.putInt("someInt", page)
            args.putSerializable("someTitle", article)
            fragmentFirst.arguments = args
            return fragmentFirst
        }
    }
}