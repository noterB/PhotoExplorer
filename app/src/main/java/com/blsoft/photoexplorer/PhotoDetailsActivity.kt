package com.blsoft.photoexplorer

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_photo_details.*

class PhotoDetailsActivity : AppCompatActivity(), PhotoDetailsView {


    val presenter = PhotoDetailsPresenter(this, FlickrPhotosRepository)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        setContentView(R.layout.activity_photo_details)
        val photoId = intent.getStringExtra("photoId")
        Thread({
            presenter.getDetails(photoId)
        }).start()
    }

    override fun onDetailsLoaded(photoModel: PhotoModel) {
        runOnUiThread {
            Glide.with(this).load(photoModel.getUrl()).into(detailImage)
            photographerTextView.text = photoModel.photographer
            locationTextView.text = photoModel.location
            photoTitleTextView.text = photoModel.title
        }
    }

    override fun onDetailsNotLoaded() {
        Toast.makeText(this, "Unable to load details for image", Toast.LENGTH_SHORT).show()
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item?.itemId == android.R.id.home) {
            finish()
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}
