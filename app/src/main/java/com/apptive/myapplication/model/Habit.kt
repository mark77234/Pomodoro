package com.apptive.myapplication.model
import java.time.LocalDate
data class Habit(
    val id: Int,
    val name: String,
    val completionDates: MutableList<LocalDate> = mutableListOf()
) {
    //HomeScreen의 text 속성을 name을 통해 접근
    val text: String
        get() = name

    //isDone 대신 isCompletedOn
    fun isCompletedOn(date: LocalDate): Boolean {
        return completionDates.contains(date)
    }
}
