package com.example.recyclerviewinfragment.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class LeagueGroup(val image:Int, val title:String, val listTeam: ArrayList<ListTeam>): Parcelable
