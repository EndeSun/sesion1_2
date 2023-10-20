package es.ua.eps.endesun_redes2

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Button
import android.widget.ImageView
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts

private const val IMAGE = 1

@Suppress("DEPRECATION")
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val btPhoto = findViewById<Button>(R.id.btPhoto)
        btPhoto.setOnClickListener{
            val cameraIntent =  Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            if(Build.VERSION.SDK_INT >= 30) {
                startForResult.launch(cameraIntent)
            }
            else {
                @Suppress("DEPRECATION")
                startActivityForResult(cameraIntent, IMAGE)
            }
        }
    }

    private val startForResult =
        registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()) { result: ActivityResult ->
            onActivityResult(IMAGE, result.resultCode, result.data)
        }
    @Deprecated("Deprecated in Java")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == IMAGE && resultCode == Activity.RESULT_OK && data != null) {
            val imageBitmap = data.extras?.get("data") as Bitmap
            val photo: ImageView = findViewById(R.id.photoScreen)
            photo.setImageBitmap(imageBitmap)
        }
    }
}