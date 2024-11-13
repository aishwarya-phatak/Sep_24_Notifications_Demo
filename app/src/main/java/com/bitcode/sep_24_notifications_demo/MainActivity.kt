package com.bitcode.sep_24_notifications_demo

import android.Manifest
import android.annotation.SuppressLint
import android.app.PendingIntent
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationChannelCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.bitcode.sep_24_notifications_demo.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var activityMainBinding: ActivityMainBinding
    private lateinit var notificationManager : NotificationManagerCompat
    private val channel_id = "bitcodeChannel"

    private val SIMPLE_NOTIFICATION = 1
    private val INBOX_STYLE_NOTIFICATION = 2
    private val ACTION_TEXT_STYLE_NOTIFICATION = 3
    private val SNACK_BAR_NOTIFICATION = 4
    private val BIG_PICTURE_STYLE_NOTIFICATION = 5

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        notificationManager = NotificationManagerCompat.from(this)
        setContentView(activityMainBinding.root)
        createNotificationChannel()
        activityMainBinding.btnSimpleNotification.setOnClickListener {
            simpleNotification()
        }
    }

    @SuppressLint("MissingPermission")
    private fun simpleNotification(){
        var notificationBuilder = NotificationCompat.Builder(this,channel_id)
        notificationBuilder.setColor(155)
//        notificationBuilder.setSound()
        notificationBuilder.setContentTitle("Simple Notification")
        notificationBuilder.setContentText("Batches Launching In December 2024")
        notificationBuilder.setLights(55,20,20)
        notificationBuilder.setSmallIcon(R.drawable.ic_launcher_background)
        notificationBuilder.setPriority(NotificationCompat.PRIORITY_HIGH)
        notificationBuilder.setAutoCancel(false)
        notificationBuilder.setVibrate(LongArray(10))
        var intent = Intent(this,SecondActivity::class.java)
        var pendingIntent = PendingIntent.getActivity(this,
            1,
            intent,
            PendingIntent.FLAG_MUTABLE)
        notificationBuilder.setContentIntent(pendingIntent)
        notificationBuilder.build()

        notificationManager.notify(SIMPLE_NOTIFICATION,notificationBuilder.build())
    }

    private fun inboxStyleNotification(){

    }

    fun actionTextStyleNotification(){

    }

    fun snackBarNotification(){

    }

    fun bigPictureStyleNotification(){

    }

    fun createNotificationChannel(){
        var notificationChannel = NotificationChannelCompat.Builder(channel_id,
            NotificationManagerCompat.IMPORTANCE_MAX)
        notificationChannel.setName("Bitcode")
        notificationChannel.setDescription("This is Bitcode Notification Channel")
        notificationChannel.setLightColor(100)
        notificationChannel.setVibrationEnabled(true)
        notificationChannel.setLightsEnabled(true)

        notificationManager.createNotificationChannel(notificationChannel.build())
    }
}