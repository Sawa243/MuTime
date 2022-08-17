package com.sawacorp.mytime.storage

import androidx.lifecycle.LiveData
import androidx.room.*
import com.sawacorp.mytime.model.PieChartData

@Dao
interface StorageDao {
    @Query("SELECT * FROM storage_entity order by name asc")
    fun allStorageEntity(): LiveData<List<PieChartData.Slice>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertStorageEntity(list: List<PieChartData.Slice>)

    @Query("DELETE FROM storage_entity")
    suspend fun deleteAll()

    @Transaction
    suspend fun insertWithTransaction(list: List<PieChartData.Slice>) {
        deleteAll()
        insertStorageEntity(list)
    }

}