package com.example.fileprovidertest

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ShareCompat
import androidx.core.content.FileProvider
import java.io.File
import java.io.IOException

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val captureFile = this.createOutputFile()
        val contentUri = FileProvider.getUriForFile(this.applicationContext, BuildConfig.APPLICATION_ID + ".provider", captureFile)
        println("⭐️⭐️⭐️" + contentUri + "⭐️⭐️⭐️")

        var btn = findViewById<Button>(R.id.testBtn)
        btn.setOnClickListener{
            val intent = ShareCompat.IntentBuilder.from(this)
                .setType("text/plain")
                .setStream(contentUri)
//            .setChooserTitle("Choose bar")
                .createChooserIntent()
                .addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
                .setClassName("com.example.fileprovidertesttarget"
                    ,"com.example.fileprovidertesttarget.MainActivity")
                .setAction("android.intent.category.LAUNCHER")

            this.applicationContext.startActivity(intent)

        }
//        val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
//        this.cameraContentUri = createOutputUri()
//        cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, this.cameraContentUri)
//        cameraIntent.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION)
//        this.activity.startActivityForResult(cameraIntent, REQUEST_CODE)

//        Intent intent = new Intent(Intent.ACTION_MAIN); //act
//        intent.setAction("android.intent.category.LAUNCHER") // cat
//        intent.setClassName("com.google.android.apps.plus",
//            "com.google.android.apps.plus.phone.HomeActivity"); // cmp 省略せずに書く
//        intent.stre
//        intent.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION)
//        startActivity(intent);
    }
    fun createOutputFile(): File {
//        val timeStamp = DateFormat.format("yyyyMMdd_HHmmss", Date()).toString()
//        val tempFile = File( this.activity.filesDir, "/my_images/$timeStamp.jpg")
        val tempFile = File( this.applicationContext.filesDir, "/docs/shareFile.txt")
        if (!tempFile.exists()) {
            try {
                tempFile.parentFile.mkdirs()
                tempFile.createNewFile()
                tempFile.writeText("hoge")
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
        return tempFile
    }
}