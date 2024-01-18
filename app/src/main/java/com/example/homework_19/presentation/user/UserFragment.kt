package com.example.homework_19.presentation.user

import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import com.example.homework_19.data.common.Resource
import com.example.homework_19.databinding.FragmentUserBinding
import com.example.homework_19.presentation.common.BaseFragment
import com.example.homework_19.presentation.event.NavigationEvent
import kotlinx.coroutines.launch


class UserFragment : BaseFragment<FragmentUserBinding>(FragmentUserBinding::inflate) {


    private val viewModel: UserViewModel by viewModels()
    private lateinit var userAdapter: UserAdapter

    override fun setupUI() {

        userAdapter = UserAdapter()
        binding.rvUser.adapter = userAdapter

        observeUsers()
        observeNavigationEvents()
    }


    override fun setupListeners() {

    }


    private fun observeUsers() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.userStateFlow
                .flowWithLifecycle(viewLifecycleOwner.lifecycle, Lifecycle.State.STARTED)
                .collect { resource ->
                    when (resource) {
                        is Resource.Loading -> {
                            binding.progressBar.visibility = if (resource.loading) View.VISIBLE else View.GONE
                        }
                        is Resource.Success -> {
                            userAdapter.submitList(resource.data)
                        }
                        is Resource.Error -> {
                            Toast.makeText(requireContext(), "Error", Toast.LENGTH_SHORT).show()
                        }
                        null -> {
                        }
                    }
                }
        }
    }

    private fun observeNavigationEvents() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.navigationEventFlow
                .flowWithLifecycle(viewLifecycleOwner.lifecycle, Lifecycle.State.STARTED)
                .collect { event ->
                    when (event) {
                        is NavigationEvent.NavigateToInfoPage -> navigateToUserInfo(event.userId)
                        is NavigationEvent.ShowErrorMessage -> showError(event.message)
                    }
                }
        }
    }

    private fun navigateToUserInfo(userId: Int) {

    }

    private fun showError(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }


}