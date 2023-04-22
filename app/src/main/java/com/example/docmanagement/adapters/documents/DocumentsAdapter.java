package com.example.docmanagement.adapters.documents;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.docmanagement.R;
import com.example.docmanagement.adapters.documents.viewholders.DocumentViewHolder;
import com.example.docmanagement.models.Document;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class DocumentsAdapter extends RecyclerView.Adapter {

  private final List<Document> documents;

  public DocumentsAdapter(Collection<Document> documents) {
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
    Log.d("TAG", "onBindViewHolder: " );

    Document document = documents.get(position);

    ((DocumentViewHolder) holder).getDocTitle().setText(document.getTitle());
    ((DocumentViewHolder) holder).getDocNumber().setText(document.getNumber());
    ((DocumentViewHolder) holder).getDocType().setText(document.getType());
    ((DocumentViewHolder) holder).getDocState().setText(document.getState());
    ((DocumentViewHolder) holder).getDocDate().setText(document.getDate());
    ((DocumentViewHolder) holder).getDocStatus().setText(document.getStatus());

    Log.d("TAG", "onBindViewHolder: " + ((DocumentViewHolder) holder).getDocTitle().getText());
  }

  @Override
  public int getItemCount() {
    return documents.size();
  }
}
