package com.example.interactiveregisterform

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doOnTextChanged
import com.example.interactiveregisterform.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 1. EVENT CHO EDITTEXT (Hiện Toast khi đang nhập chữ)
        binding.edtFullName.doOnTextChanged { text, _, _, _ ->
            if (!text.isNullOrEmpty()) {
                Toast.makeText(this, "Đang nhập họ tên: $text", Toast.LENGTH_SHORT).show()
            }
        }

        binding.edtEmail.doOnTextChanged { text, _, _, _ ->
            if (!text.isNullOrEmpty()) {
                Toast.makeText(this, "Đang nhập email: $text", Toast.LENGTH_SHORT).show()
            }
        }

        // 2. EVENT CHO RADIOBUTTON (Hiện Toast khi chọn giới tính)
        binding.rgGender.setOnCheckedChangeListener { _, checkedId ->
            val genderText = if (checkedId == R.id.rbMale) "Nam" else "Nữ"
            Toast.makeText(this, "Bạn đã chọn giới tính: $genderText", Toast.LENGTH_SHORT).show()
        }

        // 3. EVENT CHO CHECKBOX (Hiện Toast khi tích chọn mạng xã hội)
        binding.cbFacebook.setOnCheckedChangeListener { _, isChecked ->
            val status = if (isChecked) "Đã chọn Facebook" else "Đã bỏ chọn Facebook"
            Toast.makeText(this, status, Toast.LENGTH_SHORT).show()
        }

        binding.cbInstagram.setOnCheckedChangeListener { _, isChecked ->
            val status = if (isChecked) "Đã chọn Instagram" else "Đã bỏ chọn Instagram"
            Toast.makeText(this, status, Toast.LENGTH_SHORT).show()
        }

        binding.cbTiktok.setOnCheckedChangeListener { _, isChecked ->
            val status = if (isChecked) "Đã chọn TikTok" else "Đã bỏ chọn TikTok"
            Toast.makeText(this, status, Toast.LENGTH_SHORT).show()
        }

        // 4. BẤM NÚT ĐĂNG KÝ -> CHUYỂN QUA MÀN HÌNH CHI TIẾT
        binding.btnRegister.setOnClickListener {
            val fullName = binding.edtFullName.text.toString().trim()
            val email = binding.edtEmail.text.toString().trim()

            val selectedGenderId = binding.rgGender.checkedRadioButtonId
            val gender = when (selectedGenderId) {
                R.id.rbMale -> "Nam"
                R.id.rbFemale -> "Nữ"
                else -> "Chưa chọn"
            }

            val socialList = mutableListOf<String>()
            if (binding.cbFacebook.isChecked) socialList.add("Facebook")
            if (binding.cbInstagram.isChecked) socialList.add("Instagram")
            if (binding.cbTiktok.isChecked) socialList.add("TikTok")
            val socialStr = if (socialList.isNotEmpty()) socialList.joinToString(", ") else "Không chọn gì"

            if (fullName.isEmpty() || email.isEmpty()) {
                Toast.makeText(this, "Vui lòng nhập đầy đủ Họ tên và Email!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val intent = Intent(this, DetailActivity::class.java).apply {
                putExtra("EXTRA_NAME", fullName)
                putExtra("EXTRA_EMAIL", email)
                putExtra("EXTRA_GENDER", gender)
                putExtra("EXTRA_SOCIAL", socialStr)
            }
            startActivity(intent)
        }
    }
}