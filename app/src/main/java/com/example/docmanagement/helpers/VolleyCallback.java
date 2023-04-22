package com.example.docmanagement.helpers;

import com.android.volley.VolleyError;

import org.json.JSONObject;

public interface VolleyCallback<T> {
  void onSuccess(T response);
  void onError(VolleyError error);
}
