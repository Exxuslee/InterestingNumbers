package com.exxuslee.data.mapper

import com.exxuslee.data.local.entities.NumberEntity


class NumberMapper : BaseMapper.TripleMapper<String, NumberEntity, String> {
    override fun remoteToDomain(type: String) = type

    override fun localToDomain(type: NumberEntity) = type.content

    override fun remoteToLocal(type: String, number: Int): NumberEntity {
        return NumberEntity(number = number, content = type)
    }
}