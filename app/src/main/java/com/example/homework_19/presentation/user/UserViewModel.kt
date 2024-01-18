package com.example.homework_19.presentation.user

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.homework_19.data.common.Resource
import com.example.homework_19.domain.model.User
import com.example.homework_19.domain.use_case.GetUserUseCase
import com.example.homework_19.presentation.event.NavigationEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(
    private val getUserUseCase: GetUserUseCase
) : ViewModel() {

    private val _userStateFlow = MutableStateFlow<Resource<List<User>>?>(null)
    val userStateFlow: StateFlow<Resource<List<User>>?> = _userStateFlow

    private val _navigationEventFlow = MutableSharedFlow<NavigationEvent>()
    val navigationEventFlow: SharedFlow<NavigationEvent> get() = _navigationEventFlow.asSharedFlow()

    init {
        fetchUsers()
    }

    private fun fetchUsers() {
        _userStateFlow.value = Resource.Loading(true)
        viewModelScope.launch {
            getUserUseCase().collect { resource ->
                _userStateFlow.value = resource
            }
        }
    }

    fun onUserSelected(userId: Int) {
        viewModelScope.launch {
            _navigationEventFlow.emit(NavigationEvent.NavigateToInfoPage(userId))
        }
    }

    private fun handleFailure(errorMessage: String) {
        viewModelScope.launch {
            _navigationEventFlow.emit(NavigationEvent.ShowErrorMessage(errorMessage))
        }
    }


}