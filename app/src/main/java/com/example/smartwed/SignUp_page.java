package com.example.smartwed;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SignUp_page extends AppCompatActivity {
    EditText name,email,password,confirmpassword;
    Button signup;
    DataBaseHelper db;
    SharedPreferences sp;
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    String passwordPattern = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{4,}$";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_page);
        name=(EditText) findViewById(R.id.username);
        email=(EditText) findViewById(R.id.signupmail);
        password=(EditText) findViewById(R.id.spassword);
        confirmpassword=(EditText) findViewById(R.id.confirm_passwordd);
        signup=(Button) findViewById(R.id.btnsinups);
        sp=getSharedPreferences( "Myuserpref", Context.MODE_PRIVATE);
        db=new DataBaseHelper();

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user=name.getText().toString();
                String useremail=email.getText().toString();
                String pass=password.getText().toString();
                String confirmpass=confirmpassword.getText().toString();
                if(user.equals("")||useremail.equals("")||pass.equals("")||confirmpass.equals("")) {
                    Toast.makeText(SignUp_page.this,
                            "Please enter the values", Toast.LENGTH_SHORT).show();
                }
                else {

                    if(useremail.trim().matches(emailPattern)) {
                        if (pass.trim().matches(passwordPattern)) {
                            if (pass.equals(confirmpass)) {
                                Boolean checkuser = db.checkusername(useremail);
                                if (checkuser == false) {
                                    Boolean insert = db.insertData(useremail, pass);

                                    if (insert == true) {
                                        Toast.makeText(SignUp_page.this,
                                                "SignUp Successful!", Toast.LENGTH_SHORT).show();
                                        Intent intent = new Intent(getApplicationContext(), SignInActivity.class);
                                        startActivity(intent);

                                    } else {
                                        Toast.makeText(SignUp_page.this, "Sign up failed", Toast.LENGTH_SHORT).show();
                                    }
                                } else {
                                    Toast.makeText(SignUp_page.this, "User already exists please SignIn",
                                            Toast.LENGTH_SHORT).show();
                                }

                            } else {
                                Toast.makeText(SignUp_page.this, "password Mismatched!", Toast.LENGTH_SHORT).show();
                            }
                        }


                        else{Toast.makeText(SignUp_page.this,"Password should be one capital" +
                                        " letter ,integers and special characters(#,. etc)",
                                Toast.LENGTH_SHORT).show();}
                    }
                    else{Toast.makeText(SignUp_page.this,"Please insert valid email address",Toast.LENGTH_SHORT).show();}
                }
                SharedPreferences.Editor editor=sp.edit();
                editor.putString("NAME",user);
                editor.commit();

            }
        });


    }

    public void keyboard_down_signup(View view) {
        InputMethodManager inputMethodManager=(InputMethodManager)getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(),0);
    }
}