package com.breaktime.stockmarket.data.repository

import com.breaktime.stockmarket.data.csv.CSVParser
import com.breaktime.stockmarket.data.local.StockDatabase
import com.breaktime.stockmarket.data.mapper.toCompanyListing
import com.breaktime.stockmarket.data.mapper.toCompanyListingEntity
import com.breaktime.stockmarket.data.remote.StockApi
import com.breaktime.stockmarket.domain.model.CompanyListing
import com.breaktime.stockmarket.domain.repository.StockRepository
import com.breaktime.stockmarket.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class StockRepositoryImpl @Inject constructor(
    private val api: StockApi,
    private val db: StockDatabase,
    private val companyListingParser: CSVParser<CompanyListing>
) : StockRepository {
    private val dao = db.dao

    override suspend fun getCompanyListings(
        fetchFromRemote: Boolean,
        query: String
    ): Flow<Resource<List<CompanyListing>>> {
        return flow {
            emit(Resource.Loading(true))
            val localListings = dao.searchCompanyListing(query)
            emit(Resource.Success(localListings.map { it.toCompanyListing() }))
            val isDbEmpty = localListings.isEmpty() && query.isBlank()
            val shouldJustLoadFromCache = !isDbEmpty && !fetchFromRemote
            if (shouldJustLoadFromCache) {
                emit(Resource.Loading(false))
                return@flow
            }
            val remoteListing = try {
                val response = api.getListing()
                companyListingParser.parse(response.byteStream())
            } catch (e: IOException) {
                e.printStackTrace()
                emit(Resource.Error("Couldn't load data"))
                null
            } catch (e: HttpException) {
                e.printStackTrace()
                emit(Resource.Error("Couldn't load data"))
                null
            }

            remoteListing?.let {
                dao.clearCompanyListings()
                dao.insertCompanyListings(remoteListing.map { it.toCompanyListingEntity() })
                val localData = dao.searchCompanyListing(query).map { it.toCompanyListing() }
                emit(Resource.Success(localData))
                emit(Resource.Loading(false))
            }
        }
    }
}