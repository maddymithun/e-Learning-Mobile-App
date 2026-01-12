package com.example.composecleanarchitecture.models.tomo_sequence

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class BCSTomoDataFormation (
    @PrimaryKey(autoGenerate = true) val columnId:Int=0,
    val sequence:String,
    val categoryCode:String,
    val bcsYear:String,
)