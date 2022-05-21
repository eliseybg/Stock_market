package com.breaktime.stockmarket.data.mapper

import com.breaktime.stockmarket.data.local.CompanyListingEntity
import com.breaktime.stockmarket.domain.model.CompanyListing

fun CompanyListingEntity.toCompanyListing(): CompanyListing {
    return CompanyListing(name = name, symbol = symbol, exchange = exchange)
}

fun CompanyListing.toCompanyListingEntity(): CompanyListingEntity {
    return CompanyListingEntity(name = name, symbol = symbol, exchange = exchange)
}