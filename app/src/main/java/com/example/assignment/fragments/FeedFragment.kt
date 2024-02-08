package com.example.assignment.fragments

import android.app.Dialog
import android.content.Context
import android.os.Build
import android.os.Bundle
import android.util.DisplayMetrics
import android.util.Log
import android.view.Gravity
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat.getSystemService
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.assignment.MainActivity
import com.example.assignment.R
import com.example.assignment.adapters.HorizontalLiveMatchAdapter
import com.example.assignment.adapters.HorizontalMostHappenAdapter
import com.example.assignment.adapters.MatchPreviewAdapter
import com.example.assignment.databinding.FragmentFeedBinding
import com.example.assignment.models.MostlyHappenModel
import com.example.assignment.models.SportDataClass
import com.example.assignment.utils.BaseUtils
import com.google.gson.Gson
import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader


class FeedFragment : Fragment() {


    private lateinit var binding: FragmentFeedBinding
    private var footballArrayList =ArrayList<SportDataClass.DataListItem>()
    private var cricketArrayList =ArrayList<SportDataClass.DataListItem>()
    private var mostHappenArrayList =ArrayList<MostlyHappenModel>()
    private lateinit var horizontalAdapter: HorizontalLiveMatchAdapter
    private  var rapidFireArrayList = ArrayList<String>()
    private lateinit var horizontalMostHappenAdapter: HorizontalMostHappenAdapter
    private lateinit var matchReviewAdapter:MatchPreviewAdapter
    private var matchReviewList=ArrayList<Int>()
    var questionCount =0 ;



    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_feed, container, false)

        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_feed,container,false)



        init()

        return binding.root




    }

    @RequiresApi(Build.VERSION_CODES.M)
    private fun  init(){

        binding.llFootball.setOnClickListener(){
            BaseUtils.makeItemSelected(requireContext(),binding.ivFootball,binding.tvFootball,binding.llFootball)
            BaseUtils.makeItemNotSelected(requireContext(),binding.ivCricket,binding.tvCricket,binding.llCricket)

            setAdapterForFootball()
        }

        binding.llCricket.setOnClickListener(){
            BaseUtils.makeItemSelected(requireContext(),binding.ivCricket,binding.tvCricket,binding.llCricket)
            BaseUtils.makeItemNotSelected(requireContext(),binding.ivFootball,binding.tvFootball,binding.llFootball)

            setAdapterForCricket()
        }

        setFootballList()
        setCricketList()
        setDataForMostHappenData()
        setUpQuestionForRapidFire()
        setUpMatchReview()


        binding.bettaCoin.setOnClickListener(){
            showPopup()
        }

        binding.tvYes.setOnClickListener(){
            questionCount++;
            makeAllBoxUnSelected()

        }

        binding.tvNo.setOnClickListener(){
            questionCount++;
            makeAllBoxUnSelected()
        }

        binding.tvSkip.setOnClickListener(){
            questionCount++;
            makeAllBoxUnSelected()
        }

    }

    fun showPopup() {
        val inflater = requireContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val popupView = inflater.inflate(R.layout.popup_layout, null)

        val displayMetrics = DisplayMetrics()
        requireActivity().windowManager.defaultDisplay.getMetrics(displayMetrics)
        val height = displayMetrics.heightPixels





        val dialog = Dialog(requireContext(), R.style.PopupOverlay)
        dialog.setContentView(popupView)
        dialog.window?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, (height * 0.6).toInt())

        val layoutParams = dialog.window?.attributes
        layoutParams?.gravity = Gravity.BOTTOM
        dialog.window?.attributes = layoutParams

        dialog.show()
    }

    private fun makeAllBoxUnSelected() {

        binding.firstMark.visibility=View.GONE
        binding.firstBox.background = null

        binding.secondMark.visibility=View.GONE
        binding.secondBox.background = null

        binding.thirdMark.visibility=View.GONE
        binding.thirdBox.background = null

        binding.forthMark.visibility=View.GONE
        binding.forthBox.background = null


       val rem = questionCount%4
        binding.questionText.text=rapidFireArrayList[rem]

        val points = 50+(rem*10)
        binding.textView3.text = "$points Coins"

        when(rem){
           0->{
               binding.firstMark.visibility=View.VISIBLE
               binding.firstBox.setBackgroundResource(R.drawable.selected_rapid_shape)
           }
            1->{
                binding.secondMark.visibility=View.VISIBLE
                binding.secondBox.setBackgroundResource(R.drawable.selected_rapid_shape)
            }
            2->{
                binding.thirdMark.visibility=View.VISIBLE
                binding.thirdBox.setBackgroundResource(R.drawable.selected_rapid_shape)
            }
            3->{
                binding.forthMark.visibility=View.VISIBLE
                binding.forthMark.setBackgroundResource(R.drawable.selected_rapid_shape)
            }else->{
            binding.firstMark.visibility=View.VISIBLE
            binding.firstBox.setBackgroundResource(R.drawable.selected_rapid_shape)
            }

        }


    }

    private fun setUpMatchReview() {
        matchReviewList.add(R.drawable.match_preview1)
        matchReviewList.add(R.drawable.match_preview1)
        matchReviewList.add(R.drawable.match_preview1)
        matchReviewList.add(R.drawable.match_preview1)
        matchReviewList.add(R.drawable.match_preview1)
        matchReviewList.add(R.drawable.match_preview1)


        matchReviewAdapter = MatchPreviewAdapter(matchReviewList)
        binding.matchPreviewRv.layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.HORIZONTAL,false);
        binding.matchPreviewRv.adapter = matchReviewAdapter


    }

    private fun setUpQuestionForRapidFire() {
        rapidFireArrayList.add("Will the home team win the match?")
        rapidFireArrayList.add("Will the away team score first?")
        rapidFireArrayList.add("Will the match end in a draw?")
        rapidFireArrayList.add("Will there be more than 2.5 goals scored in the match?")
        rapidFireArrayList.add("Will both teams score in the first half?")






    }

    private fun setDataForMostHappenData() {
        mostHappenArrayList.add(MostlyHappenModel("USAB",R.drawable.image_1))
        mostHappenArrayList.add(MostlyHappenModel("DAL",R.drawable.image_2))
        mostHappenArrayList.add(MostlyHappenModel("NCAAF",R.drawable.image_4))
        mostHappenArrayList.add(MostlyHappenModel("Madrid",R.drawable.iamge_3))
        mostHappenArrayList.add(MostlyHappenModel("PHi",R.drawable.image_5))
        mostHappenArrayList.add(MostlyHappenModel("USAB",R.drawable.image_1))
        mostHappenArrayList.add(MostlyHappenModel("DAL",R.drawable.image_2))
        mostHappenArrayList.add(MostlyHappenModel("NCAAF",R.drawable.iamge_3))
        mostHappenArrayList.add(MostlyHappenModel("Madrid",R.drawable.image_4))
        mostHappenArrayList.add(MostlyHappenModel("PHi",R.drawable.image_5))


        horizontalMostHappenAdapter = HorizontalMostHappenAdapter(mostHappenArrayList)
        binding.mostHappeningRV.layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.HORIZONTAL,false);
        binding.mostHappeningRV.adapter = horizontalMostHappenAdapter

    }

    private fun setAdapterForCricket() {
        horizontalAdapter = HorizontalLiveMatchAdapter(cricketArrayList)
        binding.matchRV.layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.HORIZONTAL,false);
        binding.matchRV.adapter = horizontalAdapter
    }

    private fun setAdapterForFootball() {
        horizontalAdapter = HorizontalLiveMatchAdapter(footballArrayList)
        binding.matchRV.layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.HORIZONTAL,false);
        binding.matchRV.adapter = horizontalAdapter
    }

    private fun setCricketList() {
        val gson = Gson()
        val i: InputStream = requireContext().assets.open("cricketMatches.json")
        val br = BufferedReader(InputStreamReader(i))
        val dataList: SportDataClass = gson.fromJson(br, SportDataClass::class.java)
        cricketArrayList.addAll(dataList.result)
        Log.d("checkingData1",footballArrayList.toString());
        Log.d("checkingData2",footballArrayList.size.toString());
        val imageName = dataList.result[0].team1Logo.toString()



    }

    private fun setFootballList() {
        val gson = Gson()
        val i: InputStream = requireContext().assets.open("footballMatches.json")
        val br = BufferedReader(InputStreamReader(i))
        val dataList: SportDataClass = gson.fromJson(br, SportDataClass::class.java)
        footballArrayList.addAll(dataList.result)
        Log.d("checkingData1",footballArrayList.toString());
        Log.d("checkingData2",footballArrayList.size.toString());
        val imageName = dataList.result[0].team1Logo.toString()

        horizontalAdapter = HorizontalLiveMatchAdapter(footballArrayList)
        binding.matchRV.layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.HORIZONTAL,false);
        binding.matchRV.adapter = horizontalAdapter

    }

    override fun onResume() {
        super.onResume()
        (activity as MainActivity?)!!.homeFragmentFunction()
    }


}