package com.example.docmanagement.helpers;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.docmanagement.constants.HttpConstants;

import org.json.JSONException;
import org.json.JSONObject;


public class HttpHelper {

  public static final String DEBUG_TAG = HttpHelper.class.toString();

  private static final String BASE_URL = "https://dmapi.alnezis.space/";

  private static final String POST_AUTH = BASE_URL + "auth";

  private final RequestQueue requestQueue;

  public HttpHelper(Context context) {
    this.requestQueue = Volley.newRequestQueue(context);
  }


  public void signIn(String login, String password, VolleyCallback cb) {
    JSONObject authDataRequestBody = null;
    try {
      authDataRequestBody = new JSONObject()
        .put(HttpConstants.LOGIN, login)
        .put(HttpConstants.PASSWORD, password);
    } catch (JSONException e) {
      e.printStackTrace();
    }

    if (authDataRequestBody != null) {
      final JsonObjectRequest request = new JsonObjectRequest(
        Request.Method.POST,
        POST_AUTH,
        authDataRequestBody,
        response -> {
          try {
            JSONObject resultObject = response.getJSONObject(HttpConstants.RESULT);
            cb.onSuccess(resultObject);
          } catch (JSONException e) {
            e.printStackTrace();
          }
        },
        error -> {
          error.printStackTrace();
          cb.onError(error);
        }
      );

      requestQueue.add(request);
    }
  }
}
