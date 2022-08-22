package com.exxuslee.domain.usecases

import com.exxuslee.domain.repositories.NumberRepository
import com.exxuslee.domain.utils.Result

interface GetNumberUseCase {
    suspend fun getNumber(number: Int): Result<String>
    suspend fun getRandom(): Result<String>

    class Base(private val repository: NumberRepository) : GetNumberUseCase {
        override suspend fun getNumber(number: Int): Result<String> = repository.getNumber(number)
        override suspend fun getRandom(): Result<String> = repository.getRandom()
    }
}