package com.exxuslee.data.mapper

interface BaseMapper<D, R> {

    fun remoteToDomain(type: R): D

    interface TripleMapper<D, L, R> : BaseMapper<D, R> {
        fun localToDomain(type: L): D
        fun remoteToLocal(type: R, number:Int): L
    }
}