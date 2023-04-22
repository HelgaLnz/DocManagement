package com.example.docmanagement.fragmnets.documents;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.core.view.MenuProvider;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.docmanagement.R;
import com.example.docmanagement.adapters.documents.DocumentsAdapter;
import com.example.docmanagement.constants.HttpConstants;
import com.example.docmanagement.models.Document;

import java.util.ArrayList;
import java.util.List;

public class DocumentsFragment extends Fragment {

  private final String DEBUG_TAG = DocumentsFragment.class.toString();

  private Context context;
  private String userId;
  private String role;
  private RecyclerView documentsRecycler;
  private View view;

  private List<Document> documents;
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
    super.onCreateOptionsMenu(menu,inflater);
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

    Bundle bundle = getArguments();
    assert bundle != null;
    userId = bundle.getString(HttpConstants.USER_ID);
    role = bundle.getString(HttpConstants.ROLE);
    documentsRecycler = view.findViewById(R.id.documents_recycler);

    documents = new ArrayList<>();
    documents.add(new Document("title", "num", "type", "state", "date", "aaa"));
    documents.add(new Document("title", "num", "type", "state", "date", "aaa"));
    documents.add(new Document("title", "num", "type", "state", "date", "aaa"));
    documents.add(new Document("title", "num", "type", "state", "date", "aaa"));
    documents.add(new Document("title", "num", "type", "state", "date", "aaa"));

    documentsAdapter = new DocumentsAdapter(documents);
    Log.d(DEBUG_TAG, "initValues: " + documentsAdapter.getItemCount());
    documentsRecycler.setAdapter(documentsAdapter);
    documentsRecycler.setLayoutManager(new LinearLayoutManager(context));
  }

  private void setOnClickListeners() {

  }
}
