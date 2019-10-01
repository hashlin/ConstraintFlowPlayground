package com.linminphyo.constraint_flow

import androidx.constraintlayout.helper.widget.Flow
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter

/**
 * Created by lin min phyo on 2019-09-25.
 */
class TabAdapter(fm: FragmentManager, behavior: Int) : FragmentStatePagerAdapter(fm, behavior) {
    override fun getItem(position: Int): Fragment {
        return when(position){
            0 -> HorizontalFlowFragment()
            1 -> VerticalFlowFragment()
            else -> MixFragment()
        }
    }

    override fun getCount(): Int {
        return 3
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return when(position){
            0 ->  "Horizontal"
            1 ->  "Vertical"
            else ->  "Mix"
        }
    }

}