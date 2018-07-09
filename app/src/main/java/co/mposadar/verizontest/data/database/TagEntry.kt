package co.mposadar.verizontest.data.database

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "tags")
data class TagEntry(@PrimaryKey(autoGenerate = true) val id: Int = 0, val name: String, val color: Int)