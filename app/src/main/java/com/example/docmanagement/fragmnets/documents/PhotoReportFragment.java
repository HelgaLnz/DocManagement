package com.example.docmanagement.fragmnets.documents;

import static android.app.Activity.RESULT_OK;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.core.content.FileProvider;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.android.volley.VolleyError;
import com.example.docmanagement.R;
import com.example.docmanagement.constants.HttpConstants;
import com.example.docmanagement.helpers.HttpHelper;
import com.example.docmanagement.helpers.ToastHelper;
import com.example.docmanagement.helpers.VolleyCallback;
import com.example.docmanagement.models.responses.UploadResponse;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class PhotoReportFragment extends Fragment {

  private static final String DEBUG_TAG = PhotoReportFragment.class.toString();

  private static final int CAMERA_REQUEST = 1888;
  private static final int REQUEST_IMAGE_CAPTURE = 1;

  private Context context;
  private View view;
  private ImageView reportImageView;
  private Button photoButton;
  private Button sendButton;
  private EditText captionEditText;
  private HttpHelper httpHelper;
  private String currentPhotoPath;
  private String userId;
  private int dealId;

  public PhotoReportFragment() {
    super(R.layout.photo_report_fragment);
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
  public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);

    initValues(view);
    setOnClickListeners();
  }

  private void initValues(View view) {
    this.context = getContext();
    this.view = view;

    Bundle bundle = getArguments();
    if(bundle != null) {
      userId = bundle.getString(HttpConstants.USER_ID);
      dealId = bundle.getInt(HttpConstants.DEAL_ID);
    }

    httpHelper = new HttpHelper(context);

    reportImageView = view.findViewById(R.id.record_photo);
    photoButton = view.findViewById(R.id.button_photo);
    sendButton = view.findViewById(R.id.button_send);
    captionEditText = view.findViewById(R.id.photo_record_description);
  }

  private void setOnClickListeners() {
    Gson gson = new GsonBuilder().create();

    photoButton.setOnClickListener(v -> {
      Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
      if (takePictureIntent.resolveActivity(getActivity().getPackageManager()) != null) {
        File photoFile = null;
        try {
          photoFile = createImageFile();
        } catch (IOException e) {
          e.printStackTrace();
        }
        if (photoFile != null) {
          Uri photoURI = FileProvider.getUriForFile(
            context,
            "com.example.android.fileprovider",
            photoFile
          );
          takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
          startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);

          galleryAddPic();
          setPic();
        }
      }
    });
    sendButton.setOnClickListener(v -> {
      Drawable d = reportImageView.getDrawable();
      Bitmap bitmap = Bitmap.createBitmap(
        d.getIntrinsicWidth(),
        d.getIntrinsicHeight(),
        Bitmap.Config.ARGB_8888
      );
      httpHelper.uploadBitmap(bitmap, new VolleyCallback<JSONObject>() {
        @Override
        public void onSuccess(JSONObject response) {
          Log.d(DEBUG_TAG, "response: " + response.toString());
          try {
            httpHelper.attach(
              captionEditText.getText().toString(),
              userId,
              dealId,
              gson.fromJson(
                response.toString(),
                UploadResponse.class
              ).getResult(),
              new VolleyCallback<JSONObject>() {
                @Override
                public void onSuccess(JSONObject response) {
                  ToastHelper.showToast(context, "Отчет добавлен");
                  Navigation.findNavController(view).popBackStack();
                }

                @Override
                public void onError(VolleyError error) {
                  ToastHelper.showToast(context, "Ошибка при добавлении отчета");
                }
              }
            );
          } catch (Exception e) {
            e.printStackTrace();
          }
        }

        @Override
        public void onError(VolleyError error) {
          Log.e(DEBUG_TAG, "response: " + error.getMessage());
        }
      });
    });
  }

  @Override
  public void onActivityResult(int requestCode, int resultCode, Intent data) {
    setPic();
  }

  private File createImageFile() throws IOException {
    String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
    String imageFileName = "JPEG_" + timeStamp + "_";
    File storageDir = getActivity().getExternalFilesDir(Environment.DIRECTORY_PICTURES);
    File image = File.createTempFile(
      imageFileName,
      ".jpg",
      storageDir
    );

    currentPhotoPath = image.getAbsolutePath();
    return image;
  }

  private void galleryAddPic() {
    Intent mediaScanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
    File f = new File(currentPhotoPath);
    Uri contentUri = Uri.fromFile(f);
    mediaScanIntent.setData(contentUri);
    context.sendBroadcast(mediaScanIntent);
  }

  private void setPic() {
    // Get the dimensions of the View
    int targetW = reportImageView.getWidth();
    int targetH = reportImageView.getHeight();

    // Get the dimensions of the bitmap
    BitmapFactory.Options bmOptions = new BitmapFactory.Options();
    bmOptions.inJustDecodeBounds = true;

    BitmapFactory.decodeFile(currentPhotoPath, bmOptions);

    int photoW = bmOptions.outWidth;
    int photoH = bmOptions.outHeight;

    // Determine how much to scale down the image
//    int scaleFactor = Math.max(1, Math.min(photoW / targetW, photoH / targetH));

    // Decode the image file into a Bitmap sized to fill the View
    bmOptions.inJustDecodeBounds = false;
    bmOptions.inSampleSize = 0;
    bmOptions.inPurgeable = true;

    Bitmap bitmap = BitmapFactory.decodeFile(currentPhotoPath, bmOptions);
    reportImageView.setImageBitmap(bitmap);
  }

}
