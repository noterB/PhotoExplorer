package com.blsoft.photoexplorer

import org.json.JSONObject
import java.net.HttpURLConnection
import java.net.URL
import java.net.URLEncoder

/**
 * Created by developer on 10/9/17.
 */
class FlickrPhotosRepository : PhotosRepository {

    val apiKey = "211f3354c5cb8ab4a43eebeaf50f6458"

    override fun getPhotosFor(query: String): List<PhotoModel> {
        val encodedUrl = URLEncoder.encode(query, "UTF-8");
        val url = URL("https://api.flickr.com/services/rest/?method=flickr.photos.search&api_key=${apiKey}&text=${encodedUrl}&per_page=20&format=json&nojsoncallback=1")
        val conn = url.openConnection() as HttpURLConnection
        conn.connect()
        val jsonString = conn.inputStream.reader().readText()
        conn.disconnect()
        val jsonObject = JSONObject(jsonString)
        print(jsonObject)
        return listOf()
    }
}