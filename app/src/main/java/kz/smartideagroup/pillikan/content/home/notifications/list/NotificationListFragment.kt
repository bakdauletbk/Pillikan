package kz.smartideagroup.pillikan.content.home.notifications.list

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import kz.smartideagroup.pillikan.R
import kz.smartideagroup.pillikan.common.base_interfaces.FragmentImpl
import kz.smartideagroup.pillikan.common.base_vmmv.BaseFragment
import kz.smartideagroup.pillikan.common.pagination.PaginationScrollListener
import kz.smartideagroup.pillikan.common.utils.NOTIFICATION_TYPE
import kz.smartideagroup.pillikan.common.utils.NOTIFICATION_TYPE_PAYMENT
import kz.smartideagroup.pillikan.common.utils.NOTIFICATION_TYPE_SYSTEM
import kz.smartideagroup.pillikan.common.utils.NUMBER_ZERO
import kz.smartideagroup.pillikan.common.views.viewBinding
import kz.smartideagroup.pillikan.databinding.FragmentNotificationListBinding
import org.jetbrains.anko.sdk27.coroutines.onClick

class NotificationListFragment :
    BaseFragment(R.layout.fragment_notification_list, R.id.home_container), FragmentImpl {

    private lateinit var viewModel: NotificationListViewModel
    private val binding by viewBinding(FragmentNotificationListBinding::bind)
    private val adapter = NotificationListAdapter(this)
    var isLastPage: Boolean = false
    var isPageLoading: Boolean = false
    var currentPage = NUMBER_ZERO
    var lastCount = NUMBER_ZERO

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        lets()
    }

    override fun lets() {
        try {
            setupToolbar()
            setupViewModel()
            setupListeners()
            setupObservers()
            setupRecyclerView()
            getNotifications(currentPage)
        } catch (e: Exception) {
            handleCrashAndReport(this.javaClass.name, e.message.toString())
        }
    }

    private fun setupToolbar() {
        when (arguments?.getInt(NOTIFICATION_TYPE)) {
            NOTIFICATION_TYPE_SYSTEM -> binding.backButton.text =
                getString(R.string.notification_type_system)
            NOTIFICATION_TYPE_PAYMENT -> binding.backButton.text =
                getString(R.string.notification_type_payment)
        }
    }

    private fun getNotifications(page: Int) {
        when (arguments?.getInt(NOTIFICATION_TYPE)) {
            NOTIFICATION_TYPE_SYSTEM -> viewModel.getSystemNotifications(
                NotificationRequest(
                    page.toString()
                )
            )
            NOTIFICATION_TYPE_PAYMENT -> viewModel.getPaymentNotification(
                NotificationRequest(
                    page.toString()
                )
            )
        }
    }

    override fun setupViewModel() {
        viewModel = ViewModelProvider(this).get(NotificationListViewModel::class.java)
    }

    override fun setupListeners() {
        binding.backButton.onClick {
            requireActivity().onBackPressed()
        }
    }

    override fun setupObservers() {
        viewModel.isLoading.observe(viewLifecycleOwner, {
            when (it) {
                true -> showLoading()
                false -> hideLoading()
            }
        })
        viewModel.isError.observe(viewLifecycleOwner, {
            handleCrashAndReport(this.javaClass.name, it.toString())
            when (it == null) {
                true -> showException(getString(R.string.unknown))
                false -> showException(it)
            }
        })

        viewModel.notificationList.observe(viewLifecycleOwner, {
            hideLoading()
            when (it == null) {
                true -> showException(getString(R.string.unknown))
                false -> initNotifications(it)
            }
        })

        viewModel.notificationList.observe(viewLifecycleOwner, {
            hideLoading()
            when (it == null) {
                true -> showException(getString(R.string.unknown))
                false -> initNotifications(it)
            }
        })

        viewModel.totalElements.observe(viewLifecycleOwner, {

        })

    }

    private fun initNotifications(it: List<NotificationModel>) {
        when (arguments?.getInt(NOTIFICATION_TYPE)) {
            NOTIFICATION_TYPE_SYSTEM ->
                adapter.addNotifications(it, R.drawable.ic_system_notification)
            NOTIFICATION_TYPE_PAYMENT ->
                adapter.addNotifications(it, R.drawable.ic_payment_notification)
        }
        when(currentPage == NUMBER_ZERO){
            true -> binding.notificationListRecyclerView.scrollToPosition(currentPage)
        }
    }

    private fun setupRecyclerView() {
        val manager = LinearLayoutManager(requireContext())
        manager.stackFromEnd = true
        manager.reverseLayout = true
        manager.isSmoothScrollbarEnabled = true
        binding.notificationListRecyclerView.layoutManager = manager
        binding.notificationListRecyclerView.adapter = adapter
        binding.notificationListRecyclerView.addOnScrollListener(object : PaginationScrollListener(manager){
            override fun isLastPage(): Boolean {
                return isLastPage
            }
            override fun isLoading(): Boolean {
                return isPageLoading
            }
            override fun loadMoreItems() {
                isPageLoading = true
                getMoreItems()
            }
        })
    }

    private fun getMoreItems() {
        isPageLoading = false
        getNotifications(currentPage++)
    }
}