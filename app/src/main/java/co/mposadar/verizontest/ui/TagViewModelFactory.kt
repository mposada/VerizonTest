package co.mposadar.verizontest.ui

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import co.mposadar.verizontest.data.TagsRepository

class TagViewModelFactory(private val repository: TagsRepository): ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return TagViewModel(repository) as T
    }
}