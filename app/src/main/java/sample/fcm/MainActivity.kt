package sample.fcm

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.FirebaseApp
import com.google.firebase.installations.FirebaseInstallations
import com.google.firebase.messaging.FirebaseMessaging


class MainActivity : AppCompatActivity() {

    private val TAG = "sfMain"
    lateinit var context: Context

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        context = this

        val button = findViewById<Button>(R.id.button)
        button.setOnClickListener {
            //Initialize firebase, if not done
            FirebaseApp.initializeApp(context)
            FirebaseMessaging.getInstance().isAutoInitEnabled = true;
            // Log tokens for FCM and FIAM
            FirebaseMessaging.getInstance().token.addOnCompleteListener(OnCompleteListener { task ->
                if (!task.isSuccessful) {
                    Log.w(TAG, "Fetching FCM registration token failed", task.exception)
                    return@OnCompleteListener
                }

                // Get new FCM registration token
                val token = task.result

                // Log and toast
                Log.d(TAG, "FCM Token is: $token")
                Toast.makeText(baseContext, "FCM Token is: $token", Toast.LENGTH_SHORT).show()
            })
            FirebaseInstallations.getInstance().id.addOnCompleteListener { installationIdTask ->
                if (installationIdTask.isSuccessful) {
                    Log.d(TAG, "Firebase Installations ID: ${installationIdTask.result}")
                } else {
                    Log.e(TAG, "Unable to retrieve installations ID ${installationIdTask.exception}")
                }
            }
            FirebaseInstallations.getInstance().getToken(/* forceRefresh */ true)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        Log.d(TAG, "Installation auth token: ${task.result?.token}")
                    } else {
                        Log.e(TAG, "Unable to get Installation auth token")
                    }
                }

        }
    }
}