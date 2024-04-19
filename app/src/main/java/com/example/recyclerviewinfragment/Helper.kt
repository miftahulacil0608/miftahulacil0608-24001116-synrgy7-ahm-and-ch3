package com.example.recyclerviewinfragment

import android.content.res.Resources
import com.example.recyclerviewinfragment.data.LeagueGroup
import com.example.recyclerviewinfragment.data.Teams
object Helper {
    fun addListLeagueGroup(resources: Resources,resImg:Int,resTitle:Int,listTeam:ArrayList<ArrayList<Teams>>):ArrayList<LeagueGroup>{
        val title = resources.getStringArray(resTitle)
        val img = resources.obtainTypedArray(resImg)
        val listLeague = ArrayList<LeagueGroup>()
        for (i in title.indices){
            listLeague.add(LeagueGroup(img.getResourceId(i,-1),title[i],listTeam[i]))
        }
        return listLeague
    }
    fun addListTeams(resources: Resources,resImg: Int,resTitle:Int,resCity:Int):ArrayList<Teams>{
        val imgTeam = resources.obtainTypedArray(resImg)
        val titleTeam = resources.getStringArray(resTitle)
        val cityTeam = resources.getStringArray(resCity)
        val listTeams = ArrayList<Teams>()
        for (i in titleTeam.indices){
            listTeams.add(Teams(imgTeam.getResourceId(i,-1),titleTeam[i],cityTeam[i]))
        }
        return listTeams


    }
}