package com.app.vinilos_misw4203.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.appcompat.app.AppCompatActivity
import com.app.vinilos_misw4203.R
import com.app.vinilos_misw4203.databinding.AlbumDetailFragmentBinding
import com.app.vinilos_misw4203.models.Album
import com.app.vinilos_misw4203.viewmodels.AlbumDetailViewModel

class AlbumDetailFragment : Fragment() {
    private var _binding: AlbumDetailFragmentBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: AlbumDetailViewModel
    private var albumId: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            val args = AlbumDetailFragmentArgs.fromBundle(it) // Safe Args
            albumId = args.albumId
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = AlbumDetailFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val activity = requireNotNull(this.activity)
        viewModel = ViewModelProvider(this, AlbumDetailViewModel.Factory(activity.application))
            .get(AlbumDetailViewModel::class.java)

        showLoading(true)

        viewModel.album.observe(viewLifecycleOwner, Observer<Album> {
            it?.let { 
                updateUI(it) 
                showLoading(false)
            }
        })

        viewModel.eventNetworkError.observe(viewLifecycleOwner, Observer<Boolean> { isNetworkError ->
            if (isNetworkError) onNetworkError()
        })

        viewModel.refreshAlbumDetail(albumId)
    }

    private fun updateUI(album: Album) {
        binding.albumName.text = album.name
        binding.albumGenre.text = album.genre
        binding.albumReleaseDate.text = album.releaseDate.substring(0,4)
        binding.albumDescription.text = album.description
        binding.albumImage.setImageResource(R.drawable.album)
        (requireActivity() as AppCompatActivity).supportActionBar?.title = album.name

    }

    private fun onNetworkError() {
        if (!viewModel.isNetworkErrorShown.value!!) {
            Toast.makeText(activity, "Network Error", Toast.LENGTH_LONG).show()
            viewModel.onNetworkErrorShown()
            showLoading(false)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun showLoading(isLoading: Boolean) {
        binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
        binding.albumDetailFragment.visibility = if (isLoading) View.GONE else View.VISIBLE
    }
}