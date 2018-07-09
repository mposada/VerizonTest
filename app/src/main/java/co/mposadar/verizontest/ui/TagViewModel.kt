package co.mposadar.verizontest.ui

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import co.mposadar.verizontest.data.TagsRepository
import co.mposadar.verizontest.data.database.TagEntry

class TagViewModel(tagsRepository: TagsRepository): ViewModel() {

    companion object {
        var tags: LiveData<List<TagEntry>>? = null
    }

    init {
        tags = tagsRepository.getTags()
    }

    fun getTags(): LiveData<List<TagEntry>> = tags!!
}