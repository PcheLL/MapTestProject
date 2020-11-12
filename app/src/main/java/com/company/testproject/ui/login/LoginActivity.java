package com.company.testproject.ui.login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.company.testproject.R;
import com.company.testproject.presenter.login.ILoginPresenter;
import com.company.testproject.presenter.login.LoginPresenter;
import com.company.testproject.ui.point.PointListActivity;
import com.company.testproject.utils.Const;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;

public class LoginActivity extends AppCompatActivity implements LoginView, View.OnClickListener {

    private ILoginPresenter presenter;
    private TextInputEditText loginEditText;
    private TextInputEditText passwordEditText;
    private ConstraintLayout loginConstraintLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        MaterialButton loginButton = findViewById(R.id.btn_login);
        loginButton.setOnClickListener(this);
        loginEditText = findViewById(R.id.tiet_login);
        passwordEditText = findViewById(R.id.tiet_password);
        loginConstraintLayout = findViewById(R.id.cl_login);

        init();
    }

    private void init() {
        presenter = new LoginPresenter(this);
    }

    @Override
    public void onClick(View v) {
        presenter.onLoginButtonClicked(loginEditText.getText().toString(), passwordEditText.getText().toString());
    }

    @Override
    public void showMainScreen(String code) {
        Intent intent = new Intent(this, PointListActivity.class);
        intent.putExtra(Const.ARGUMENT_USER_CODE, code);
        startActivity(intent);
        finish();
    }

    @Override
    public void showShortSnackbar(int resId) {
        Snackbar.make(loginConstraintLayout, resId, Snackbar.LENGTH_SHORT).show();
    }
}
