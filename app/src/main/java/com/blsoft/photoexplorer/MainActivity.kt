package com.blsoft.photoexplorer

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity(), PhotosView {

    val presenter = PhotosPresenter(this, FlickrPhotosRepository())

    override fun onPhotoList(photos: List<PhotoModel>) {
        runOnUiThread {
            recyclerView.adapter = PhotosAdapter(photos)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Thread(Runnable {
            presenter.getPhotosFor("dog")
        }).start()

        val dpWidth = resources.displayMetrics.widthPixels / resources.displayMetrics.density
        val cols = (dpWidth / 168).toInt()

        recyclerView.layoutManager = GridLayoutManager(this, cols)
        recyclerView.adapter = PhotosAdapter(listOf())
    }
}

class PhotosAdapter(val list: List<PhotoModel>) : RecyclerView.Adapter<PhotosAdapter.ViewHolder>() {

    override fun getItemCount(): Int {
        return 20//list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent?.context).inflate(R.layout.photo_item_layout, parent, false)
        return ViewHolder(view)
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
//        val image: ImageView = itemView.findViewById(R.id.photoView)
    }
}