package com.example.newsapp.ui.activities

import android.content.Intent
import android.os.Bundle
import android.view.View.*
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.newsapp.R
import com.example.newsapp.databinding.ActivityLoginBinding
import com.example.newsapp.utils.isEditEmpty
import com.example.newsapp.utils.snackBar
import com.example.newsapp.utils.toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import timber.log.Timber

class Login : AppCompatActivity() {
    lateinit var binding: ActivityLoginBinding

    lateinit var progressBar: ProgressBar

    /**
     * Check if user is already logged in or not
     * */
    private var mAuthListener: FirebaseAuth.AuthStateListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setupFirebaseAuth()
        /**
         * Inflate layout
         * */

        binding = DataBindingUtil.setContentView(this, R.layout.activity_login)
        /**
         * Get EditText views
         * */
        val email = binding.loginEmail
        val password = binding.loginPassword

        progressBar = binding.progressBar

        progressBar.visibility = GONE


        /**
         * Login user when login button is clicked
         * !isEditEmpty(view) is used to check if EditText passed is empty or not
         * */
        binding.login.setOnClickListener {
            val view = it
            if (!isEditEmpty(email.text.toString())
                && !isEditEmpty(password.text.toString())
            ) {
                it.snackBar("Signing in...")
                showDialog()

                /**
                 * Login in user with password and email
                 * */
                FirebaseAuth.getInstance()
                    .signInWithEmailAndPassword(email.text.toString(), password.text.toString())
                    .addOnSuccessListener {
                        hideDialog()
                        finish()
                    }.addOnFailureListener {
                        view.snackBar("authentication failed...")
                        hideDialog()
                    }
            } else {
                it.snackBar("please fill all fields..")
            }
        }

        /**
         * Go to register activity
         * */
        binding.toRegister.setOnClickListener {
            startActivity(Intent(this, Registration::class.java))
        }

        /**
         * Go to reset password activity
         * */
        binding.forgotPassword.setOnClickListener {
            startActivity(Intent(this, ResetPassword::class.java))
        }
    }

    private fun hideDialog() {
        if (progressBar.visibility == VISIBLE) {
            progressBar.visibility = INVISIBLE
        }
    }


    private fun showDialog() {
        progressBar.visibility = VISIBLE
    }

    /**
     * fire base auth listener
     * */
    private fun setupFirebaseAuth() {
        mAuthListener = FirebaseAuth.AuthStateListener {

            val user: FirebaseUser? = it.currentUser
            if (user != null) {
                if (user.isEmailVerified) {
                    val toMainActivity = Intent(this@Login, HomeActivity::class.java)
                    startActivity(toMainActivity)
                    finish()
                } else {
                    this.toast("Check Your Email For Verification")
                    FirebaseAuth.getInstance().signOut()
                }

            } else {
                Timber.d("signed out")
            }
        }
    }

    override fun onStop() {
        super.onStop()
        mAuthListener?.let {
            FirebaseAuth.getInstance()
                .removeAuthStateListener(it)
        }
    }


    override fun onStart() {
        super.onStart()
        mAuthListener?.let {
            FirebaseAuth.getInstance().addAuthStateListener(it)
        }
    }
}
