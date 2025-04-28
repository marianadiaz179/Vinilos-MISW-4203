package com.app.vinilos_misw4203.repositories

import android.app.Application
import android.util.Log
import com.android.volley.VolleyError
import com.app.vinilos_misw4203.network.NetworkServiceAdapter
import com.app.vinilos_misw4203.models.Album

class AlbumRepository(val application: Application) {

    companion object {
        private const val TAG = "AlbumRepository"
    }

    fun refreshData(callback: (List<Album>) -> Unit, onError: (VolleyError) -> Unit) {
        Log.d(TAG, "Iniciando carga de álbumes desde NetworkServiceAdapter...")

        NetworkServiceAdapter.getInstance(application).getAlbums({ albums ->
            Log.d(TAG, "Álbumes recibidos correctamente. Total: ${albums.size}")
            // Puedes imprimir el contenido si no es muy grande
            albums.forEach {
                Log.d(TAG, "Álbum: ${it.name} (ID: ${it.albumId}) - ${it.coverUrl}")
            }
            callback(albums)
        }, { error ->
            Log.e(TAG, "Error al obtener los álbumes", error)
            onError(error)
        })
    }
}