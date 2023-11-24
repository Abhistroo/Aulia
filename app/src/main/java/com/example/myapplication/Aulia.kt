package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.databinding.AuliaBinding
import com.google.firebase.auth.FirebaseAuth
import java.util.*

class Aulia : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var binding: AuliaBinding

    private lateinit var quoteTextView: TextView
    private lateinit var greetingImageView: ImageView // New ImageView for greeting
    private lateinit var greetingTextView: TextView

    private val preDefinedQuotes = listOf(
        // Your list of quotes
        "The only way to do great work is to love what you do. - Steve Jobs",
        "In the middle of difficulty lies opportunity. - Albert Einstein",
        "Believe you can and you're halfway there. - Theodore Roosevelt",
        "The expert in anything was once a beginner. - Helen Hayes",
        "Success is the sum of small efforts, repeated day in and day out. - Robert Collier",
        "Education is not the filling of a pail, but the lighting of a fire. - William Butler Yeats",
        "Live as if you were to die tomorrow. Learn as if you were to live forever. - Mahatma Gandhi",
        "An investment in knowledge pays the best interest. - Benjamin Franklin",
        "Education is the most powerful weapon which you can use to change the world. - Nelson Mandela",
        "In learning, you will teach, and in teaching, you will learn. - Phil Collins",
        "The roots of education are bitter, but the fruit is sweet. - Aristotle",
        "The more I learn, the more I realize how much I don't know. - Albert Einstein",
        "Life is short, make it sweet.",
        "Embrace the journey, not the destination.",
        "Life is what happens when you're busy making other plans. - John Lennon",
        "In the end, it's not the years in your life that count. It's the life in your years. - Abraham Lincoln",
        "The purpose of our lives is to be happy. - Dalai Lama",
        "Life is a one-time offer. Use it well.",
        "Life is short, smile while you still have teeth.",
        "The biggest adventure you can take is to live the life of your dreams. - Oprah Winfrey",
        "Life is a journey, not a destination.",
        "Life is either a daring adventure or nothing at all. - Helen Keller",
        // ... (other quotes)
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = AuliaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = FirebaseAuth.getInstance()

        quoteTextView = findViewById(R.id.quoteTextView)
        greetingImageView = findViewById(R.id.greetingimage)
        greetingTextView = findViewById(R.id.greetingtext)

        // Reference the new ImageView
        binding.logout.setOnClickListener {
            auth.signOut()
            startActivity(Intent(this, signin::class.java))
        }

        displayRandomQuote()
        displayGreeting()
    }

    private fun displayRandomQuote() {
        val randomIndex = Random().nextInt(preDefinedQuotes.size)
        val randomQuote = preDefinedQuotes[randomIndex]

        quoteTextView.text = "\"$randomQuote\""
    }

    private fun displayGreeting() {
        val calendar = Calendar.getInstance()
        val timeOfDay = calendar.get(Calendar.HOUR_OF_DAY)
        val greetingImage: Int // Resource ID for custom image
        val greetingtext : String

        when (timeOfDay) {
            in 1..6 ->{
                greetingImage = R.drawable.night_image
                greetingtext = "Good Morning"
            }
            in 6..12 -> { // Morning (before noon)
                greetingImage = R.drawable.morning_image // Replace with your custom image for morning
                greetingtext = "Good Morning"
            }
            in 12..16 -> { // Afternoon
                greetingImage = R.drawable.afternoon_image // Replace with your custom image for afternoon
                greetingtext = "Good Afternoon"
            }
            in 17..20 -> { // Evening
                greetingImage = R.drawable.evening_image // Replace with your custom image for evening
                greetingtext = "Good Evening"
            }
            else -> { // Night
                greetingImage = R.drawable.night_image // Replace with your custom image for night
                greetingtext = "Good Night"
            }
        }

        greetingImageView.setImageResource(greetingImage)
        greetingTextView.setText(greetingtext)
    }
}
