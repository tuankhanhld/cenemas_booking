package com.kotlin.tuank.tk_cenemas

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import cenemas.core_enums.sign_up_field_enum
import cenemas.core_interfaces.ISigninInfor
import cenemas.database.CheckValidValueSignUp
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_sign_up.*

class MainLoginScreen : AppCompatActivity() {

    lateinit var loginBtn: Button
    lateinit var navigateSignUp: TextView
    lateinit var signInUserName: EditText
    lateinit var signInPassword: EditText
    lateinit var signInViewPass: TextView
    lateinit var signInProgressBar: ProgressBar
    val db: FirebaseDatabase = FirebaseDatabase.getInstance()
    val authenFb: FirebaseAuth = FirebaseAuth.getInstance()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_login_screen)
        this.loginBtn = findViewById(R.id.loginBtn)
        this.navigateSignUp = findViewById(R.id.navigateSignUp)
        this.signInUserName = findViewById(R.id.signInUserName)
        this.signInPassword = findViewById(R.id.signInPassword)
        this.signInViewPass = findViewById(R.id.signInViewPass)
        this.signInProgressBar = findViewById(R.id.signInProgressBar)

        setLoginBtnFn()
        setSignUpBtnFn()
    }

    private fun setLoginBtnFn(){
        this.loginBtn.setOnClickListener { view ->
            if(isAllowHandlingSignin()){
                handlingSignIn(signInUserName.text.toString(), signInPassword.text.toString())
            }

        }
    }

    private fun setSignUpBtnFn(){
        this.navigateSignUp.setOnClickListener { view ->
            startActivity(Intent(this@MainLoginScreen, SignUp :: class.java))
            finish()
        }
    }

    private fun getUserInformation(): ArrayList<CheckValidValueSignUp>{
        return arrayListOf(
                CheckValidValueSignUp(signInUserName.text.toString(), sign_up_field_enum.USER_NAME.fieldName),
                CheckValidValueSignUp(signInPassword.text.toString(), sign_up_field_enum.PASSWORD.fieldName)
        )
    }

    private fun isAllowHandlingSignin(): Boolean{
        val signinInfor = getUserInformation()
        var isAllow: Boolean = true
        for (field in signinInfor){
            if(field.fieldValue.isEmpty()){
                this.showMessage("Please fill ${field.fieldName}!")
                isAllow = false
                break
            }
        }
        return isAllow
    }

    private fun handlingSignIn(email: String, password: String){
        if(email.isNotEmpty() && password.isNotEmpty()){
            loginBtn.visibility = View.INVISIBLE
            signInProgressBar.visibility = View.VISIBLE
            authenFb.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, OnCompleteListener { result ->
                    if (result.isSuccessful) {
                        startActivity(Intent(this@MainLoginScreen, HomeScreen :: class.java))
                        finish()
                    } else {
                        signInProgressBar.visibility = View.INVISIBLE
                        loginBtn.visibility = View.VISIBLE
                        showMessage("Authentication failed!")
                    }
                })
        }
    }

    private fun showMessage(message: String){
        Toast.makeText(this@MainLoginScreen, message, Toast.LENGTH_LONG).show()
    }

}
