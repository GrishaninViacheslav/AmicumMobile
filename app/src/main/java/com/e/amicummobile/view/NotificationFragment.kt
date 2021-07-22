package com.e.amicummobile.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.e.amicummobile.R
import com.e.amicummobile.adapters.vpNotificationAdapter
import com.e.amicummobile.config.Const
import com.e.amicummobile.databinding.NotificationFragmentBinding
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

        storeAmicum = ViewModelProvider(requireActivity()).get(StoreAmicum::class.java)

        initFragment()                                                                              // инициализируем фрагмент

        initObserve()                                                                               // инициализируем наблюдателей за обновлением данных в локальном хранилище

        // Заполнение вкладок уведомлений бэйджиками
        val tabNotifications = binding.tabNotifications

        setGroupNotificationBadge(getNotificationAllSize(storeAmicum.getNotificationAll().value))

        binding.vpNotificationsFragment.adapter = vpNotificationAdapter(childFragmentManager)
        tabNotifications.setupWithViewPager(binding.vpNotificationsFragment)

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
    private fun getNotificationAllSize(notificationAll: StoreAmicum.NotificationAll?): Int {
        var sizeNotification = 0
        if (notificationAll?.medicalExam != null) {
            sizeNotification += notificationAll.medicalExam.size
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
            groupNotification!!.setVisible(true)
            groupNotification.maxCharacterCount = 3
            groupNotification.number = sizeBadge
        } else {
            groupNotification!!.setVisible(false)
        }
    }

    /**
     * Метод инициализации фрагмента
     */
    private fun initFragment() {

        storeAmicum.getNotification(storeAmicum.getUserSession().value?.userCompanyId)             // получить уведомления пользователя с сервера

        childFragmentManager.beginTransaction()                                                    // загружаем AppBarTop                                                                 // поверх открываем всплывающее окно, которое закроется через 5 секунд
            .add(
                R.id.containerAppBar, AppBarTopMainFragment.newInstance(
                    "Уведомления",
                    Const.APP_BAR_ONLY_BACK,
                    "",
                    ""
                )
            )
            .commitNow()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        fun newInstance() = NotificationFragment()
    }


}