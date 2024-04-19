package com.example.recyclerviewinfragment.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ListTeam(val imgTeam: Int, val teamName: String, val cityTeam: String):Parcelable
