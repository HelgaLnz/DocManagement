package com.example.docmanagement.fragmnets.documents;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.docmanagement.R;

public class DocumentsFragment extends Fragment {

  private Context context;
  private View view;

  public DocumentsFragment() {
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
  }

  private void setOnClickListeners() {

  }
}
