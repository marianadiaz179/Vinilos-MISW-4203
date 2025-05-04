package com.app.vinilos_misw4203.repositories

import android.app.Application
import android.util.Log
import com.android.volley.VolleyError
import com.app.vinilos_misw4203.network.NetworkServiceAdapter
import com.app.vinilos_misw4203.models.Album

class AlbumDetailRepository(val application: Application) {

    companion object {
        private const val TAG = "AlbumDetailRepository"
    }

    fun refreshData(id: Int, callback: (Album) -> Unit, onError: (VolleyError) -> Unit) {
        Log.d(TAG, "Iniciando carga de detalles del álbum con ID: $id")

        NetworkServiceAdapter.getInstance(application).getAlbum(id, { album ->
            Log.d(TAG, "Detalles del álbum recibidos correctamente: ${album.name} (ID: ${album.albumId})")
            callback(album)
        }, { error ->
            Log.e(TAG, "Error al obtener los detalles del álbum", error)
            onError(error)
        })
    }
}