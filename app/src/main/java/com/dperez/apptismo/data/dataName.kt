package com.dperez.apptismo.data
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DataName(val name: String) {
    // Método opcional para mostrar el nombre si lo necesitas
    fun printName() {
        println("El nombre es: $name")
    }
}