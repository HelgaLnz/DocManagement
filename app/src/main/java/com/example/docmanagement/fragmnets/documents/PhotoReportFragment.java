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
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.core.content.FileProvider;
import androidx.fragment.app.Fragment;

import com.example.docmanagement.R;
import com.example.docmanagement.helpers.HttpHelper;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class PhotoReportFragment extends Fragment {

  private static final int CAMERA_REQUEST = 1888;
  private static final int REQUEST_IMAGE_CAPTURE = 1;

  private Context context;
  private View view;
  private ImageView reportImageView;
  private Button photoButton;
  private Button sendButton;
  private HttpHelper httpHelper;
  private String currentPhotoPath;

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

    httpHelper = new HttpHelper(context);

    reportImageView = view.findViewById(R.id.record_photo);
    photoButton = view.findViewById(R.id.button_photo);
    sendButton = view.findViewById(R.id.button_send);
  }

  private void setOnClickListeners() {
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
      httpHelper.uploadBitmap(bitmap);
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
