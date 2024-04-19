package com.example.recyclerviewinfragment
import android.content.res.Resources
import com.example.recyclerviewinfragment.data.LeagueGroup
import com.example.recyclerviewinfragment.data.ListTeam

object Helper {


    //function untuk melakukan input data pada class fragmentListLeagueGroupFragment
    fun listTeam(resources: Resources,imgResources:Int,titleResources:Int,cityTeamResources:Int):ArrayList<ListTeam>{
        val imgTeam = resources.obtainTypedArray(imgResources)
        val titleTeam = resources.getStringArray(titleResources)
        val cityTeam = resources.getStringArray(cityTeamResources)
        val listTeamItem = ArrayList<ListTeam>()
        for (i in titleTeam.indices){
            val data = ListTeam(imgTeam.getResourceId(i,-1),titleTeam[i],cityTeam[i])
            listTeamItem.add(data)
        }
        return listTeamItem
    }


    //function untuk melakukan input data listLeagueGroup pada class DetailRecyclerViewFragment
    fun listLeagueGroup(resources: Resources,dataImagesResources:Int,dataTitleResources:Int,listTeam:ArrayList<ArrayList<ListTeam>>):ArrayList<LeagueGroup>{
        val dataImages = resources.obtainTypedArray(dataImagesResources)
        val dataTitleLeague = resources.getStringArray(dataTitleResources)
        val listLeague = ArrayList<LeagueGroup>()
        for (i in dataTitleLeague.indices){
            val data = LeagueGroup(dataImages.getResourceId(i,-1),dataTitleLeague[i], listTeam[i])
            listLeague.add(data)
        }
        return listLeague
    }


}
