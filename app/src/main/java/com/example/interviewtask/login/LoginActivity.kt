package com.example.interviewtask.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.interviewtask.R
import com.example.interviewtask.dashboard.DashboardActivity
import com.example.interviewtask.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding:ActivityLoginBinding = DataBindingUtil.setContentView(
                this, R.layout.activity_login)

        val loginViewModel = ViewModelProvider(this).get(LoginViewModel::class.java)
        binding.loginViewModel = loginViewModel
        binding.lifecycleOwner = this


        loginViewModel.navigateToDashBoard.observe(this, { night ->
            night?.let {
                if(it){
                    val intent = Intent(this, DashboardActivity::class.java)
                    startActivity(intent)
                    loginViewModel.navigationFinished()
                    finish()
                }
            }
        })
    }


}