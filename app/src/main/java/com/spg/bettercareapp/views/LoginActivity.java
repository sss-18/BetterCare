package com.spg.bettercareapp.views;

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

import androidx.appcompat.app.AppCompatActivity;

import com.spg.bettercareapp.R;
import com.spg.bettercareapp.model.Admin;
import com.spg.bettercareapp.model.Care;
import com.spg.bettercareapp.model.Keys;
import com.spg.bettercareapp.model.Resident;
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

    @BindView(R.id.sign_up)
    Button signUp;

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

    private void receiveIntent() {
        if (getIntent() != null && getIntent().getStringExtra(Keys.LOGIN_KEY) != null) {
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
            isEnabled = true;
            btnLogin.setEnabled(true);
        } else {
            btnLogin.setEnabled(false);
        }
    }

    @OnClick(R.id.sign_up)
    public void onSignUpClick() {
        Intent intent = new Intent(this, SignUpActivity.class);
        intent.putExtra(Keys.LOGIN_KEY, loginKey);
        startActivity(intent);
    }

    @OnClick(R.id.btn_login)
    public void onLoginClicked() {
        btnLogin.setEnabled(isEnabled);
        if (isEnabled) {
            String username = email.getText().toString();
            String pass = password.getText().toString();

            if (loginKey.equals("Admin")) {
                startActivity(new Intent(LoginActivity.this, AdminDashboardActivity.class));
                // TODO : Uncomment once done
                //authAdminCredential(username, pass);
            } else if (loginKey.equals("Care Staff")) {
                startActivity(new Intent(LoginActivity.this, ChooseShiftActivity.class));
                // TODO : Uncomment once done
                //authCareStaffCredential(username, pass);
            } else {
                Toast.makeText(this, "Invalid login credentials", Toast.LENGTH_SHORT).show();
            }

        }
    }

    private void authAdminCredential(String user_name, String password) {
        ApiInterface apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        Call<List<Admin>> call = apiInterface.authAdmin(user_name);
        call.enqueue(new Callback<List<Admin>>() {
            @Override
            public void onResponse(Call<List<Admin>> call, Response<List<Admin>> response) {
                Log.d("Login-Activity", "Fetched Successfully Admin");
                response.body();
                if (password.equals(response.body().get(0).getPassword())) {
                    startActivity(new Intent(LoginActivity.this, AdminDashboardActivity.class));
                } else {
                    Toast.makeText(LoginActivity.this, "Invalid login credentials", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Admin>> call, Throwable t) {
                Log.d("Login-Activity", "Failed Admin" + t);
                Toast.makeText(LoginActivity.this, "Invalid login credentials", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void authCareStaffCredential(String user_name, String password) {
        ApiInterface apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        Call<List<Care>> call = apiInterface.authCareStaff(user_name);
        call.enqueue(new Callback<List<Care>>() {
            @Override
            public void onResponse(Call<List<Care>> call, Response<List<Care>> response) {
                Log.d("Login-Activity", "Fetched Successfully Care-Staff");
                response.body();
                if (password.equals(response.body().get(0).getPassword())) {
                    startActivity(new Intent(LoginActivity.this, ChooseShiftActivity.class));
                } else {
                    Toast.makeText(LoginActivity.this, "Invalid login credentials", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Care>> call, Throwable t) {
                Log.d("Login-Activity", "Failed Care-Staff" + t);
                Toast.makeText(LoginActivity.this, "Invalid login credentials", Toast.LENGTH_SHORT).show();
            }
        });
    }
}