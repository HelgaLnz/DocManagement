package com.example.docmanagement.adapters.documents;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.example.docmanagement.R;
import com.example.docmanagement.adapters.documents.viewholders.DocumentViewHolder;
import com.example.docmanagement.constants.HttpConstants;
import com.example.docmanagement.models.Document;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class DocumentsAdapter extends RecyclerView.Adapter {

  private final List<Document> documents;
  private final View view;
  private final String userId;

  public DocumentsAdapter(View view, Collection<Document> documents, String userId) {
    this.view = view;
    this.userId = userId;
    this.documents = new ArrayList<>(documents);
    notifyDataSetChanged();
  }

  public void setItems(Collection<Document> tweets) {
    documents.addAll(tweets);
    notifyDataSetChanged();
  }

  public void clearItems() {
    documents.clear();
    notifyDataSetChanged();
  }

  @NonNull
  @Override
  public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    View view = LayoutInflater.from(parent.getContext())
      .inflate(R.layout.document_recycler_item, parent, false);
    return new DocumentViewHolder(view);
  }

  @Override
  public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
    Document document = documents.get(position);

    ((DocumentViewHolder) holder).getCard().setOnClickListener(v -> {
        Bundle bundle = new Bundle();
        bundle.putString(HttpConstants.USER_ID, userId);
        bundle.putInt(HttpConstants.DEAL_ID, document.getDealId());

        Navigation
          .findNavController(view)
          .navigate(R.id.action_documentsFragment_to_photoRecordFragment, bundle);
      }
    );
    ((DocumentViewHolder) holder).getDocTitle().setText(document.getTitle());
    ((DocumentViewHolder) holder).getDocNumber().setText("Номер: " + document.getNumber());
    ((DocumentViewHolder) holder).getDocType().setText("Вид документа: " + document.getType());
    ((DocumentViewHolder) holder).getDocState().setText("Состояние: " + document.getState());
    ((DocumentViewHolder) holder).getDocDate().setText(document.getDate());
    ((DocumentViewHolder) holder).getDocStatus().setText(document.getStatus());
  }

  @Override
  public int getItemCount() {
    return documents.size();
  }
}
