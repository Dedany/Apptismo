package com.dperez.apptismo.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "DataName")
data class User(
    @PrimaryKey val name: String // Clave primaria (única)
)
