package com.e.amicummobile.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.e.amicummobile.view.GroupNotificationFragment
import com.e.amicummobile.view.PersonalNotificationFragment
import java.lang.IllegalStateException


class vpNotificationAdapter(fragmentManager: FragmentManager) :
    FragmentStatePagerAdapter(fragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {


    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> GroupNotificationFragment()
            1 -> PersonalNotificationFragment()
            else -> GroupNotificationFragment()
        }
    }

    override fun getCount(): Int {
        return 2
    }

    override fun getPageTitle(position: Int): CharSequence? =
        when (position) {
            0 -> "Групповые"
            1 -> "Личные"
            else -> throw IllegalStateException("Индекс списка больше размера списка")
        }
}