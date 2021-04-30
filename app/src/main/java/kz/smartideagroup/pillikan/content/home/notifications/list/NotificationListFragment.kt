package kz.smartideagroup.pillikan.content.home.notifications.list

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kz.smartideagroup.pillikan.R
import kz.smartideagroup.pillikan.common.base_interfaces.FragmentImpl
import kz.smartideagroup.pillikan.common.base_vmmv.BaseFragment
import kz.smartideagroup.pillikan.common.utils.*
import kz.smartideagroup.pillikan.common.views.SoftPaginationScrollListener
import kz.smartideagroup.pillikan.common.views.viewBinding
import kz.smartideagroup.pillikan.databinding.FragmentNotificationListBinding
import org.jetbrains.anko.sdk27.coroutines.onClick

class NotificationListFragment :
    BaseFragment(R.layout.fragment_notification_list, R.id.home_container), FragmentImpl {

    private lateinit var viewModel: NotificationListViewModel
    private val binding by viewBinding(FragmentNotificationListBinding::bind)
    private val adapter = NotificationListAdapter(this)
    private var currentPage = NUMBER_ZERO
    private var totalCounts = NUMBER_ZERO
    var isPageLast: Boolean = false
    var isPageLoading: Boolean = false

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
            getInitialPage()
        } catch (e: Exception) {
            handleCrashAndReport(this.javaClass.name, e.message.toString())
        }
    }

    private fun getInitialPage() {
        getNotifications(NUMBER_ZERO)
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

        viewModel.systemNotificationList.observe(viewLifecycleOwner, {
            hideLoading()
            when (it == null) {
                true -> showException(getString(R.string.unknown))
                false -> initNotifications(it)
            }
        })

        viewModel.paymentNotificationList.observe(viewLifecycleOwner, {
            isPageLoading = false
            hideLoading()
            when (it == null) {
                true -> showException(getString(R.string.unknown))
                false -> initNotifications(it)
            }
        })

        viewModel.totalElements.observe(viewLifecycleOwner, {
            totalCounts = it
        })

    }

    private fun initNotifications(it: List<NotificationModel>) {
        isPageLoading = false
        when (arguments?.getInt(NOTIFICATION_TYPE)) {
            NOTIFICATION_TYPE_SYSTEM ->
                adapter.addNotifications(it, R.drawable.ic_system_notification)
            NOTIFICATION_TYPE_PAYMENT ->
                adapter.addNotifications(it, R.drawable.ic_payment_notification)
        }
        when (currentPage == NUMBER_ZERO) {
            true -> binding.notificationListRecyclerView.scrollToPosition(currentPage)
        }
    }

    private fun setupRecyclerView() {
        val manager = ApplicationPreferences.getReversRecyclerView(requireContext())
        binding.notificationListRecyclerView.layoutManager = manager
        binding.notificationListRecyclerView.adapter = adapter
        binding.notificationListRecyclerView.addOnScrollListener(object :
            SoftPaginationScrollListener(manager) {
            override fun isLastPage(): Boolean {
                return isPageLast
            }

            override fun isLoading(): Boolean {
                return isPageLoading
            }

            override fun loadMoreItems() {
                getNextPage()
            }
        })
    }

    private fun getNextPage() {
        when (totalCounts > adapter.itemCount) {
            true -> {
                currentPage += NUMBER_ONE
                isPageLoading = true
                getNotifications(currentPage)
                isPageLast = false
            }
            false -> {
                isPageLast = true
            }
        }
    }
}