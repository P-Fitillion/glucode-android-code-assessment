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
    fun `sortByQuickStatAsc sorts by years ascending`() {
        EngineersRepository.sortByQuickStatAsc { it.years }

        val expectedOrder = listOf("Bob", "Alice", "Charlie")
        val actualOrder = EngineersRepository.engineers.map { it.name }

        assertEquals(expectedOrder, actualOrder)
    }

    @Test
    fun `sortByQuickStatAsc sorts by coffees ascending`() {
        EngineersRepository.sortByQuickStatAsc { it.coffees }

        val expectedOrder = listOf("Alice", "Charlie", "Bob")
        val actualOrder = EngineersRepository.engineers.map { it.name }

        assertEquals(expectedOrder, actualOrder)
    }

    @Test
    fun `sortByQuickStatAsc sorts by bugs ascending`() {
        EngineersRepository.sortByQuickStatAsc { it.bugs }

        val expectedOrder = listOf("Charlie", "Bob", "Alice")
        val actualOrder = EngineersRepository.engineers.map { it.name }

        assertEquals(expectedOrder, actualOrder)
    }

    @Test
    fun `byQuickStatAsc does not change the original data`() {
        val expectedOrder = listOf("Alice", "Bob", "Charlie");
        EngineersRepository.sortByQuickStatAsc { it.bugs }
        val actualOrder = EngineersRepository.unsortedEngineers.map { it.name }
        assertEquals(expectedOrder, actualOrder)
    }
}
