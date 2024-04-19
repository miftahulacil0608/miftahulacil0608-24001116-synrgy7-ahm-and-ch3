package com.example.recyclerviewinfragment

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.FragmentManager
import com.example.recyclerviewinfragment.databinding.ActivityMainBinding
import com.example.recyclerviewinfragment.fragment.ListLeagueGroupFragment

class MainActivity : AppCompatActivity() {

    private val mainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(mainBinding.root)

        //Attach Fragment in here done

        val fragmentManager = supportFragmentManager
        val listLeagueGroup = ListLeagueGroupFragment()
        val listLeagueFragment = fragmentManager.findFragmentByTag(ListLeagueGroupFragment::class.java.simpleName)

        if (listLeagueFragment !is ListLeagueGroupFragment)
        {
            fragmentManager
                .beginTransaction()
                .replace(R.id.fragment_container,listLeagueGroup,ListLeagueGroupFragment::class.java.simpleName)
                .commit()
        }

    }
}