package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.databinding.AuliaBinding
import com.example.myapplication.signin
import com.google.firebase.auth.FirebaseAuth

class Aulia : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var binding: AuliaBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = AuliaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = FirebaseAuth.getInstance()

        binding.logout.setOnClickListener {
            auth.signOut()
            startActivity(Intent(this,signin::class.java))
        }
    }
}
