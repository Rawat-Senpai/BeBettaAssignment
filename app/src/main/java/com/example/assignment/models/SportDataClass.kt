package com.example.assignment.models

import com.google.gson.annotations.SerializedName

data class SportDataClass(
    @SerializedName("result") var result: ArrayList<DataListItem> = arrayListOf()
){
    data class DataListItem(
        @SerializedName("id") var id: Int? = null,
        @SerializedName("team1") var team1: String? = null,
        @SerializedName("team2") var team2: String? = null,
        @SerializedName("team1Logo") var team1Logo: String? = null,
        @SerializedName("team2Logo") var team2Logo: String? = null,
        @SerializedName("teamOneScore") var teamOneScore: String? = null,
        @SerializedName("teamTwoScore") var teamTwoScore: String? = null,
        @SerializedName("timeRemaining") var timeRemaining: Int? = null
    )
}
