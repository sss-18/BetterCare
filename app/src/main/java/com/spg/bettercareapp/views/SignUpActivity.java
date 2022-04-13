package com.spg.bettercareapp.views;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.spg.bettercareapp.R;
import com.spg.bettercareapp.model.Keys;
import com.spg.bettercareapp.model.RowChangeModel;
import com.spg.bettercareapp.repo.ApiClient;
import com.spg.bettercareapp.repo.ApiInterface;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnFocusChange;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignUpActivity extends AppCompatActivity {

    @BindView(R.id.login)
    Button btnLogin;

    @BindView(R.id.username)
    EditText user_email;

    @BindView(R.id.username_error)
    TextView emailError;

    @BindView(R.id.lock_error)
    TextView passwordError;

    @BindView(R.id.btn_sign_up)
    Button btnSignUp;

    @BindView(R.id.password)
    EditText passwordEditText;

    private String loginKey;
    private boolean isEnabled = false;
    private boolean isEmailError = false;
    private boolean isPassError = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        ButterKnife.bind(this);
        setUp();
    }

    private void setUp() {
        receiveIntent();
        btnSignUp.setEnabled(false);
        emailError.setVisibility(View.GONE);
        passwordError.setVisibility(View.GONE);
        user_email.addTextChangedListener(new TextWatcher() {
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
                    btnSignUp.setEnabled(false);
                    isEnabled = false;
                    isEmailError = true;
                } else {
                    isEmailError = false;
                    emailError.setVisibility(View.GONE);
                    setSignUpButtonEnable();
                }
            }
        });

        passwordEditText.addTextChangedListener(new TextWatcher() {
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
                    btnSignUp.setEnabled(false);
                    isEnabled = false;
                    isPassError = true;
                } else {
                    isPassError = false;
                    passwordError.setVisibility(View.GONE);
                    setSignUpButtonEnable();
                }
            }
        });
    }

    @OnFocusChange(R.id.username)
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
        String emailValue = user_email.getText().toString().trim();
        String passValue = passwordEditText.getText().toString().trim();
        if (emailValue.equals("")) {
            emailError.setVisibility(View.VISIBLE);
            btnSignUp.setEnabled(false);
            isEnabled = false;
            isEmailError = true;
        } else {
            isEmailError = false;
            emailError.setVisibility(View.GONE);
        }

        if (passValue.equals("")) {
            passwordError.setVisibility(View.VISIBLE);
            btnSignUp.setEnabled(false);
            isEnabled = false;
            isPassError = true;
        } else {
            isPassError = false;
            passwordError.setVisibility(View.GONE);
        }

        if (!emailValue.equals("") && !passValue.equals("")) {
            btnSignUp.setEnabled(true);
            isEnabled = true;
        }
    }

    private void setSignUpButtonEnable() {
        if (!isEmailError && !isPassError) {
            isEnabled = true;
            btnSignUp.setEnabled(true);
        } else {
            btnSignUp.setEnabled(false);
        }
    }

    private void receiveIntent() {
        if (getIntent() != null && getIntent().getStringExtra(Keys.LOGIN_KEY) != null) {
            loginKey = getIntent().getStringExtra(Keys.LOGIN_KEY);
        }
    }

    @OnClick(R.id.btn_sign_up)
    public void onSignUp() {
        btnSignUp.setEnabled(true);
        if (isEnabled) {
            String username = user_email.getText().toString();
            String pass = passwordEditText.getText().toString();

            if (loginKey.equals("Admin")) {
                setAdmin(username, pass);
            } else if (loginKey.equals("Care Staff")) {
                setCareStaff(username, pass);
            } else {
                Toast.makeText(this, "Invalid login credentials", Toast.LENGTH_SHORT).show();
            }

        }
    }

    @OnClick(R.id.login)
    public void onLoginClick() {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }

    public void setAdmin(String user_name, String password) {
        ApiInterface apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        Call<List<RowChangeModel>> call = apiInterface.setAdmin(user_name, password);
        call.enqueue(new Callback<List<RowChangeModel>>() {
            @Override
            public void onResponse(Call<List<RowChangeModel>> call, Response<List<RowChangeModel>> response) {
                Log.d("Sign-Up-Activity", "Saved Successfully Admin");
                startActivity(new Intent(SignUpActivity.this, HomeScreenActivity.class));
                Toast.makeText(SignUpActivity.this, "Saved login credentials", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<List<RowChangeModel>> call, Throwable t) {
                Toast.makeText(SignUpActivity.this, "Invalid login credentials", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void setCareStaff(String user_name, String password) {
        ApiInterface apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        Call<List<RowChangeModel>> call = apiInterface.setCareStaff(user_name, password);
        call.enqueue(new Callback<List<RowChangeModel>>() {
            @Override
            public void onResponse(Call<List<RowChangeModel>> call, Response<List<RowChangeModel>> response) {
                Log.d("Sign-Up-Activity", "Saved Successfully Care Staff");
                Toast.makeText(SignUpActivity.this, "Saved login credentials", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(SignUpActivity.this, HomeScreenActivity.class));
            }

            @Override
            public void onFailure(Call<List<RowChangeModel>> call, Throwable t) {
                Log.d("Sign-Up-Activity", "Saved Failed Care Staff");
                Toast.makeText(SignUpActivity.this, "Invalid login credentials", Toast.LENGTH_SHORT).show();
            }
        });
    }
}