package com.hha.codemanagement.ui.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter

class MainViewPager2Adapter(fragmentManager : FragmentManager, lifecycle : Lifecycle) : FragmentStateAdapter(fragmentManager, lifecycle)
{
    private var fragmentList : ArrayList<Fragment> = arrayListOf()

    override fun getItemCount() : Int
    {
        return fragmentList.size
    }

    override fun createFragment(position : Int) : Fragment
    {
        return fragmentList[position]
    }

    fun addFragment(fragment : Fragment)
    {
        fragmentList.add(fragment)
    }
}