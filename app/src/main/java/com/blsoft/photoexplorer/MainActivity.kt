package com.blsoft.photoexplorer

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.ImageView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity(), PhotosView {


    private val presenter = PhotosPresenter(this, FlickrPhotosRepository)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val cols = (resources.displayMetrics.widthPixels / resources.displayMetrics.density) / 150
        recyclerView.layoutManager = GridLayoutManager(this, cols.toInt())
        recyclerView.adapter = PhotosAdapter(listOf())

        presenter.getLastQuery()

        searchButton.setOnClickListener({
            fetchPhotos()
        })

        searchInput.setOnFocusChangeListener { view, hasFocus ->
            if (!hasFocus) {
                hideKeyboard()
            }
        }
    }

    private fun fetchPhotos() {
        if (searchInput.text.isNotBlank()) {
            Thread(Runnable {
                presenter.getPhotosFor(searchInput.text.toString())
            }).start()
        }
        recyclerView.requestFocus()
    }

    override fun onLastQuery(query: String) {
        searchInput.setText(query)
        fetchPhotos()
    }

    private fun hideKeyboard() {
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(searchInput.windowToken, 0)
    }

    override fun onPhotoList(photos: List<PhotoModel>) {
        runOnUiThread {
            recyclerView.adapter = PhotosAdapter(photos)
        }
    }
}


class PhotosAdapter(private val list: List<PhotoModel>) : RecyclerView.Adapter<PhotosAdapter.ViewHolder>() {

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val thumbnailUrl = list[position].getThumbnailUrl()
        Glide.with(holder.itemView.context).load(thumbnailUrl).into(holder.image)
        holder.itemView.setOnClickListener {
            val intent = Intent(it.context, PhotoDetailsActivity::class.java)
            intent.putExtra("photoId", list[position].photoId)
            it.context.startActivity(intent)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent?.context).inflate(R.layout.photo_item_layout, parent, false)
        return ViewHolder(view)
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val image: ImageView = itemView.findViewById(R.id.photoView)
    }
}