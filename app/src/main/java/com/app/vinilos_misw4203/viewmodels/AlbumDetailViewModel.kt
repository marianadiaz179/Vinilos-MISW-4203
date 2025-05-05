package com.app.vinilos_misw4203.viewmodels

import android.app.Application
import androidx.lifecycle.*
import com.app.vinilos_misw4203.models.Album
import com.app.vinilos_misw4203.models.Comment
import com.app.vinilos_misw4203.repositories.AlbumDetailRepository
import com.app.vinilos_misw4203.repositories.CommentsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class AlbumDetailViewModel(application: Application) : AndroidViewModel(application) {

    private val albumDetailRepository = AlbumDetailRepository(application)
    private val commentsRepository = CommentsRepository(application)

    private val _album = MutableLiveData<Album>()
    val album: LiveData<Album>
        get() = _album

    private val _comments = MutableLiveData<List<Comment>>()
    val comments: LiveData<List<Comment>>
        get() = _comments

    private var _eventNetworkError = MutableLiveData<Boolean>(false)
    val eventNetworkError: LiveData<Boolean>
        get() = _eventNetworkError

    private var _isNetworkErrorShown = MutableLiveData<Boolean>(false)
    val isNetworkErrorShown: LiveData<Boolean>
        get() = _isNetworkErrorShown

    fun refreshAlbumDetail(id: Int) {
        try {
            viewModelScope.launch(Dispatchers.Default) {
                withContext(Dispatchers.IO) {
                    val albumData = albumDetailRepository.refreshData(id)
                    val commentsData = commentsRepository.refreshData(id)

                    _album.postValue(albumData)
                    _comments.postValue(commentsData)
                }
                _eventNetworkError.postValue(false)
                _isNetworkErrorShown.postValue(false)
            }
        } catch (e: Exception) {
            _eventNetworkError.value = true
        }
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