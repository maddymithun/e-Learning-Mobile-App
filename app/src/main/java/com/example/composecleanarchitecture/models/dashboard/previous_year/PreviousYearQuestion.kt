package com.example.composecleanarchitecture.models.dashboard.previous_year

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
data class PreviousYearQuestion(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val isTracerEnable: Boolean,
    val qsnStatus: String,
    val qsnCategory: String,
    val qsnId: String,
    val imageVector: Int
)

@Entity
data class OrganizationCategory(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val isTracerEnable: Boolean,
    val qsnStatus: String,
    val qsnCategory: String,
    val qsnId: String,
    val imageVector: Int
)
