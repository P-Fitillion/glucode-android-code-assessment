package com.glucode.about_you

import org.junit.Test

import org.junit.Assert.*
import org.junit.Before

import com.glucode.about_you.data.EngineersRepository
import com.glucode.about_you.engineers.models.Engineer
import com.glucode.about_you.engineers.models.QuickStats

class EngineersRepositoryTest {

    @Before
    fun setUp() {
        val initialEngineers = listOf(
            Engineer(
                name = "Alice",
                role = "Backend Developer",
                defaultImageName = "",
                quickStats = QuickStats(years = 5, coffees = 1000, bugs = 50),
                questions = emptyList()
            ),
            Engineer(
                name = "Bob",
                role = "Frontend Developer",
                defaultImageName = "",
                quickStats = QuickStats(years = 3, coffees = 2000, bugs = 30),
                questions = emptyList()
            ),
            Engineer(
                name = "Charlie",
                role = "DevOps Engineer",
                defaultImageName = "",
                quickStats = QuickStats(years = 8, coffees = 1500, bugs = 20),
                questions = emptyList()
            )
        )
        EngineersRepository.init(initialEngineers)
    }

    @Test
    fun `byQuickStatAsc sorts by years ascending`() {
        val sortedByYears = EngineersRepository.byQuickStatAsc { it.years }

        val expectedOrder = listOf("Bob", "Alice", "Charlie")
        val actualOrder = sortedByYears.map { it.name }

        assertEquals(expectedOrder, actualOrder)
    }

    @Test
    fun `byQuickStatAsc sorts by coffees ascending`() {
        val sortedByCoffees = EngineersRepository.byQuickStatAsc { it.coffees }

        val expectedOrder = listOf("Alice", "Charlie", "Bob")
        val actualOrder = sortedByCoffees.map { it.name }

        assertEquals(expectedOrder, actualOrder)
    }

    @Test
    fun `byQuickStatAsc sorts by bugs ascending`() {
        val sortedByBugs = EngineersRepository.byQuickStatAsc { it.bugs }

        val expectedOrder = listOf("Charlie", "Bob", "Alice")
        val actualOrder = sortedByBugs.map { it.name }

        assertEquals(expectedOrder, actualOrder)
    }

    @Test
    fun `byQuickStatAsc does not update the original data`() {
        val expectedOrder = listOf("Alice", "Bob", "Charlie");
        EngineersRepository.byQuickStatAsc { it.bugs }
        val actualOrder = EngineersRepository.engineers.map { it.name }
        assertEquals(expectedOrder, actualOrder)
    }
}
