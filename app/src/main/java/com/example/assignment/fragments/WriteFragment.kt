package com.example.assignment.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.assignment.MainActivity
import com.example.assignment.R
import com.example.assignment.databinding.CommonFragmentBinding

class WriteFragment: Fragment() {


    private lateinit var binding: CommonFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.common_fragment, container, false)

        binding = DataBindingUtil.inflate(inflater,R.layout.common_fragment,container,false)


        binding.fragmentName.text="Write Fragment"


        return binding.root

    }

    override fun onResume() {
        super.onResume()
        (activity as MainActivity?)!!.writeFragmentFunction()
    }

}