package com.breaktime.stockmarket.domain.repository

import com.breaktime.stockmarket.domain.model.CompanyListing
import com.breaktime.stockmarket.util.Resource
import kotlinx.coroutines.flow.Flow

interface StockRepository {
    suspend fun getCompanyListings(
        fetchFromRemote: Boolean,
        query: String
    ): Flow<Resource<List<CompanyListing>>>
}