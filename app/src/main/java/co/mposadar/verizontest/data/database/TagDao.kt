package co.mposadar.verizontest.data.database

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query

@Dao
interface TagDao {
    @Query("SELECT * FROM tags")
    fun getTags(): LiveData<List<TagEntry>>

    @Insert
    fun insert(tag: TagEntry)

    @Insert
    @JvmSuppressWildcards
    fun bulkInsert(tags: List<TagEntry>)
}