package com.example.carhelperapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_signup.*

class SignupActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        signup_button.setOnClickListener {
            if(signup_password.text.toString()==signup_confirm.text.toString()){
                var url=UserInfo.url + "signup.php?mobile=" + signup_mobile.text.toString() +
                        "&password=" + signup_password.text.toString() +
                        "&name=" + signup_name.text.toString()
                var rq= Volley.newRequestQueue(this)
                var sr= StringRequest(Request.Method.GET,url,
                    Response.Listener { response ->
                        if(response=="0")
                            Toast.makeText(this,"Mobile Already Exist",
                                Toast.LENGTH_LONG).show()
                        else{
                            UserInfo.mobile=signup_mobile.text.toString()
                            var i= Intent(this,MainActivity::class.java)
                            startActivity(i)
                        }
                    },
                    Response.ErrorListener {  })
                rq.add(sr)
            }
            else{
                Toast.makeText(this,"Password not match",
                    Toast.LENGTH_LONG).show()
            }


        }
    }
}
