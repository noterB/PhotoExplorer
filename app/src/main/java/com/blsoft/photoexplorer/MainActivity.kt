package com.blsoft.photoexplorer

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity(), PhotosView {

    private val presenter = PhotosPresenter(this, FlickrPhotosRepository())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView.layoutManager = GridLayoutManager(this, 2)
        recyclerView.adapter = PhotosAdapter(listOf())

        searchButton.setOnClickListener({
            if (searchInput.text.isNotBlank()) {
                Thread(Runnable {
                    presenter.getPhotosFor(searchInput.text.toString())
                }).start()
            }
        })
    }

    override fun onPhotoList(photos: List<PhotoModel>) {
        runOnUiThread {
            recyclerView.adapter = PhotosAdapter(photos)
        }
    }
}



class PhotosAdapter(val list: List<PhotoModel>) : RecyclerView.Adapter<PhotosAdapter.ViewHolder>() {

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val thumbnailUrl = list[position].getThumbnailUrl()
        Glide.with(holder.itemView.context).load(thumbnailUrl).into(holder.image)
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent?.context).inflate(R.layout.photo_item_layout, parent, false)
        return ViewHolder(view)
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val image: ImageView = itemView.findViewById(R.id.photoView)
    }
}