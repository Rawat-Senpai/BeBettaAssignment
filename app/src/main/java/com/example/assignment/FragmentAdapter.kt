package com.example.assignment

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.assignment.fragments.*

class FragmentAdapter(
    fragmentManager: FragmentManager,
    lifecycle: Lifecycle

): FragmentStateAdapter(fragmentManager,lifecycle){
    override fun getItemCount(): Int {
        return 5
    }

    override fun createFragment(position: Int): Fragment {
        when(position)
        {
            0->{
                return FeedFragment()
            }

            1->{
                return ExploreFragment()
            }

            2->{
                return WriteFragment()
            }
            3->{
                return LifeFragment()
            }
            4->{
                return AccountFragment()
            }


            else ->{
                return FeedFragment()
            }

        }

    }

}

