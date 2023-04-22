package com.example.docmanagement.helpers;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.docmanagement.constants.HttpConstants;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.util.HashMap;
import java.util.Map;


public class HttpHelper {

  public static final String DEBUG_TAG = HttpHelper.class.toString();

  private static final String BASE_URL = "https://dmapi.alnezis.space/";

  private static final String POST_AUTH = BASE_URL + "auth";
  private static final String POST_IMAGE = BASE_URL + "files/upload";
  private static final String GET_DEALS = BASE_URL + "deals/list";

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
            cb.onSuccess(response.getJSONObject(HttpConstants.RESULT));
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

  public void getDeals(String userId, VolleyCallback<JSONArray> cb) {
    final JsonObjectRequest request = new JsonObjectRequest(
      Request.Method.GET,
      GET_DEALS,
      new JSONObject(),
      response -> {
        try {
          Log.d(DEBUG_TAG, "getDeals: " + response.getJSONArray(HttpConstants.RESULT));
          cb.onSuccess(response.getJSONArray(HttpConstants.RESULT));
        } catch (JSONException e) {
          e.printStackTrace();
        }
      },
      error -> {
        error.printStackTrace();
        cb.onError(error);
      }
    ) {
      @Override
      public Map<String, String> getHeaders() {
        Map<String, String> params = new HashMap<>();
        params.put("USER-ID", userId);

        return params;
      }
    };

    requestQueue.add(request);
  }

  public void uploadBitmap(final Bitmap bitmap) {
    VolleyMultipartRequest request = new VolleyMultipartRequest(
      Request.Method.POST,
      POST_IMAGE,
      response -> {
        try {
          JSONObject obj = new JSONObject(new String(response.data));
          Log.d(DEBUG_TAG, "onResponse: " + obj.getString(HttpConstants.RESULT));
        } catch (JSONException e) {
          e.printStackTrace();
        }
      },
      error -> {
        Log.e("GotError", "" + error.toString());
      }) {

      @Override
      protected Map<String, DataPart> getByteData() {
        Map<String, DataPart> params = new HashMap<>();
        long imageName = System.currentTimeMillis();
        params.put(
          HttpConstants.FILE,
          new DataPart(imageName + ".png", getFileDataFromDrawable(bitmap))
        );
        return params;
      }
    };

    requestQueue.add(request);
  }

  private byte[] getFileDataFromDrawable(Bitmap bitmap) {
    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
    bitmap.compress(Bitmap.CompressFormat.PNG, 80, byteArrayOutputStream);
    return byteArrayOutputStream.toByteArray();
  }
}
