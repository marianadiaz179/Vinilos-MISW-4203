package com.app.vinilos_misw4203.viewmodels

import android.app.Application
import androidx.lifecycle.*
import com.app.vinilos_misw4203.models.Album
import com.app.vinilos_misw4203.repositories.AlbumDetailRepository

class AlbumDetailViewModel(application: Application) : AndroidViewModel(application) {

    private val albumDetailRepository = AlbumDetailRepository(application)

    private val _album = MutableLiveData<Album>()
    val album: LiveData<Album>
        get() = _album

    private var _eventNetworkError = MutableLiveData<Boolean>(false)
    val eventNetworkError: LiveData<Boolean>
        get() = _eventNetworkError

    private var _isNetworkErrorShown = MutableLiveData<Boolean>(false)
    val isNetworkErrorShown: LiveData<Boolean>
        get() = _isNetworkErrorShown

    fun refreshAlbumDetail(id: Int) {
        albumDetailRepository.refreshData(id, { album ->
            _album.postValue(album)
            _eventNetworkError.value = false
            _isNetworkErrorShown.value = false
        }, { 
            _eventNetworkError.value = true
        })
    }

    fun onNetworkErrorShown() {
        _isNetworkErrorShown.value = true
    }

    class Factory(private val app: Application) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(AlbumDetailViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return AlbumDetailViewModel(app) as T
            }
            throw IllegalArgumentException("Unable to construct AlbumDetailViewModel")
        }
    }
}