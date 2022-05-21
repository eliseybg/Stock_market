package com.breaktime.stockmarket.di

import com.breaktime.stockmarket.data.csv.CSVParser
import com.breaktime.stockmarket.data.csv.CompanyListingParser
import com.breaktime.stockmarket.data.csv.IntradayInfoParser
import com.breaktime.stockmarket.data.repository.StockRepositoryImpl
import com.breaktime.stockmarket.domain.model.CompanyListing
import com.breaktime.stockmarket.domain.model.IntradayInfo
import com.breaktime.stockmarket.domain.repository.StockRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    @Singleton
    abstract fun bindCompanyListingsParser(
        companyListingParser: CompanyListingParser
    ): CSVParser<CompanyListing>

    @Binds
    @Singleton
    abstract fun bindIntradayInfoParser(
        intradayInfoParser: IntradayInfoParser
    ): CSVParser<IntradayInfo>

    @Binds
    @Singleton
    abstract fun bindStockRepository(
        stockRepositoryImpl: StockRepositoryImpl
    ): StockRepository
}