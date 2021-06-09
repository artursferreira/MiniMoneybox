package com.example.minimoneybox.ui.login

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.minimoneybox.databinding.ActivityLoginBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * A login screen that offers login via email/password.
 */
class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    private val viewModel: LoginViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupViews()
    }

    private fun setupViews() {

        binding.btnSignIn.setOnClickListener {
            binding.animation.playAnimation()
        }
    }
}
