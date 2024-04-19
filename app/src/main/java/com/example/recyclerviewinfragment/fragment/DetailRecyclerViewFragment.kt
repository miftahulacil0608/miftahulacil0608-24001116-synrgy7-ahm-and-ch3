package com.example.recyclerviewinfragment.fragment

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.RecyclerListener
import com.example.recyclerviewinfragment.adapter.RecyclerViewAdapter
import com.example.recyclerviewinfragment.data.LeagueGroup
import com.example.recyclerviewinfragment.data.ListTeam
import com.example.recyclerviewinfragment.databinding.FragmentDetailRecyclerViewBinding

@Suppress("DEPRECATION")
class DetailRecyclerViewFragment : Fragment(),RecyclerViewAdapter.OnClickListener {
    companion object{
        const val EXTRA_PARCELABLE = "EXTRA_PARCELABLE"
    }

   private lateinit var detailRecyclerViewBinding: FragmentDetailRecyclerViewBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        detailRecyclerViewBinding = FragmentDetailRecyclerViewBinding.inflate(inflater,container,false)
        return detailRecyclerViewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val dataDetail = arguments?.getParcelable<LeagueGroup>(EXTRA_PARCELABLE)
        dataDetail?.let {
            showDetailData(it)
        }
    }

    private fun showDetailData(data:LeagueGroup){
        detailRecyclerViewBinding.imgLeague.setImageResource(data.image)
        detailRecyclerViewBinding.titleLeague.text = data.title
        detailRecyclerViewBinding.rvTeams.setHasFixedSize(true)
        showRecyclerList(data.listTeam)
    }
    private fun showRecyclerList(dataTeam: ArrayList<ListTeam>){
        detailRecyclerViewBinding.rvTeams.layoutManager = LinearLayoutManager(context,RecyclerView.VERTICAL,false)
        val convertToAny = convertDataTypeToAny(dataTeam)
        val adapter = RecyclerViewAdapter(convertToAny)
        adapter.notifyDataSetChanged()
        detailRecyclerViewBinding.rvTeams.adapter = adapter
        adapter.setOnClickListener(this)
    }
    private fun convertDataTypeToAny(dataTeams:ArrayList<ListTeam>):ArrayList<Any>{
        val dataTeamsAny = ArrayList<Any>()
        dataTeamsAny.addAll(dataTeams)

        return dataTeamsAny
    }

    override fun onClickLeagueGroup(leagueGroup: LeagueGroup) {
        //nothing happens
    }

    override fun onClickTeam(listTeam: ListTeam) {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse("http://www.google.com/search?q=${listTeam.teamName}"))
        requireContext().startActivity(intent)
    }


}