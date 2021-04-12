package com.example.newsapp.ui.activities

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.newsapp.R
import com.example.newsapp.databinding.ActivityResetPasswordBinding
import com.example.newsapp.utils.isEditEmpty
import com.example.newsapp.utils.snackBar
import com.example.newsapp.utils.toast
import com.google.firebase.auth.FirebaseAuth

class ResetPassword : AppCompatActivity() {
    lateinit var resetPasswordEdit: EditText
    lateinit var resetPasswordButton: Button

    lateinit var binding: ActivityResetPasswordBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_reset_password)

        resetPasswordButton = binding.sendResetPasswordEMail
        resetPasswordEdit = binding.resetPasswordEmail

        resetPasswordButton.setOnClickListener {
            val resetPasswordEmail = resetPasswordEdit.text.toString()

            if (!isEditEmpty(resetPasswordEmail)) {
                resetPassword(resetPasswordEmail, it)
                startActivity(Intent(this, Login::class.java))
                finish()
            } else {
                it.snackBar("Fill Email Field")
            }
        }
    }

    private fun resetPassword(email: String, view: View) {
        FirebaseAuth.getInstance().sendPasswordResetEmail(email)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    toast("Email Sent")
                    view.snackBar("Email Sent")
                }
            }
    }
}