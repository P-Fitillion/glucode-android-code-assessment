package com.glucode.about_you.data

import com.glucode.about_you.engineers.models.Engineer
import com.glucode.about_you.engineers.models.QuickStats

object EngineersDataService {
    private val _engineers = mutableListOf<Engineer>()
    private var _quickStatSelector: ((QuickStats) -> Int)? = null

    val engineers: List<Engineer>
        get() = _quickStatSelector?.let { selector ->
            _engineers.sortedBy { selector(it.quickStats) }
        } ?: _engineers

    val unsortedEngineers: List<Engineer> = _engineers


    fun init(initialEngineers: List<Engineer>) {
        _engineers.clear()
        _engineers.addAll(initialEngineers)
    }

    fun updateEngineerImageName(engineerName: String, imageName: String) {
        val engineer = _engineers.find { it.name == engineerName }
        engineer?.defaultImageName = imageName
    }

    fun sortByQuickStatAsc(quickStatSelector: (QuickStats) -> Int) {
        _quickStatSelector = quickStatSelector
    }
}