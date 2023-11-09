package com.example.actfirebasesplash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {

    private lateinit var email: EditText
    private lateinit var password: EditText
    private lateinit var button: Button
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        auth = Firebase.auth

        email = findViewById(R.id.editTextTextEmailAddress)
        password = findViewById(R.id.editPassword)
        button = findViewById(R.id.btnEntrar)

        button.setOnClickListener {
            val logEmail = email.text.toString()
            val logPassword = password.text.toString()
            if (checkEmpty(logEmail, logPassword)){
                auth.signInWithEmailAndPassword(logEmail,logPassword)
                    .addOnCompleteListener(this){task ->
                        if (task.isSuccessful){
                            startActivity((Intent(this,CompletedLoginActivity::class.java)))
                            finish()
                        }else{
                            Toast.makeText(applicationContext, "ERROR INICIAR SESION", Toast.LENGTH_SHORT).show()
                        }
                    }
            }
        }

    }

    private fun checkEmpty(logEmail: String, logPassword: String): Boolean {
        return logEmail.isNotEmpty() && logPassword.isNotEmpty()
    }
}