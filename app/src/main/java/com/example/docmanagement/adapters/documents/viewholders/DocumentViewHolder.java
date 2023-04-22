package com.example.docmanagement.adapters.documents.viewholders;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.docmanagement.R;

public class DocumentViewHolder extends RecyclerView.ViewHolder {

  private final TextView docTitle;
  private final TextView docNumber;
  private final TextView docType;
  private final TextView docState;
  private final TextView docDate;
  private final TextView docStatus;

  public DocumentViewHolder(@NonNull View itemView) {
    super(itemView);

    docTitle = itemView.findViewById(R.id.doc_title);
    docNumber = itemView.findViewById(R.id.doc_number);
    docType = itemView.findViewById(R.id.doc_type);
    docState = itemView.findViewById(R.id.doc_state);
    docDate = itemView.findViewById(R.id.doc_date);
    docStatus = itemView.findViewById(R.id.doc_status);
  }

  public TextView getDocTitle() {
    return docTitle;
  }

  public TextView getDocNumber() {
    return docNumber;
  }

  public TextView getDocType() {
    return docType;
  }

  public TextView getDocState() {
    return docState;
  }

  public TextView getDocDate() {
    return docDate;
  }

  public TextView getDocStatus() {
    return docStatus;
  }
}
