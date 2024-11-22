package com.dperez.apptismo.data

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "Emotion")
data class Emotion(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val emotion: String
)
