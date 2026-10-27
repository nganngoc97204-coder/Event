package com.example.interactiveregisterform

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.interactiveregisterform.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val name = intent.getStringExtra("EXTRA_NAME") ?: ""
        val email = intent.getStringExtra("EXTRA_EMAIL") ?: ""
        val gender = intent.getStringExtra("EXTRA_GENDER") ?: ""
        val social = intent.getStringExtra("EXTRA_SOCIAL") ?: ""

        binding.tvResultFullName.text = "Họ tên: $name"
        binding.tvResultEmail.text = "Email: $email"
        binding.tvResultGender.text = "Giới tính: $gender"
        binding.tvResultSocial.text = "Mạng xã hội: $social"

        binding.btnBack.setOnClickListener {
            finish()
        }
    }
}