package com.exxuslee.domain.usecases

import com.exxuslee.domain.repositories.NumberRepository
import com.exxuslee.domain.utils.Result

interface NumberUseCase {
    suspend fun getNumber(number: Int): Result<Pair<Int, String>>
    suspend fun getRandom(): Result<Pair<Int, String>>

    class Base(private val repository: NumberRepository) : NumberUseCase {
        override suspend fun getNumber(number: Int): Result<Pair<Int, String>> =
            repository.getNumber(number)

        override suspend fun getRandom(): Result<Pair<Int, String>> = repository.getRandom()
    }
}