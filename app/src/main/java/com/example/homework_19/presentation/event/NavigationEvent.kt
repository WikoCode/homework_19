package com.example.homework_19.presentation.event

sealed class NavigationEvent() {
    data class NavigateToInfoPage(val userId: Int) : NavigationEvent()
    data class ShowErrorMessage(val message: String) : NavigationEvent()
}