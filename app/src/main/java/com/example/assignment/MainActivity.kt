package com.example.assignment


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager

import com.example.assignment.databinding.ActivityMainBinding
import com.example.assignment.utils.BaseUtils
import com.example.assignment.utils.BaseUtils.Companion.unSelectLayoutIcon


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding;
    private  lateinit var  adapter:FragmentAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        BaseUtils.makeStatusBarTransparent(this)
    //setContentView(R.layout.activity_main)

        adapter = FragmentAdapter(supportFragmentManager, lifecycle)
        binding.viewPager.adapter = adapter
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        binding.viewPager.isUserInputEnabled= false;
    }

    override fun onResume() {
        super.onResume()

        binding.homeFragment.setOnClickListener(){
            when {
                binding.viewPager.currentItem != 0 -> {
                    binding.viewPager.currentItem = 0
                }
            }
        }

        binding.exploreFragment.setOnClickListener(){
            when {
                binding.viewPager.currentItem != 1 -> {
                    binding.viewPager.currentItem = 1
                }
            }
        }

        binding.writeFragment.setOnClickListener(){
            when {
                binding.viewPager.currentItem != 2 -> {
                    binding.viewPager.currentItem = 2
                }
            }
        }

        binding.likeFragment.setOnClickListener(){
            when {
                binding.viewPager.currentItem != 3 -> {
                    binding.viewPager.currentItem = 3
                }
            }

        }

        binding.accountFragment.setOnClickListener(){
            when {
                binding.viewPager.currentItem != 4 -> {
                    binding.viewPager.currentItem = 4
                }
            }
        }

    }


    fun homeFragmentFunction(){
        unselectAllFragment()
        BaseUtils.selectLayoutIcon(this, binding.homeFragment)
    }

    fun exploreFragmentFunction(){
        unselectAllFragment()
        BaseUtils.selectLayoutIcon(this, binding.exploreFragment)
    }

    fun writeFragmentFunction(){
        unselectAllFragment()
        BaseUtils.selectLayoutIcon(this, binding.writeFragment)
    }

    fun likeFragmentFunction(){
        unselectAllFragment()
        BaseUtils.selectLayoutIcon(this, binding.likeFragment)
    }

    fun accountPlanFunction(){
        unselectAllFragment()
        BaseUtils.selectLayoutIcon(this, binding.accountFragment)
    }



    private fun unselectAllFragment() {
        unSelectLayoutIcon(this, binding.homeFragment)
        unSelectLayoutIcon(this, binding.exploreFragment)
        unSelectLayoutIcon(this, binding.writeFragment)
        unSelectLayoutIcon(this, binding.likeFragment)
        unSelectLayoutIcon(this, binding.accountFragment)
    }

}