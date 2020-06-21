package com.kotlin.tuank.tk_cenemas

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.text.InputType
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import cenemas.core_enums.sign_up_field_enum
import cenemas.core_interfaces.IToggleViewPass
import cenemas.database.CheckValidValueSignUp
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import java.util.*
import kotlin.collections.ArrayList


class SignUp : AppCompatActivity() {

    lateinit var signUpUserName: EditText
    lateinit var signUpEmail: EditText
    lateinit var signUpPassword: EditText
    lateinit var signUpConfirmPassword: EditText
    lateinit var signUpAlias: EditText
    lateinit var signUpAliasSubmitBtn: Button
    lateinit var signUpBackToSignIn: TextView
    lateinit var signUpViewPass: TextView
    lateinit var signUpViewRePass: TextView

    val db: FirebaseDatabase = FirebaseDatabase.getInstance()
    val myRef: DatabaseReference = db.getReference()

    val authenFb: FirebaseAuth = FirebaseAuth.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)
        setViewBinding()
        setBackToMainFn()
        setRegisterFn()
        setTogglePassFn()
    }

    private fun setViewBinding(){
        this.signUpUserName = findViewById(R.id.signUpUserName)
        this.signUpEmail = findViewById(R.id.signUpEmail)
        this.signUpPassword = findViewById(R.id.signUpPassword)
        this.signUpConfirmPassword = findViewById(R.id.signUpConfirmPassword)
        this.signUpAlias = findViewById(R.id.signUpAlias)
        this.signUpAliasSubmitBtn = findViewById(R.id.signUpAliasSubmitBtn)
        this.signUpBackToSignIn = findViewById(R.id.signUpBackToSignIn)
        this.signUpViewPass = findViewById(R.id.signUpViewPass)
        this.signUpViewRePass = findViewById(R.id.signUpViewRePass)
    }


    private fun setBackToMainFn(){
        this.signUpBackToSignIn.setOnClickListener { view ->
            startActivity(Intent(this@SignUp, MainLoginScreen :: class.java));
            finish()
        }
    }

    private fun setRegisterFn(){
        this.signUpAliasSubmitBtn.setOnClickListener { view ->
            this.registeringUser()
        }
    }

    private fun setTogglePassFn(){
        val fieldsHandlingViewPass = arrayListOf<IToggleViewPass>(
                IToggleViewPass(sign_up_field_enum.PASSWORD, this.signUpViewPass, this.signUpPassword),
                IToggleViewPass(sign_up_field_enum.RE_PASSWORD, this.signUpViewRePass, this.signUpConfirmPassword)
        )
        for ( field in fieldsHandlingViewPass){
            toggleViewPassword(field);
        }
    }

    @Suppress("DEPRECATED_IDENTITY_EQUALS")
    private fun toggleViewPassword(field: IToggleViewPass){
        field.toggleBtnView.setOnClickListener { view ->
            if(field.toggleBtnView.text == "View"){
                field.valueView.transformationMethod = HideReturnsTransformationMethod.getInstance()
                field.toggleBtnView.text = "Hide"
            }else{
                field.valueView.transformationMethod = PasswordTransformationMethod.getInstance()
                field.toggleBtnView.text = "View"
            }
        }
    }

    private fun registeringUser(){
        val userName = this.signUpUserName.text.toString()
        val email = this.signUpEmail.text.toString()
        val password = this.signUpPassword.text.toString()
        val reTypePassword = this.signUpConfirmPassword.text.toString()
        val alias = this.signUpAlias.text.toString()

        val isAllowRegis = isAllowRegistering(
            userName, email, password, reTypePassword
        )

        if(isAllowRegis){
            //todo register
            authenFb.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, OnCompleteListener<AuthResult?> { task ->
                    if (task.isSuccessful) {
                        val user: FirebaseUser? = authenFb.getCurrentUser()
                        startActivity(Intent(this@SignUp, MainLoginScreen :: class.java))
                        finish()
                    } else {
                        Toast.makeText(this@SignUp, "Authentication failed.",
                            Toast.LENGTH_SHORT).show()
                    }
                })
        }

    }

    private fun isAllowRegistering(userName: String, email: String, pass: String, rePass: String): Boolean{
        if(userName.isNotEmpty() && email.isEmpty() && pass.isNotEmpty() && rePass.isEmpty()){
            return true
        }else{
            val fieldsValue: ArrayList<CheckValidValueSignUp>
                = arrayListOf<CheckValidValueSignUp>(
                    CheckValidValueSignUp(userName, sign_up_field_enum.USER_NAME.fieldName),
                    CheckValidValueSignUp(email, sign_up_field_enum.EMAIL_ADDRESS.fieldName),
                    CheckValidValueSignUp(pass, sign_up_field_enum.PASSWORD.fieldName),
                    CheckValidValueSignUp(rePass, sign_up_field_enum.RE_PASSWORD.fieldName)
            );
            val passwordFields: ArrayList<CheckValidValueSignUp>
                    = arrayListOf<CheckValidValueSignUp>(
                    CheckValidValueSignUp(pass, sign_up_field_enum.PASSWORD.fieldName),
                    CheckValidValueSignUp(rePass, sign_up_field_enum.RE_PASSWORD.fieldName)
            );
            var isAllow: Boolean = true
            for (field in fieldsValue){
                if(field.fieldValue.isEmpty()){
                    this.showMessage("Please fill ${field.fieldName}!")
                    isAllow = false
                    break
                } else {
                    if( passwordFields.contains(field) && field.fieldValue.length < 8 ){
                        this.showMessage("Password must be more than 8 words")
                        isAllow = false
                        break
                    }
                }
            }

            if(isAllow && (pass != rePass)){
                this.showMessage("Two password is not same! Please re-fill")
                isAllow = false
            }
            return isAllow
        }
    }

    private fun showMessage(message: String){
        Toast.makeText(this@SignUp, message, Toast.LENGTH_LONG).show()
    }

}
