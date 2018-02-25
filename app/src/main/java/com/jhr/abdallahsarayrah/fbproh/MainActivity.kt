package com.jhr.abdallahsarayrah.fbproh

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val auth = FirebaseAuth.getInstance()

        button_signUp.setOnClickListener {
            if (editText_password.text.toString() == editText_confirmPassword.text.toString()) {
                auth.createUserWithEmailAndPassword(editText_email.text.toString(),
                        editText_password.text.toString()).addOnCompleteListener { task ->
                    if (task.isSuccessful) Toast.makeText(this, "User created", Toast.LENGTH_SHORT).show()
                    else Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "password not match", Toast.LENGTH_SHORT).show()
            }
        }

        button_signIn.setOnClickListener {
            auth.signInWithEmailAndPassword(editText_email.text.toString(),
                    editText_password.text.toString()).addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val intent = Intent(this, UploadActivity::class.java)
                    startActivity(intent)
                } else Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
