package com.apptive.myapplication.viewmodel

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.apptive.myapplication.model.Habit
import java.time.LocalDate

class MainViewModel : ViewModel() {
    val habits = mutableStateListOf<Habit>()

    fun addHabit(name: String) {
        if (name.isBlank()) return

        val newId = (habits.maxOfOrNull { it.id } ?: 0) + 1

        habits.add(Habit(newId, name))
    }

    fun removeHabit(habit: Habit) {
        habits.remove(habit)
    }

    fun toggleHabit(habit: Habit) {
        val today = LocalDate.now()
        val updatedDates = if (habit.isCompletedOn(today)) {
            habit.completionDates - today
        } else {
            habit.completionDates + today
        }

        val habitIndex = habits.indexOfFirst { it.id == habit.id }
        if (habitIndex != -1) {
            habits[habitIndex] = habit.copy(completionDates = updatedDates)
        }
    }
}
