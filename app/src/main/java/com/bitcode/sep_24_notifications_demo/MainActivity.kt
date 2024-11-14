package com.bitcode.sep_24_notifications_demo

import android.Manifest
import android.annotation.SuppressLint
import android.app.PendingIntent
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationChannelCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.bitcode.sep_24_notifications_demo.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {
    private lateinit var activityMainBinding: ActivityMainBinding
    private lateinit var notificationManager : NotificationManagerCompat
    private val channel_id = "bitcodeChannel"

    private val SIMPLE_NOTIFICATION = 1
    private val INBOX_STYLE_NOTIFICATION = 2
    private val ACTION_TEXT_STYLE_NOTIFICATION = 3
    private val SNACK_BAR_NOTIFICATION = 4
    private val BIG_PICTURE_STYLE_NOTIFICATION = 5

    @RequiresApi(Build.VERSION_CODES.S)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        notificationManager = NotificationManagerCompat.from(this)
        setContentView(activityMainBinding.root)
        createNotificationChannel()
        activityMainBinding.btnSimpleNotification.setOnClickListener {
            simpleNotification()
        }

        activityMainBinding.btnInboxStyleNotification.setOnClickListener {
            inboxStyleNotification()
        }

        activityMainBinding.btnActionStyleNotification.setOnClickListener {
            actionTextStyleNotification()
        }

        activityMainBinding.btnSnackBarNotification.setOnClickListener {
            snackBarNotification()
        }

        activityMainBinding.btnBigPictureStyleNotification.setOnClickListener {
            bigPictureStyleNotification()
        }

        activityMainBinding.btnSnackBarNotification.setOnClickListener {
            snackBarNotification()
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

    @SuppressLint("MissingPermission")
    private fun inboxStyleNotification(){
        var notificationBuilder = NotificationCompat.Builder(this,channel_id)
        notificationBuilder.setContentTitle("New Upcoming Batches")
        notificationBuilder.setColor(155)
        notificationBuilder.setPriority(NotificationCompat.PRIORITY_HIGH)
        notificationBuilder.setSmallIcon(R.drawable.ic_launcher_background)

        var inboxStyleNotification = NotificationCompat.InboxStyle()
        inboxStyleNotification.addLine("iOS - Nov 24")
        inboxStyleNotification.addLine("Android - Nov 24")
        inboxStyleNotification.addLine("Web - Nov 24")
        inboxStyleNotification.setSummaryText("Contact Bitcode For More Information")
        inboxStyleNotification.setBigContentTitle("Bitcode Technologies Batches Schedule")

        notificationBuilder.setStyle(inboxStyleNotification)

        notificationManager.notify(INBOX_STYLE_NOTIFICATION, inboxStyleNotification.build()!!)
    }

    @SuppressLint("MissingPermission")
    private fun actionTextStyleNotification(){
        var notificationBuilder = NotificationCompat.Builder(this,channel_id)
        notificationBuilder.setContentTitle("Action text Style")
        notificationBuilder.setPriority(NotificationCompat.PRIORITY_LOW)
        notificationBuilder.setColor(200)
        notificationBuilder.setAutoCancel(false)
        notificationBuilder.setLights(200,50,50)
        notificationBuilder.setOngoing(true)
//        var bitmapIcon = BitmapFactory.decodeResource(resources,R.drawable.ic_launcher_background)
//        notificationBuilder.setLargeIcon(bitmapIcon)
        notificationBuilder.setSmallIcon(R.drawable.ic_launcher_background)
        var intent = Intent(this,SecondActivity::class.java)
        var pendingIntent = PendingIntent.getActivity(this,
            1,
            intent,
            PendingIntent.FLAG_MUTABLE)

        var actionTextStyle = NotificationCompat.Action(
            R.drawable.ic_launcher_background,
            "Action Text Style",
            pendingIntent
        )

        notificationBuilder.addAction(actionTextStyle)

        notificationManager.notify(ACTION_TEXT_STYLE_NOTIFICATION,notificationBuilder.build())
    }

    @RequiresApi(Build.VERSION_CODES.S)
    @SuppressLint("MissingPermission")
    fun bigPictureStyleNotification(){
        var notificationBuilder = NotificationCompat.Builder(this,channel_id)
        notificationBuilder.setPriority(NotificationCompat.PRIORITY_MIN)
        notificationBuilder.setColor(100)
        notificationBuilder.setVibrate(LongArray(10) { index -> (index + 10).toLong() })
        notificationBuilder.setCategory(NotificationCompat.CATEGORY_MESSAGE)
        notificationBuilder.setContentTitle("Big Picture Style Notification")
        notificationBuilder.setOngoing(true)
        notificationBuilder.setContentText("This is Android Batch Sep 24")
        notificationBuilder.setLights(144,50,50)
        notificationBuilder.setSmallIcon(R.drawable.ic_launcher_background)
        var bigPictureStyle = NotificationCompat.BigPictureStyle()
        bigPictureStyle.setBigContentTitle("Bitcode Youtube Channel")

        var bitmapIcon = BitmapFactory.decodeResource(resources,R.drawable.test_image_3)
//        activityMainBinding.imgView.setImageBitmap(bitmapIcon)

        bigPictureStyle.bigPicture(bitmapIcon)

//        bigPictureStyle.bigLargeIcon(Bitmap.createBitmap(BitmapFactory.decodeResource(resources,R.drawable.test_image_3)))

        bigPictureStyle.setContentDescription("This channel is for videos " +
                "on technologies like iOS, Android, Web")
        notificationManager.notify(BIG_PICTURE_STYLE_NOTIFICATION,notificationBuilder.build())
    }

    @SuppressLint("MissingPermission")
    fun snackBarNotification(){
//        var snackBar = Snackbar.make(this,
//
//            "Snack Bar",
//            Snackbar.LENGTH_LONG)
//        snackBar.setText("Snack bar")

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