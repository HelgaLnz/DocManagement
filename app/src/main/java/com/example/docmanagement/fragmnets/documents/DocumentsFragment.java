package com.example.docmanagement.fragmnets.documents;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.view.MenuProvider;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.VolleyError;
import com.example.docmanagement.R;
import com.example.docmanagement.adapters.documents.DocumentsAdapter;
import com.example.docmanagement.constants.HttpConstants;
import com.example.docmanagement.helpers.HttpHelper;
import com.example.docmanagement.helpers.ToastHelper;
import com.example.docmanagement.helpers.VolleyCallback;
import com.example.docmanagement.models.Document;
import com.example.docmanagement.models.responses.AuthResponse;
import com.example.docmanagement.models.responses.DealResponse;
import com.example.docmanagement.models.responses.DocumentResponse;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class DocumentsFragment extends Fragment {

  private final String DEBUG_TAG = DocumentsFragment.class.toString();

  private Context context;
  private String userId;
  private String role;
  private RecyclerView documentsRecycler;
  private View view;
  private HttpHelper httpHelper;

  private Set<Document> documents;
  private DocumentsAdapter documentsAdapter;

  public DocumentsFragment() {
    super(R.layout.documents_fragment);
  }

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setHasOptionsMenu(true);
  }

  @Override
  public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
    inflater.inflate(R.menu.nav_menu, menu);
    super.onCreateOptionsMenu(menu, inflater);
  }

  @Override
  public boolean onOptionsItemSelected(@NonNull MenuItem item) {
    return super.onOptionsItemSelected(item);
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
    this.httpHelper = new HttpHelper(context);

    Bundle bundle = getArguments();
    assert bundle != null;
    userId = bundle.getString(HttpConstants.USER_ID);
    role = bundle.getString(HttpConstants.ROLE);
    documentsRecycler = view.findViewById(R.id.documents_recycler);

    documents = new HashSet<>();
    documentsAdapter = new DocumentsAdapter(view, documents);

    Gson gson = new GsonBuilder().create();
    this.httpHelper.getDeals(userId, new VolleyCallback<JSONArray>() {
      @Override
      public void onSuccess(JSONArray response) {
        try {
          List<DealResponse> dealsResponse = Arrays.asList(gson.fromJson(
            response.toString(),
            DealResponse[].class
          ));


          for (DealResponse deal : dealsResponse) {
            for (DocumentResponse document : deal.getDocuments()) {
              documents.add(new Document(document, userId));
            }
          }
          Log.d(DEBUG_TAG, "deals: " + documents);
          documentsAdapter.setItems(documents);
        } catch (Exception e) {
          e.printStackTrace();
          ToastHelper.showToast(context, "Ошибка сервера");
        }
      }

      @Override
      public void onError(VolleyError error) {
        Log.e(DEBUG_TAG, "onError: " + error.toString());
      }
    });

    Log.d(DEBUG_TAG, "initValues: " + documentsAdapter.getItemCount());
    documentsRecycler.setAdapter(documentsAdapter);
    documentsRecycler.setLayoutManager(new LinearLayoutManager(context));
  }

  private void setOnClickListeners() {

  }
}
