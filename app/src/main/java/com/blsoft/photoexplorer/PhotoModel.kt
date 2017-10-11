package com.blsoft.photoexplorer

import android.os.Parcelable

interface PhotoModel {
    val photoId: String
    var photographer: String
    var location: String
    var title: String
    fun getThumbnailUrl(): String
    fun getUrl(): String
}