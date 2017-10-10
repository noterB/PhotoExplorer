package com.blsoft.photoexplorer

import org.json.JSONObject
import java.net.HttpURLConnection
import java.net.URL
import java.net.URLEncoder


object FlickrPhotosRepository : PhotosRepository {



    private val apiKey = "d83d183927fd2af478dfd9a3633e6524"
    private var lastSearch:Map<String, List<PhotoModel>> = mapOf()
    private var lastQuery: String = ""

    override fun getLastQuery(): String {
        return lastQuery
    }

    override fun getPhotosFor(query: String): List<PhotoModel> {
        lastQuery = query
        if (lastSearch.containsKey(query)) {
            lastSearch[query]?.let {
                lastQuery = query
                return it
            }
        }
        val encodedUrl = URLEncoder.encode(query, "UTF-8")
        val url = URL("https://api.flickr.com/services/rest/?method=flickr.photos.search&api_key=$apiKey&text=$encodedUrl&per_page=20&format=json&nojsoncallback=1")
        val conn = url.openConnection() as HttpURLConnection
        conn.connect()
        val jsonString = conn.inputStream.reader().readText()
        conn.disconnect()
        val jsonObject = JSONObject(jsonString).getJSONObject("photos")
        val jsonPhotos = jsonObject.getJSONArray("photo")
        val photos = mutableListOf<PhotoModel>()
        for (i in 0 until jsonPhotos.length()) {
            val jsonPhoto = jsonPhotos.getJSONObject(i)
            val flickrPhotoModel = FlickrPhotoModel(jsonPhoto.getString("id"), jsonPhoto.getInt("farm"), jsonPhoto.getString("server"), jsonPhoto.getString("secret"))
            photos.add(flickrPhotoModel)
        }

        val list = photos.toList()
        lastSearch = mapOf(query to list)
        return list
    }
}