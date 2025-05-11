package com.example.schoolsguide.tabAdapter

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.schoolsguide.tabFragments4.Description9
import com.example.schoolsguide.tabFragments4.MapsFragment9
import com.example.schoolsguide.tabFragments4.Reviews9

internal class MyAdapter9(var context: Context, fm: FragmentManager, var totalTabs: Int ): FragmentPagerAdapter (fm) {

    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> {
                Description9 ()
            }

            1 -> {
                Reviews9 ()
            }

            2 -> {
                MapsFragment9 ()
            }
            else -> getItem(position)
        }
    }

    override fun getCount(): Int {
        return totalTabs
    }


}