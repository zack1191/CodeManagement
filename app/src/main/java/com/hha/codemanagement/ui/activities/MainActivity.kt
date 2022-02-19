package com.hha.codemanagement.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.google.android.material.tabs.TabLayoutMediator
import com.hha.codemanagement.databinding.ActivityMainBinding
import com.hha.codemanagement.ui.adapters.MainViewPager2Adapter
import com.hha.codemanagement.ui.fragments.MoviesFragment
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity()
{
    @Inject
    lateinit var testString : String
    private lateinit var binding : ActivityMainBinding

    private val v2Adapter by lazy {
        MainViewPager2Adapter(supportFragmentManager, lifecycle)
    }

    override fun onCreate(savedInstanceState : Bundle?)
    {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        bindFragments()
        Log.i("hilttest",testString)
    }

    private fun bindFragments()
    {
        binding.apply {

            v2Adapter.addFragment(MoviesFragment())
            v2Adapter.addFragment(MoviesFragment())
            v2Adapter.addFragment(MoviesFragment())
            v2Adapter.addFragment(MoviesFragment())
            v2Adapter.addFragment(MoviesFragment())


            viewPager.adapter = v2Adapter
            viewPager.isUserInputEnabled = false


            TabLayoutMediator(tabLayout, viewPager) { tab, position ->
                when (position)
                {
                    0 ->
                    {
                        tab.text = "Movies"
                    }

                    1 ->
                    {
                        tab.text = "Events"
                    }

                    2 ->
                    {
                        tab.text = "Plays"

                    }

                    3 ->
                    {
                        tab.text = "Sports"
                    }

                    4 ->
                    {
                        tab.text = "Activities"
                    }
                }
            }.attach()
        }
    }

}