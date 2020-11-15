package com.elnaggar.ibtikartask.features.image

import android.app.DownloadManager
import android.content.Context
import android.database.Cursor
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import coil.load
import com.elnaggar.ibtikartask.UrlValidator
import com.elnaggar.ibtikartask.databinding.ImageActivityBinding
import com.elnaggar.ibtikartask.features.personDetails.IMAGE_URL_KEY
import java.io.File

class ImageActivity : AppCompatActivity() {

    var msg: String? = ""
    var lastMsg = ""

    lateinit var binding: ImageActivityBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ImageActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val imageUrl = intent.getStringExtra(IMAGE_URL_KEY)
        if (imageUrl != null) {
            binding.imageIv.load(imageUrl)
            binding.downloadBt.isVisible = true
            binding.downloadBt.setOnClickListener {
                downloadImage(imageUrl)
            }
        } else {
            binding.downloadBt.isVisible = false
        }
    }

    private fun downloadImage(url: String) {
        val result = UrlValidator.isValidImageUrl(url)
        if (result) {
            val directory = File(Environment.DIRECTORY_PICTURES)

            if (!directory.exists()) {
                directory.mkdirs()
            }
            val downloadManager = this.getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager

            val downloadUri = Uri.parse(url)

            val request = DownloadManager.Request(downloadUri).apply {
                setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI or DownloadManager.Request.NETWORK_MOBILE)
                        .setAllowedOverRoaming(false)
                        .setTitle(url.substring(url.lastIndexOf("/") + 1))
                        .setDescription("")
                        .setDestinationInExternalPublicDir(
                                directory.toString(),
                                url.substring(url.lastIndexOf("/") + 1)
                        )
            }
            val downloadId = downloadManager.enqueue(request)
            val query = DownloadManager.Query().setFilterById(downloadId)
            Thread(Runnable {
                var downloading = true
                while (downloading) {
                    val cursor: Cursor = downloadManager.query(query)
                    cursor.moveToFirst()
                    if (cursor.getInt(cursor.getColumnIndex(DownloadManager.COLUMN_STATUS)) == DownloadManager.STATUS_SUCCESSFUL) {
                        downloading = false
                    }
                    val status = cursor.getInt(cursor.getColumnIndex(DownloadManager.COLUMN_STATUS))
                    msg = statusMessage(url, directory, status)
                    if (msg != lastMsg) {
                        this.runOnUiThread {
                            Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
                        }
                        lastMsg = msg ?: ""
                    }
                    cursor.close()
                }
            }).start()
        }else{
            Toast.makeText(this, "Not valid Url", Toast.LENGTH_SHORT).show()

        }
    }

    private fun statusMessage(url: String, directory: File, status: Int): String? {
        var msg = ""
        msg = when (status) {
            DownloadManager.STATUS_FAILED -> "Download has been failed, please try again"
            DownloadManager.STATUS_PAUSED -> "Paused"
            DownloadManager.STATUS_PENDING -> "Pending"
            DownloadManager.STATUS_RUNNING -> "Downloading..."
            DownloadManager.STATUS_SUCCESSFUL -> "Image downloaded successfully in $directory" + File.separator + url.substring(
                url.lastIndexOf("/") + 1
            )
            else -> "There's nothing to download"
        }
        return msg
    }


}

