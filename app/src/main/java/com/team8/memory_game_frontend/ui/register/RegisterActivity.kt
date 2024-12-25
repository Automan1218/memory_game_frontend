package com.team8.memory_game_frontend.ui.register

import android.os.Bundle
import android.text.InputType
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.team8.memory_game_frontend.databinding.ActivityRegisterBinding
import android.content.Intent
import android.util.Log
import androidx.lifecycle.lifecycleScope
import com.team8.memory_game_frontend.R
import com.team8.memory_game_frontend.data.api.RetrofitClient
import com.team8.memory_game_frontend.data.model.request.RegisterRequest
import com.team8.memory_game_frontend.data.model.response.RegisterResponse
import com.team8.memory_game_frontend.ui.login.LoginActivity
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException

class RegisterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding
    private var isPasswordVisible: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Handle password visibility toggle
        binding.passwordToggle.setOnClickListener {
            togglePasswordVisibility()
        }

        // Handle Register Button Click
        binding.registerButton.setOnClickListener {
            val username = binding.usernameEditText.text.toString()
            val password = binding.passwordEditText.text.toString()

            if (username.isBlank() || password.isBlank()) {
                Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val request = RegisterRequest(username, password)
            lifecycleScope.launch {
                try {
                    val response = RetrofitClient.instance.register(request)
                    Toast.makeText(this@RegisterActivity, "Registration successful!", Toast.LENGTH_SHORT).show()
                    val intent = Intent(this@RegisterActivity, LoginActivity::class.java)
                    startActivity(intent)
                    finish()
                } catch (e: Exception) {
                    if (e is IOException) {
                        Toast.makeText(this@RegisterActivity, "Network error. Please check your connection.", Toast.LENGTH_SHORT).show()
                    } else {
                        Log.e("RegisterActivity", "Registration failed", e)
                        Toast.makeText(this@RegisterActivity, "An error occurred. Please try again later.", Toast.LENGTH_SHORT).show()
                    }}
            }

//            // Simulate registration logic
//            Toast.makeText(this, "Registration successful!", Toast.LENGTH_SHORT).show()
//            finish() // Close the activity
        }

        // Handle Back to Login Button Click
        binding.backToLoginButton.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish() // Close the current activity
        }
    }

    private fun togglePasswordVisibility() {
        isPasswordVisible = !isPasswordVisible
        if (isPasswordVisible) {
            binding.passwordEditText.inputType = InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD
            binding.passwordToggle.setImageResource(R.drawable.ic_visibility)
        } else {
            binding.passwordEditText.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
            binding.passwordToggle.setImageResource(R.drawable.ic_visibility_off)
        }
        binding.passwordEditText.setSelection(binding.passwordEditText.text.length) // Move cursor to end
    }
}
