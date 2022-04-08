package com.spg.bettercareapp.views;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.spg.bettercareapp.R;
import com.spg.bettercareapp.model.Keys;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnFocusChange;

public class LoginActivity extends AppCompatActivity {
    @BindView(R.id.email_error)
    TextView emailError;

    @BindView(R.id.lock_error)
    TextView passwordError;

    @BindView(R.id.btn_login)
    Button btnLogin;

    @BindView(R.id.email)
    EditText email;

    @BindView(R.id.password)
    EditText password;

    private boolean isEnabled = false;
    private boolean isPassError = false;
    private boolean isEmailError = false;
    private String loginKey;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        setUp();
    }

    private void setUp() {
        receiveIntent();

        btnLogin.setEnabled(false);
        emailError.setVisibility(View.GONE);
        passwordError.setVisibility(View.GONE);
        email.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (editable.toString().trim().equals("")) {
                    emailError.setVisibility(View.VISIBLE);
                    btnLogin.setEnabled(false);
                    isEnabled = false;
                    isEmailError = true;
                } else {
                    isEmailError = false;
                    emailError.setVisibility(View.GONE);
                    setLoginButtonEnable();
                }
            }
        });
        password.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (editable.toString().trim().equals("")) {
                    passwordError.setVisibility(View.VISIBLE);
                    btnLogin.setEnabled(false);
                    isEnabled = false;
                    isPassError = true;
                } else {
                    isPassError = false;
                    passwordError.setVisibility(View.GONE);
                    setLoginButtonEnable();
                }
            }
        });
    }
    private void receiveIntent(){
        if(getIntent()!=null && getIntent().getStringExtra(Keys.LOGIN_KEY)!=null){
            loginKey = getIntent().getStringExtra(Keys.LOGIN_KEY);
        }
    }
    @OnFocusChange(R.id.email)
    public void onEmailFocusChange(boolean isFocus) {
        if (isFocus) {
            emailError.setVisibility(View.GONE);
        } else {
            validate();
        }
    }

    @OnFocusChange(R.id.password)
    public void onPasswordFocusChange(boolean isFocus) {
        if (isFocus) {
            passwordError.setVisibility(View.GONE);
        } else {
            validate();
        }
    }

    private void validate() {
        String emailValue = email.getText().toString().trim();
        String passValue = password.getText().toString().trim();
        if (emailValue.equals("")) {
            emailError.setVisibility(View.VISIBLE);
            btnLogin.setEnabled(false);
            isEnabled = false;
            isEmailError = true;
        } else {
            isEmailError = false;
            emailError.setVisibility(View.GONE);
        }

        if (passValue.equals("")) {
            passwordError.setVisibility(View.VISIBLE);
            btnLogin.setEnabled(false);
            isEnabled = false;
            isPassError = true;
        } else {
            isPassError = false;
            passwordError.setVisibility(View.GONE);
        }

        if (!emailValue.equals("") && !passValue.equals("")) {
            btnLogin.setEnabled(true);
            isEnabled = true;
        }
    }

    private void setLoginButtonEnable() {
        if (!isEmailError && !isPassError) {
            isEnabled=true;
            btnLogin.setEnabled(true);
        } else {
            btnLogin.setEnabled(false);
        }
    }

    @OnClick(R.id.btn_login)
    public void onLoginClicked(){
        btnLogin.setEnabled(isEnabled);
        if(isEnabled){
            String username = email.getText().toString();
            String pass = password.getText().toString();
            if(username.equals("test123") && pass.equals("test123")){

                if(loginKey.equals("Admin")){
                    startActivity(new Intent(this,AdminDashboardActivity.class));
                }else if(loginKey.equals("Care Staff")){
                    startActivity(new Intent(this,ChooseShiftActivity.class));
                }else{
                    Toast.makeText(this,"Invalid login credentials",Toast.LENGTH_SHORT).show();
                }
            }else{
                Toast.makeText(this,"Invalid login credentials",Toast.LENGTH_SHORT).show();
            }
        }
    }


}