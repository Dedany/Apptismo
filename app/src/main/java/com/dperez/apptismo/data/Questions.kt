package com.dperez.apptismo.data


import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "Questions")
data class Questions(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val questions: String
)
