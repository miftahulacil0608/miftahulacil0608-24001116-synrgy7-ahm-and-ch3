package com.example.recyclerviewinfragment.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class LeagueGroup(val imgLeague:Int, val title: String, val listTeam: ArrayList<Teams>): Parcelable
