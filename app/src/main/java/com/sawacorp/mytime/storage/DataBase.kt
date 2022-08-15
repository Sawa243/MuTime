package com.sawacorp.mytime.storage

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.sawacorp.mytime.model.PieChartData

@Database(
    entities = [PieChartData.Slice::class],
    version = 1,
    exportSchema = false
)
@TypeConverters()
abstract class DataBase : RoomDatabase() {
    abstract fun storageDao(): StorageDao

    companion object {
        @Volatile
        private var INSTANCE: DataBase? = null
        fun getDatabase(context: Context): DataBase {
            val tempInstance = INSTANCE
            if (tempInstance != null)
                return tempInstance
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    DataBase::class.java,
                    "Database"
                )
                    .build()
                INSTANCE = instance
                return instance
            }
        }
    }

}