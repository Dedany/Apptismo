package com.dperez.apptismo.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Name")
data class Name(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val name: String
)
