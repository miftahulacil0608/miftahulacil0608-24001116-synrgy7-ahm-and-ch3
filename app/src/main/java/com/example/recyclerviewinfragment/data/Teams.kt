package com.example.recyclerviewinfragment.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class Teams(val imgTeams: Int, val titleTeams:String, val cityTeam:String): Parcelable
