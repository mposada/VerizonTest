package co.mposadar.verizontest.data.database

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context

@Database(entities = [(TagEntry::class)], version = 1)
abstract class TagsDatabase: RoomDatabase () {

    companion object {
        private const val DATABASE_NAME = "verizon"
        private var instance: TagsDatabase? = null

        fun getInstance(context: Context): TagsDatabase? {
            if (instance == null) {
                synchronized(TagsDatabase::class) {
                    instance = Room.databaseBuilder(
                            context.applicationContext,
                            TagsDatabase::class.java,
                            DATABASE_NAME).build()
                }
            }

            return instance
        }
    }

    abstract fun tagDao(): TagDao
}