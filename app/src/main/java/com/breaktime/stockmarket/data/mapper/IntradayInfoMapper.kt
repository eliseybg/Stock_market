package com.breaktime.stockmarket.data.mapper

import com.breaktime.stockmarket.data.remote.dto.CompanyInfoDto
import com.breaktime.stockmarket.data.remote.dto.IntradayInfoDto
import com.breaktime.stockmarket.domain.model.CompanyInfo
import com.breaktime.stockmarket.domain.model.IntradayInfo
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*

fun IntradayInfoDto.toIntradayInfo(): IntradayInfo {
    val pattern = "yyyy-MM-dd HH:mm:ss"
    val formatter = DateTimeFormatter.ofPattern(pattern, Locale.getDefault())
    val localDateTime = LocalDateTime.parse(timestamp, formatter)
    return IntradayInfo(localDateTime, close)
}

fun CompanyInfoDto.toCompanyInfo(): CompanyInfo {
    return CompanyInfo(
        symbol.orEmpty(),
        description.orEmpty(),
        name.orEmpty(),
        country.orEmpty(),
        industry.orEmpty()
    )
}

//fun IntradayInfo.toIntradayInfoDto(): IntradayInfoDto {
//    val pattern = "yyyy-MM-dd HH:mm:ss"
//    val formatter = DateTimeFormatter.ofPattern(pattern, Locale.getDefault())
//    val localDateTime = LocalDateTime.parse(timestamp, formatter)
//    return IntradayInfoDto(localDateTime, close)
//}