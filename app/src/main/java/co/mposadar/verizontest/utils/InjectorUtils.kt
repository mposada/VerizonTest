package co.mposadar.verizontest.utils

import android.content.Context
import co.mposadar.verizontest.AppExecutors
import co.mposadar.verizontest.data.TagsRepository
import co.mposadar.verizontest.data.database.TagsDatabase
import co.mposadar.verizontest.ui.TagViewModelFactory

class InjectorUtils {
    companion object {
        fun provideRepository(context: Context): TagsRepository {
            val database: TagsDatabase = TagsDatabase.getInstance(context)!!
            val executors = AppExecutors.instance
            return TagsRepository(database.tagDao(), executors)
        }

        fun provideTagViewModel(context: Context): TagViewModelFactory {
            val repository = provideRepository(context = context)
            return TagViewModelFactory(repository)
        }
    }
}