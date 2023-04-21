package com.example.docmanagement.fragmnets.authorization;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.android.volley.VolleyError;
import com.example.docmanagement.R;
import com.example.docmanagement.helpers.HttpHelper;
import com.example.docmanagement.helpers.ToastHelper;
import com.example.docmanagement.helpers.VolleyCallback;
import com.example.docmanagement.models.responses.AuthResponse;
import com.example.docmanagement.models.responses.ErrorResponse;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;

public class AuthorizationFragment extends Fragment {

  public static final String DEBUG_TAG = AuthorizationFragment.class.toString();

  private Context context;
  private View view;
  private EditText loginInput;
  private EditText passwordInput;
  private Button loginBtn;
  private HttpHelper httpHelper;

  public AuthorizationFragment() {
    super(R.layout.authorization_fragment);
  }

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

  }

  @Override
  public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);

    initValues(view);
    setOnClickListeners();
  }

  private void initValues(View view) {
    this.context = getContext();
    this.view = view;

    httpHelper = new HttpHelper(context);

    loginInput = view.findViewById(R.id.login_edit_text);
    passwordInput = view.findViewById(R.id.password_edit_text);
    loginBtn = view.findViewById(R.id.btn_login);
  }

  private void setOnClickListeners() {
    Gson gson = new GsonBuilder().create();

    loginBtn.setOnClickListener(v -> {
      httpHelper.signIn(
        loginInput.getText().toString(),
        passwordInput.getText().toString(),
        new VolleyCallback() {
          @Override
          public void onSuccess(JSONObject response) {
            Log.i(DEBUG_TAG, response.toString());
            AuthResponse authResponse = gson.fromJson(response.toString(), AuthResponse.class);
            Log.i(DEBUG_TAG, authResponse.toString());
          }

          @Override
          public void onError(VolleyError error) {
            Log.e(DEBUG_TAG, new String(error.networkResponse.data, StandardCharsets.UTF_8));

            if (error.networkResponse.data != null) {
              ToastHelper.showToast(
                context,
                gson.fromJson(
                  new String(error.networkResponse.data, StandardCharsets.UTF_8),
                  ErrorResponse.class
                ).getError().getMessage()
              );
            }
          }
        }
      );
    });
  }
}
