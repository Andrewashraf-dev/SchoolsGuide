package com.example.schoolsguide.tabAdapter

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.schoolsguide.tabFragments4.*

internal class MyAdapter8(var context: Context, fm: FragmentManager, var totalTabs: Int ): FragmentPagerAdapter (fm) {

    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> {
                Description8 ()
            }

            1 -> {
                Reviews8 ()
            }

            2 -> {
                MapsFragment8 ()
            }
            else -> getItem(position)
        }
    }

    override fun getCount(): Int {
        return totalTabs
    }


}