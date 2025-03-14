package com.example.flagquizapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.flagquizapp.databinding.ActivityResultBinding

class ResultActivity : AppCompatActivity() {
    private lateinit var binding:ActivityResultBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityResultBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val correctCounter=intent.getIntExtra("correctCounter",0)

        binding.textViewResult.text="$correctCounter CORRECT ${5-correctCounter} WRONG"
        binding.textViewPercentageResult.text="%${(correctCounter*100)/5} SUCCESS"
        binding.buttonRetry.setOnClickListener {
            startActivity(Intent(this@ResultActivity,QuizActivity::class.java))
            finish()

        }
    }
}