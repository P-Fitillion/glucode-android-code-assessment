package com.glucode.about_you.data

import com.glucode.about_you.engineers.models.Engineer

object EngineersRepository {
    private val _engineers = mutableListOf<Engineer>()
    val engineers: List<Engineer> = _engineers

    fun init(initialEngineers: List<Engineer>) {
        _engineers.clear()
        _engineers.addAll(initialEngineers)
    }
}