package com.jhr.abdallahsarayrah.fbproh

import android.content.Intent
import android.graphics.Bitmap
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import kotlinx.android.synthetic.main.activity_upload.*
import java.io.ByteArrayOutputStream

class UploadActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_upload)

        imageView.setOnClickListener {
            val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            startActivityForResult(intent, 1)
        }

        button_upload.setOnClickListener {
            imageView.isDrawingCacheEnabled = true
            imageView.buildDrawingCache()

            val bmp = imageView.drawingCache
            val baos = ByteArrayOutputStream()
            bmp.compress(Bitmap.CompressFormat.JPEG, 100, baos)
            val data = baos.toByteArray()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        val bmp = data?.extras?.get("data") as Bitmap
        imageView.setImageBitmap(bmp)
    }
}
