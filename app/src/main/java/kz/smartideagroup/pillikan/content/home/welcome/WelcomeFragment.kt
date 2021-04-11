package kz.smartideagroup.pillikan.content.home.welcome

import android.os.Bundle
import android.view.View
import androidx.activity.addCallback
import androidx.lifecycle.ViewModelProvider
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kz.smartideagroup.pillikan.R
import kz.smartideagroup.pillikan.common.base_interfaces.FragmentImpl
import kz.smartideagroup.pillikan.common.base_vmmv.BaseFragment
import kz.smartideagroup.pillikan.common.views.viewBinding
import kz.smartideagroup.pillikan.content.home.welcome.adapters.BannersAdapter
import kz.smartideagroup.pillikan.content.home.welcome.models.BannerModel
import kz.smartideagroup.pillikan.databinding.FragmentHomeWelcomeBinding
import java.lang.Exception

class WelcomeFragment : BaseFragment(R.layout.fragment_home_welcome), FragmentImpl {

    private lateinit var viewModel: WelcomeViewModel
    private val binding by viewBinding(FragmentHomeWelcomeBinding::bind)
    private val adapter: BannersAdapter = BannersAdapter(this)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        lets()
    }

    override fun lets() {
        try {
            showLoading()
            setupViewModel()
            setupListeners()
            setupObservers()
            setupRecyclerView()
            requestBannersItem()
        } catch (e: Exception) {
            handleCrashAndReport(this.javaClass.name, e.message.toString())
        }
    }


    override fun setupViewModel() {
        viewModel = ViewModelProvider(this).get(WelcomeViewModel::class.java)
    }

    override fun setupListeners() {
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
        viewModel.sliderItems.observe(viewLifecycleOwner, {
            hideLoading()
            when (it == null) {
                true -> showException(getString(R.string.unknown))
                false -> initBannerItems(it)
            }
        })
    }

    private fun setupRecyclerView() {
        binding.welcomeBannersRecyclerView.adapter = adapter
    }

    private fun requestBannersItem() {
        CoroutineScope(Dispatchers.IO).launch {
            viewModel.getSliderItems()
        }
    }

    private fun initBannerItems(bannerItems: List<BannerModel>) {
        adapter.addBannerList(bannerItems)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requireActivity().onBackPressedDispatcher.addCallback(this) {

        }
    }

}