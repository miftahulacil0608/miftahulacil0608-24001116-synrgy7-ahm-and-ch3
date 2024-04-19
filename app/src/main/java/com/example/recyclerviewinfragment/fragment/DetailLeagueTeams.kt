package com.example.recyclerviewinfragment.fragment

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.recyclerviewinfragment.R
import com.example.recyclerviewinfragment.adapter.RecyclerViewAdapter
import com.example.recyclerviewinfragment.adapter.RecyclerviewClickListener
import com.example.recyclerviewinfragment.adapter.databind.DataBinder
import com.example.recyclerviewinfragment.data.LeagueGroup
import com.example.recyclerviewinfragment.data.Teams
import com.example.recyclerviewinfragment.databinding.FragmentDetailLeagueTeamsBinding



class DetailLeagueTeams : Fragment(), RecyclerviewClickListener<Teams> {

    private lateinit var binding: FragmentDetailLeagueTeamsBinding
    private val dataDetail by lazy{
        arguments?.getParcelable<LeagueGroup>("EXTRA")
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        /*val titleAppBar = activity as AppCompatActivity
        titleAppBar.supportActionBar?.title = "Teams"*/
        return FragmentDetailLeagueTeamsBinding.inflate(inflater,container,false).also {
            binding = it
        }.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //val dataDetail = arguments?.getParcelable<LeagueGroup>("EXTRA")
        dataDetail?.also {
            binding.titleLeague.text = it.title
            binding.imgLeague.setImageResource(it.imgLeague)
        }
        showRecyclerView()
    }
    private fun showRecyclerView(){
        binding.rvTeams.layoutManager = LinearLayoutManager(context,RecyclerView.VERTICAL,false)
        val adapter = RecyclerViewAdapter(
            dataDetail!!.listTeam,
            R.layout.item_view_teams,
            DataBinder(),
            this
        )
        binding.rvTeams.adapter = adapter
        binding.rvTeams.itemAnimator = DefaultItemAnimator()
        adapter.notifyDataSetChanged()
    }

    override fun onClickListener(data: Teams) {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.google.com/search?q=${data.titleTeams}"))
        context?.startActivity(intent)
    }


}