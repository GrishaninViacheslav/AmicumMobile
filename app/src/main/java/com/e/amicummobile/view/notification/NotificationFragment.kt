package com.e.amicummobile.view.notification

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.e.amicummobile.R
import com.e.amicummobile.view.notification.adapters.VpNotificationAdapter
import com.e.amicummobile.config.Const
import com.e.amicummobile.databinding.NotificationFragmentBinding
import com.e.amicummobile.modelAmicum.Notification
import com.e.amicummobile.modelAmicum.NotificationList
import com.e.amicummobile.view.menu.AppBarTopMainFragment
import com.e.amicummobile.viewmodel.StoreAmicum

/**
 * Уведомления
 */
class NotificationFragment : Fragment() {

    private var _binding: NotificationFragmentBinding? = null
    private val binding get() = _binding!!
    private lateinit var storeAmicum: StoreAmicum


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = NotificationFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        storeAmicum = ViewModelProvider(requireActivity())[StoreAmicum::class.java]

        initFragment(view)                                                                          // инициализируем фрагмент

        initObserve()                                                                               // инициализируем наблюдателей за обновлением данных в локальном хранилище

    }

    /**
     * Метод инициализации наблюдателей за изменением данных
     */
    private fun initObserve() {
        storeAmicum.getNotificationAll().observe(viewLifecycleOwner, {
            setGroupNotificationBadge(getNotificationAllSize(it))
        })
    }

    /**
     * Метод расчета количества уведомлений
     */
    private fun getNotificationAllSize(notificationList: ArrayList<NotificationList<Notification>>?): Int {
        var sizeNotification = 0
        if (notificationList != null) {
            for (notificationListItem in notificationList) {
                sizeNotification += notificationListItem.notifications.size
            }
        }

        return sizeNotification
    }

    /**
     * Заполнение вкладки группового уведомления бэйджиком
     */
    private fun setGroupNotificationBadge(sizeBadge: Int = 0) {
        val tabNotifications = binding.tabNotifications
        val groupNotification = tabNotifications.getTabAt(0)?.orCreateBadge

        if (sizeBadge > 0) {
            groupNotification!!.isVisible = true
            groupNotification.maxCharacterCount = 3
            groupNotification.number = sizeBadge
        } else {
            groupNotification!!.isVisible = false
        }
    }

    /**
     * Метод инициализации фрагмента
     */
    private fun initFragment(view: View) {

        storeAmicum.getNotification(storeAmicum.getUserSession().value?.userCompanyId)             // получить уведомления пользователя с сервера

        childFragmentManager.beginTransaction()                                                    // загружаем AppBarTop
            .add(
                R.id.containerAppBar, AppBarTopMainFragment.newInstance(
                    "Уведомления",
                    Const.APP_BAR_ONLY_BACK,
                    "",
                    ""
                )
            )
            .commitNow()

        val tabNotifications = binding.tabNotifications                                             // инициализация ViewPager и привязка его к табам
        binding.vpNotificationsFragment.adapter = VpNotificationAdapter(childFragmentManager)
        tabNotifications.setupWithViewPager(binding.vpNotificationsFragment)


        setGroupNotificationBadge(getNotificationAllSize(storeAmicum.getNotificationAll().value))   // Заполнение вкладок уведомлений бэйджиками

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        fun newInstance() = NotificationFragment()
    }


}