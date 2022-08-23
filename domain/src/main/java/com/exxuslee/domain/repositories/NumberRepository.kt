package com.exxuslee.domain.repositories

import com.exxuslee.domain.utils.Result

interface NumberRepository {
    suspend fun getNumber(number: Int): Result<Pair<Int, String>>
    suspend fun getRandom(): Result<Pair<Int, String>>
}