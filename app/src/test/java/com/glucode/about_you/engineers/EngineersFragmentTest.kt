package com.glucode.about_you.engineers

import com.glucode.about_you.engineers.models.Engineer
import com.glucode.about_you.engineers.models.QuickStats
import org.junit.Before
import org.junit.Test
import junit.framework.TestCase.assertEquals

class EngineersFragmentTest {
    private lateinit var engineers: List<Engineer>
    private lateinit var expectedEngineersByYears: List<Engineer>
    private lateinit var expectedEngineersByCoffees: List<Engineer>
    private lateinit var expectedEngineersByBugs: List<Engineer>

    @Before
    fun setUp() {
        val engineer1 = Engineer(
                name = "John",
                role = "Software",
                defaultImageName = "",
                quickStats = QuickStats(years = 1, coffees = 2, bugs = 3),
                questions = emptyList()
            )

        val engineer2 = Engineer(
            name = "Jane",
            role = "Software",
            defaultImageName = "",
            quickStats = QuickStats(years = 3, coffees = 1, bugs = 2),
            questions = emptyList()
        )

        val engineer3 = Engineer(
            name = "Bob",
            role = "Software",
            defaultImageName = "",
            quickStats = QuickStats(years = 2, coffees = 3, bugs = 1),
            questions = emptyList()
        )

        engineers = listOf(
            engineer1,
            engineer2,
            engineer3
        )

        expectedEngineersByYears = listOf(
            engineer2,
            engineer3,
            engineer1
        )

        expectedEngineersByCoffees = listOf(
            engineer3,
            engineer1,
            engineer2
        )

        expectedEngineersByBugs = listOf(
            engineer1,
            engineer2,
            engineer3
        )
    }

    @Test
    fun sortEngineersByQuickStatYears() {
        val sortedEngineers = engineers.sortedBy { it.quickStats.years }.reversed()
        assertEquals(expectedEngineersByYears, sortedEngineers)
    }

    @Test
    fun sortEngineersByQuickStatCoffees() {
        val sortedEngineers = engineers.sortedBy { it.quickStats.coffees }.reversed()
        assertEquals(expectedEngineersByCoffees, sortedEngineers)
    }

    @Test
    fun sortEngineersByQuickStatBugs() {
        val sortedEngineers = engineers.sortedBy { it.quickStats.bugs }.reversed()
        assertEquals(expectedEngineersByBugs, sortedEngineers)
    }
}