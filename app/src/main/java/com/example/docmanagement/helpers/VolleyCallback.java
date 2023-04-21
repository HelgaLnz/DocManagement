package com.example.docmanagement.helpers;

import com.android.volley.VolleyError;
import com.example.docmanagement.models.responses.ErrorResponse;

import org.json.JSONObject;

public interface VolleyCallback {
  void onSuccess(JSONObject response);
  void onError(VolleyError error);
}
