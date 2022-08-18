package com.sawacorp.mytime.repository

import androidx.lifecycle.LiveData
import com.sawacorp.mytime.model.PieChartData
import com.sawacorp.mytime.storage.DataBase
import javax.inject.Singleton

@Singleton
class SliceRepository(dataBase: DataBase) {

    private val storageDao = dataBase.storageDao()

    val allSlice: LiveData<List<PieChartData.Slice>> = storageDao.allStorageEntity()

    suspend fun insert(sliceList: List<PieChartData.Slice>) {
        storageDao.insertStorageEntity(sliceList)
    }
}