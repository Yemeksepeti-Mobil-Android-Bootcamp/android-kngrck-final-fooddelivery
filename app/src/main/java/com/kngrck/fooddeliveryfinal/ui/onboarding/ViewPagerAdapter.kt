package com.kngrck.fooddeliveryfinal.ui.onboarding

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

private const val FRAGMENT_COUNT = 3

class ViewPagerAdapter(activity: FragmentActivity) : FragmentStateAdapter(activity) {

    override fun getItemCount(): Int = FRAGMENT_COUNT

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> FirstOnBoardingFragment()
            1 -> SecondOnBoardingFragment()
            2 -> ThirdOnBoardingFragment()
            else -> ThirdOnBoardingFragment()
        }
    }
}