package com.app.vinilos_misw4203.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.app.vinilos_misw4203.models.Performer

class PerformerViewModel(application: Application) : AndroidViewModel(application) {

    private val _performers = MutableLiveData<List<Performer>>()
    val performers: LiveData<List<Performer>>
        get() = _performers

    private val _eventNetworkError = MutableLiveData(false)
    val eventNetworkError: LiveData<Boolean>
        get() = _eventNetworkError

    private val _isNetworkErrorShown = MutableLiveData(false)
    val isNetworkErrorShown: LiveData<Boolean>
        get() = _isNetworkErrorShown

    init {
        refreshDataFromNetwork()
    }

    private fun refreshDataFromNetwork() {
        AlbumViewModel.NetworkServiceAdapter
            .getInstance(getApplication())
            .getPerformers(
                {
                    val it = null
                    _performers.postValue(it)
                    _eventNetworkError.value = false
                    _isNetworkErrorShown.value = false
                },
                {
                    _eventNetworkError.value = true
                }
            )
    }

    fun onNetworkErrorShown() {
        _isNetworkErrorShown.value = true
    }

    class Factory(private val app: Application) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(PerformerViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return PerformerViewModel(app) as T
            }
            throw IllegalArgumentException("Unable to construct PerformerViewModel")
        }
    }
}

private fun Any.getPerformers(any: Any, function: () -> Unit) {

}
