package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.myapplication.databinding.SignupBinding
import com.google.firebase.auth.FirebaseAuth

class signup : AppCompatActivity() {
    private lateinit var binding: SignupBinding
    private lateinit var firebaseAuth: FirebaseAuth


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        binding = SignupBinding.inflate(layoutInflater)
        setContentView(binding.root)

        firebaseAuth = FirebaseAuth.getInstance()
        binding.alreadyUser.setOnClickListener(){
            val intent = Intent(this,signin::class.java)
            startActivity(intent)
        }
        binding.signupbutton.setOnClickListener {
            val email = binding.signupemail.text.toString()
            val password = binding.signuppassword.text.toString()
            val confirm_password = binding.signupconfirmpassword.text.toString()

            if (email.isNotEmpty() && password.isNotEmpty() && confirm_password.isNotEmpty()){
                if(password == confirm_password){
                    firebaseAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(){
                        if (it.isSuccessful){
                            val intent = Intent(this,signin::class.java)
                            startActivity(intent)
                        }else{
                            Toast.makeText(this,it.exception.toString(),Toast.LENGTH_SHORT).show()
                        }
                    }
                }else{
                    Toast.makeText(this,"Password is not matching",Toast.LENGTH_SHORT).show()
                }
            }
            else{
                Toast.makeText(this,"Empty fields are not allowed ",Toast.LENGTH_SHORT).show()
            }
        }
    }
}