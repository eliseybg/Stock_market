package com.breaktime.stockmarket.domain.repository

import com.breaktime.stockmarket.domain.model.CompanyInfo
import com.breaktime.stockmarket.domain.model.CompanyListing
import com.breaktime.stockmarket.domain.model.IntradayInfo
import com.breaktime.stockmarket.util.Resource
import kotlinx.coroutines.flow.Flow

interface StockRepository {
    suspend fun getCompanyListings(
        fetchFromRemote: Boolean,
        query: String
    ): Flow<Resource<List<CompanyListing>>>

    suspend fun getInradayInfo(
        symbol: String,
    ): Resource<List<IntradayInfo>>

    suspend fun getCompanyInfo(
        symbol: String,
    ): Resource<CompanyInfo>
}