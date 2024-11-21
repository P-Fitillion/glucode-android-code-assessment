package com.glucode.about_you.data

import com.glucode.about_you.engineers.models.Engineer
import com.glucode.about_you.engineers.models.QuickStats

object EngineersDataService {
    private val _engineers = mutableListOf<Engineer>()
    private var _currentSortingCriterion: SortingCriterion? = null

    val engineers: List<Engineer>
        get() = _currentSortingCriterion?.let { criterion ->
            _engineers.sortedBy { criterion.selector(it.quickStats) }
        } ?: _engineers

    val unsortedEngineers: List<Engineer> = _engineers

    val currentSortingCriterion: String?
        get() = _currentSortingCriterion?.name

    fun init(initialEngineers: List<Engineer>) {
        _currentSortingCriterion = null
        _engineers.clear()
        _engineers.addAll(initialEngineers)
    }

    fun updateEngineerImageName(engineerName: String, imageName: String) {
        val engineer = _engineers.find { it.name == engineerName }
        engineer?.defaultImageName = imageName
    }

    fun sortByQuickStatAsc(criterion: SortingCriterion) {
        _currentSortingCriterion = criterion
    }
}

enum class SortingCriterion(val selector: (QuickStats) -> Int) {
    YEARS({ it.years }),
    COFFEES({ it.coffees }),
    BUGS({ it.bugs })
}