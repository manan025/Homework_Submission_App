package tech.codezit.homeworksubmission

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.math.sign

class MainActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        auth = FirebaseAuth.getInstance() // Initializing Firebase authentication

        signInbtn.setOnClickListener {
            val mail = mailInp.text.toString()
            val password = passwordInp.text.toString()
            signIn(mail, password)
        }

    }

    override fun onStart() {
        super.onStart()
        if (auth.currentUser != null) {
            // Authenticated user
            val intent = Intent(this, HomeActivity::class.java) // TODO: Create HomeActivity
            startActivity(intent)
        }
    }

    private fun signIn(email: String, password: String) {
        val mail = "$email@davkoylanagar.codezit.tech"
        auth.signInWithEmailAndPassword(mail, password)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        val intent = Intent(this, HomeActivity::class.java) // TODO: Create HomeActivity
                        startActivity(intent)
                    } else {
                        val dialogBuilder = AlertDialog
                                .Builder(this)
                                .setTitle("")
                                .setMessage("")
                                .setPositiveButton("Retry") { dialog, which ->
                                    dialog.dismiss()
                                    signIn(email, password)
                                }
                                .setNegativeButton("Cancel") { dialog, which ->
                                    dialog.cancel()
                                    Toast.makeText(this, "Error occured: ${task.exception}", Toast.LENGTH_LONG)
                                            .show()
                                }
                        dialogBuilder.show()
                    }
                }
    }

    private fun forgotPassword() {
        // TODO: Load forget password dialog
        /*
        * The app will ask the user to enter the admission number and we will send the OTP to the registered phone number.
        * Then the user will enter the OTP and will enter it to set a new password.
        *
        */
    }

}