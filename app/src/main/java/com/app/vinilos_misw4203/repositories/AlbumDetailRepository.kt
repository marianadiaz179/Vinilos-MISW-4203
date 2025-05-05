package com.app.vinilos_misw4203.repositories

import android.app.Application
import android.util.Log
import com.android.volley.VolleyError
import com.app.vinilos_misw4203.network.NetworkServiceAdapter
import com.app.vinilos_misw4203.models.Album

class AlbumDetailRepository(val application: Application) {

    suspend fun refreshData(id: Int): Album {
        return NetworkServiceAdapter.getInstance(application).getAlbum(id)
    }
}