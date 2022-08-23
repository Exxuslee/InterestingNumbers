package com.exxuslee.data.repositories

import com.exxuslee.data.local.dao.NumberDAO
import com.exxuslee.data.mapper.NumberMapper
import com.exxuslee.data.remote.api.ApiService
import com.exxuslee.data.utils.Constants.GENERAL_NETWORK_ERROR
import com.exxuslee.domain.repositories.NumberRepository
import com.exxuslee.domain.utils.Result
import retrofit2.Response

class NumberRepositoryImpl(
    private val apiService: ApiService,
    private val NumberDao: NumberDAO,
) : NumberRepository {
    private val mapper = NumberMapper()

    private suspend fun remoteResult(result: Response<String>): Result<Pair<Int, String>> {
        return if (result.isSuccessful) {
            val remoteData = result.body()
            if (remoteData != null) {
                val number = remoteData.split(' ')[0].toInt()
                NumberDao.insertNumber(mapper.remoteToLocal(remoteData, number))
                Result.Success(Pair(number, remoteData))
            } else Result.Error(GENERAL_NETWORK_ERROR)
        } else Result.Error(GENERAL_NETWORK_ERROR)
    }

    override suspend fun getNumber(number: Int): Result<Pair<Int, String>> {
        val localData = NumberDao.number(number)
        return if (localData != null) Result.Success(Pair(number, mapper.localToDomain(localData)))
        else remoteResult(apiService.getNumber(number = number))
    }

    override suspend fun getRandom() = remoteResult(apiService.getRandom())
}