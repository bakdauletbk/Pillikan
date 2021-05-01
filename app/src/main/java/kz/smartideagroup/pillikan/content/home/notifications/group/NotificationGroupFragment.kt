package kz.smartideagroup.pillikan.content.home.notifications.group

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import kz.smartideagroup.pillikan.R
import kz.smartideagroup.pillikan.common.base_interfaces.FragmentImpl
import kz.smartideagroup.pillikan.common.base_vmmv.BaseFragment
import kz.smartideagroup.pillikan.common.utils.ApplicationPreferences
import kz.smartideagroup.pillikan.common.utils.NOTIFICATION_TYPE
import kz.smartideagroup.pillikan.common.views.viewBinding
import kz.smartideagroup.pillikan.databinding.FragmentNotificationsGroupBinding
import org.jetbrains.anko.sdk27.coroutines.onClick

class NotificationGroupFragment :
    BaseFragment(R.layout.fragment_notifications_group, R.id.home_container), FragmentImpl {


    private val binding by viewBinding(FragmentNotificationsGroupBinding::bind)
    private val adapter: NotificationGroupAdapter = NotificationGroupAdapter(this)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        lets()
    }

    override fun lets() {
        setupViewModel()
        setupListeners()
        setupObservers()
        setupRecyclerView()
        showNotificationGroups()
    }

    fun onGroupClick(id: Int) {
        findNavController().navigate(
            R.id.action_notificationGroupFragment_to_notificationListFragment,
            bundleOf(NOTIFICATION_TYPE to id)
        )
    }

    private fun showNotificationGroups() {
        adapter.addGroups(ApplicationPreferences.getNotificationsGroup(requireContext()))
    }

    override fun setupViewModel() {
    }

    override fun setupListeners() {
        binding.backButton.onClick {
            requireActivity().onBackPressed()
        }
    }

    override fun setupObservers() {
    }

    private fun setupRecyclerView() {
        binding.notificationGroupRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.notificationGroupRecyclerView.adapter = adapter
    }
}