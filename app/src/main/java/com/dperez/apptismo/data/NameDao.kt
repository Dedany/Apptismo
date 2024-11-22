package com.dperez.apptismo.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.RewriteQueriesToDropUnusedColumns

@Dao
@RewriteQueriesToDropUnusedColumns // Esto soluciona advertencias sobre columnas no utilizadas
interface NameDao {

    @Query("SELECT * FROM Name WHERE id = :id LIMIT 1")
    fun getName(id: Int): Name?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrUpdateName(name: Name): Long // Retorna un Long para el ID generado
}
