package co.mposadar.verizontest.data

import android.arch.lifecycle.LiveData
import co.mposadar.verizontest.AppExecutors
import co.mposadar.verizontest.data.database.TagDao
import co.mposadar.verizontest.data.database.TagEntry
import co.mposadar.verizontest.utils.HelperFunctions
import co.mposadar.verizontest.utils.random

class TagsRepository(val tagDao: TagDao, val appExecutors: AppExecutors) {

    fun getTags(): LiveData<List<TagEntry>> = tagDao.getTags()

    fun saveTag(name: String){
        appExecutors.diskIO().execute {
            val color = HelperFunctions.getColor()
            val tag = TagEntry(name = name, color = color)
            tagDao.insert(tag)
        }
    }

    fun bulkInsert(tags: List<TagEntry>) {
        appExecutors.diskIO().execute {
            val newList: ArrayList<TagEntry> = ArrayList()
            val listSize = (tags!!.size - 1)
            for (i in 0..999) {
                val randomNumber = (0..listSize).random()
                val name = tags[randomNumber].name
                val color = HelperFunctions.getColor()
                val tag = TagEntry(name = name, color = color)
                newList.add(tag)
            }
            tagDao.bulkInsert(newList)
        }
    }
}