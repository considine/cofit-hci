package com.example.johnpconsidine.bengalworkouts.Fragments;


import android.app.Activity;
import android.app.AlertDialog;
import android.app.Fragment;
import android.content.ContentResolver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.johnpconsidine.bengalworkouts.HomeScreen;
import com.example.johnpconsidine.bengalworkouts.R;
import com.squareup.picasso.Picasso;

import java.io.ByteArrayOutputStream;
import java.io.File;

/**
 * A simple {@link Fragment} subclass.
 */
public class NewExercise extends android.support.v4.app.Fragment {
    public static final int PICK_PHOTO_REQUEST = 2; //status code for picking photo
    public static final int TAKE_PHOTO_REQUEST = 0; // status code for taking a photo
    public static final int MEDIA_TYPE_IMAGE = 4; //if we take a photo, code for saving it
    private String exerciseName;

    ImageView uploadImage;
    private Uri mUri;
    TextView messageText;
    public NewExercise() {
        // Required empty public constructor
    }


    @Override
    public void onAttach(Context context) {
        ((HomeScreen)getActivity()).hideCover();
        super.onAttach(context);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment


        final View view =  inflater.inflate(R.layout.fragment_new_exercis, container, false);
        Spinner dropdown = (Spinner)view.findViewById(R.id.spinner1);
        String[] items = new String[]{"EASY", "CARDIO", "HARD"};
        uploadImage = (ImageView) view.findViewById(R.id.uploadImage);

        messageText = (TextView) view.findViewById(R.id.messageText);
        messageText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setTitle("Type your message here:");
                final EditText input = new EditText(getContext());
                builder.setView(input);
                builder.setPositiveButton("Set", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        messageText.setText(input.getText().toString());
                    }
                });
                builder.show();
            }
        });
        TextView add = (TextView) view.findViewById(R.id.sendText);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "Your move has been successfully added", Toast.LENGTH_SHORT).show();
                ((HomeScreen) getActivity()).finish();
                startActivity(((HomeScreen) getActivity()).getIntent());
            }
        });
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_dropdown_item, items);
        dropdown.setAdapter(adapter);

        ((HomeScreen)getActivity()).setDrawerIcon(R.drawable.back);


        uploadImage.setOnClickListener(cameraListener);
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        final EditText input = new EditText(getContext());
        final Spinner spinner = new Spinner(getContext());
        final LinearLayout layout = new LinearLayout(getContext());
        layout.setOrientation(LinearLayout.VERTICAL);
        String[] workoutTypes = new String[]{"Legs", "Arms", "Core"};
        layout.addView(input);
        layout.addView(spinner);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_dropdown_item, workoutTypes);
        spinner.setAdapter(arrayAdapter);

        builder.setView(layout);
        builder.setMessage("Please name your exercise");
        builder.setTitle("New exercise");
        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //have to set the type to this
                TextView typeText = (TextView) view.findViewById(R.id.typeTextView);
                typeText.setText(spinner.getSelectedItem().toString());
                        exerciseName = input.getText().toString();

            }
        });
        builder.show();
        return view;
    }




    View.OnClickListener cameraListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            new AlertDialog.Builder(getContext())
                    .setTitle("Insert Photo")
                    .setMessage("How would you like to add a photo?")
                    .setPositiveButton("Pick from library", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            // continue with delete
                            Intent choosePhotoIntent = new Intent(Intent.ACTION_GET_CONTENT);
                            choosePhotoIntent.setType("image/*");
                            startActivityForResult(choosePhotoIntent, PICK_PHOTO_REQUEST);
                        }
                    })
                    .setNegativeButton("Take a photo", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            // do nothing
                            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                            intent.putExtra(MediaStore.EXTRA_OUTPUT, mUri);
                            startActivityForResult(intent, 100);
                        }
                    }).show();

        }
    };


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_PHOTO_REQUEST && resultCode == Activity.RESULT_OK) {
            Uri selectedImage = data.getData();
            ContentResolver cr = getActivity().getContentResolver();
            Bitmap bitmap;






                //get path first:
                String path = getRealPathFromURI(getContext(), selectedImage);
                Picasso.with(getContext()).load(new File(path)).fit().centerCrop().into(uploadImage);

//
//                String path = getRealPathFromURI(getContext(), selectedImage);
//                Log.v("TAG", "The path to this image is " + path);
//                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), selectedImage);
//
//                ByteArrayOutputStream stream = new ByteArrayOutputStream();
//                // Compress image to lower quality scale 1 - 100
//                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
//                byte[] image = stream.toByteArray();


                // Create the ParseFile
//                postImage  = new ParseFile("picture_1.jpeg", image);
//                Log.v(TAG, "The url is " + postImage.getUrl());

        }

        else if (requestCode == 100 && resultCode == Activity.RESULT_OK) {
            Uri selectedImage = data.getData();
            Bitmap photo = (Bitmap) data.getExtras().get("data");
            uploadImage.setImageBitmap(photo);
            String[] filePathColumn = {MediaStore.Images.Media.DATA};
            Cursor cursor = getActivity().getContentResolver().query(selectedImage,
                    filePathColumn, null, null, null);
            if (cursor != null) {
                cursor.moveToFirst();
            }
            else {
                return;
            }
            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            String picturePath = cursor.getString(columnIndex);
            cursor.close();
           // Log.v(TAG, "The picture path is " + picturePath);

            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            if (photo != null) {
                photo.compress(Bitmap.CompressFormat.JPEG, 100, stream);
                byte[] bytes = stream.toByteArray();

            }

         //   Log.v(TAG, "the mediu uri is " + postImage.getUrl());
        }
        else {
           // Log.e(TAG, "The request code " + resultCode);
        }
    }


    public String getRealPathFromURI(Context context, Uri contentUri) {
        Cursor cursor = null;
        try {
            String[] proj = { MediaStore.Images.Media.DATA };
            cursor = context.getContentResolver().query(contentUri,  proj, null, null, null);
            int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            cursor.moveToFirst();
            return cursor.getString(column_index);
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
    }
    @Override
    public void onResume() {
        super.onResume();
        ((HomeScreen)getActivity()).hideCover();
    }
}
