package com.example.newsapp.ui.activities

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.newsapp.R
import com.example.newsapp.databinding.ActivityRegistrationBinding
import com.example.newsapp.domain.NickNames
import com.example.newsapp.utils.isEditEmpty
import com.example.newsapp.utils.snackBar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.FirebaseDatabase
import timber.log.Timber

class Registration : AppCompatActivity() {

    lateinit var binding: ActivityRegistrationBinding

    private val addressType: String = "gmail.com"
    lateinit var progressBar: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_registration)


        val name = binding.registerName
        val email = binding.registerEmail
        val password = binding.registerPassword
        val inputConfirmPassword = binding.registerConfirmPassword
        progressBar = binding.progressBar


        progressBar.visibility = GONE

        /**
         * Register new user
         * */
        binding.register.setOnClickListener {
            it.snackBar("Registering")

            if (!isEditEmpty(email.text.toString())
                && !isEditEmpty(password.text.toString())
                && !isEditEmpty(inputConfirmPassword.text.toString())
                && !isEditEmpty(name.text.toString())
            ) {

                /**
                 * check if user has registered with gmail.com email
                 * */
                if (isValidDomain(email.text.toString())) {
                    /**
                     * Checking if passwords match
                     * */
                    if (doStringMatch(
                            password.text.toString(),
                            inputConfirmPassword.text.toString()
                        )
                    ) {
                        /**
                         * create new user
                         * */
                        showDialog()
                        registerNewEmail(email.text.toString(), password.text.toString(), it)

                        /**
                         * save name provided in nick names database collection
                         * */

                        val saveToNickNameCollection =
                            FirebaseDatabase.getInstance().getReference("Nick Names")

                        /**
                         * Split email
                         */

                        val arrayEmail = email.text.toString().split("@").toTypedArray()
                        val emailTrue = (arrayEmail[0]).trim()

                        val nickName = NickNames(
                            email.text.toString(),
                            name.text.toString()
                        )
                        /**
                         * Save to database
                         * */
                        saveToNickNameCollection.child(emailTrue).setValue(nickName)

                    } else {
                        it.snackBar("passwords do not match")
                    }
                } else {
                    it.snackBar("Register with gmail.com")
                }
            } else {
                it.snackBar("Fill all the fields")
            }
        }

        binding.toLogin.setOnClickListener {
            startActivity(Intent(this, Login::class.java))
            finish()
        }
    }

    /**
     * function to register new email
     * */
    @SuppressLint("LogNotTimber")
    private fun registerNewEmail(email: String, password: String, view: View) {
        FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) {
                if (it.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d(
                        "OnComplete Auth State: ",
                        "createUserWithEmail:success" + FirebaseAuth.getInstance()
                            .currentUser!!.getIdToken(true)
                    )
                    sendVerificationEmail(view)
                    view.snackBar("Registration success...")
                    FirebaseAuth.getInstance().signOut()
                    startActivity(Intent(this@Registration, Login::class.java))
                    finish()
                }
                if (!it.isSuccessful) {
                    view.snackBar("Registration failed!!...")
                }
            }
        hideDialog()
    }

    private fun sendVerificationEmail(view: View) {
        val user: FirebaseUser? = FirebaseAuth.getInstance().currentUser

        user?.sendEmailVerification()?.addOnCompleteListener {
            if (it.isSuccessful) {
                view.snackBar("Sent Verification Email...")
            } else {
                view.snackBar("Could Not Send Verification Email...")
            }
        }
    }

    private fun hideDialog() {
        if (progressBar.visibility == VISIBLE) {
            progressBar.visibility = GONE
        }
    }

    private fun showDialog() {
        progressBar.visibility = VISIBLE
    }

    /**
     * return true if @param strings1 matches strings2
     * */
    private fun doStringMatch(strings1: String, strings2: String): Boolean {
        return strings1 == strings2
    }

    /**
     * returns true if user has email with name altruism.ca
     * */

    @SuppressLint("DefaultLocale")
    private fun isValidDomain(email: String): Boolean {
        Timber.d("isValidDomain: verifying email has correct domain $email")
        val domain: String = email.substring(email.indexOf("@") + 1).toLowerCase()
        Timber.d("isValidDomain : users domain $domain")
        return domain == addressType
    }
}