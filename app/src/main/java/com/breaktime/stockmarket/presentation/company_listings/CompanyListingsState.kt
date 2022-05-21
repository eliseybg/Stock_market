package com.breaktime.stockmarket.presentation.company_listings

import com.breaktime.stockmarket.domain.model.CompanyListing

data class CompanyListingsState(
    val companies: List<CompanyListing> = emptyList(),
    val loading: Boolean = false,
    val isRefreshing: Boolean = false,
    val searchQuery: String = ""
)
