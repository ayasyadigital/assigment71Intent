package com.niketgoel.assigment71intent;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.app.SearchManager;
import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.provider.ContactsContract;
import android.database.Cursor;
import android.net.Uri;
import android.widget.Toast;
import android.provider.MediaStore;
import android.widget.ImageView;


public class MainActivity extends AppCompatActivity {

    //For Assignment 7.1 for Entering Search Text
    private EditText editTextInput;
    //For Assignment 7.2 for Opening Contact List
    private final int PICK_CONTACT = 1;
    //For Assignment 7.3 for Image Gallery
    private static final int SELECT_PICTURE = 100;
    ImageView imageViewLoad;

    private EditText username;
    private EditText password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextInput = (EditText) findViewById(R.id.editTextInput);
        imageViewLoad = (ImageView) findViewById(R.id.imageView);
        username = (EditText)findViewById(R.id.login_username);

    }


    //For Assignment 7.1 for Entering Search Text
    public void onSearchClick(View v) {
        try {
            Intent intent = new Intent(Intent.ACTION_WEB_SEARCH);
            String term = editTextInput.getText().toString();
            intent.putExtra(SearchManager.QUERY, term);
            startActivity(intent);
        } catch (Exception e) {
            // TODO: handle exception
        }

    }

    //For Assignment 7.2 for Opening Contact List
    public void callContacts(View v) {

        Intent intent = new Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI);
        startActivityForResult(intent, PICK_CONTACT);
    }

    //For Assignment 7.3 for Image View Listing
    public void onLoadImageClick(View v){
        try{

            Intent intent = new Intent();
            intent.setType("image/*");
            intent.setAction(Intent.ACTION_GET_CONTENT);
            startActivityForResult(Intent.createChooser(intent, "Select Picture"), SELECT_PICTURE);
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
    //For Assignment 7.4 for Login Screen
    public void onLoginClick(View v) {


            Intent intent = new Intent(getApplicationContext(), user.class);
            intent.putExtra("username",username.getText().toString());
            startActivity(intent);


    }



    @Override
    protected void onActivityResult(int reqCode, int resultCode, Intent data) {
        // TODO Auto-generated method stub
        super.onActivityResult(reqCode, resultCode, data);

        //For Assignment 7.2 for Contact Listing
        if(reqCode == PICK_CONTACT) {
            if(resultCode == RESULT_OK) {
                Uri contactData = data.getData();
                Cursor c = getContentResolver().query(contactData, null, null, null, null);

                if(c.moveToFirst()) {
                    String name = c.getString(c.getColumnIndexOrThrow(ContactsContract.Contacts.DISPLAY_NAME));
                    Toast.makeText(this, "You've picked:" + name, Toast.LENGTH_LONG).show();
                }
            }
        }
        //For Assignment 7.3 for Image View Listing
        if (reqCode == SELECT_PICTURE) {
            if (resultCode == RESULT_OK) {
                // Get the url from data
                Uri selectedImageUri = data.getData();
                if (null != selectedImageUri) {
                    // Get the path from the Uri
                    String path = getPathFromURI(selectedImageUri);
                    // Set the image in ImageView
                    imageViewLoad.setImageURI(selectedImageUri);
                }
            }
        }
    }
    /* Get the real path from the URI */
    public String getPathFromURI(Uri contentUri) {
        String res = null;
        String[] proj = {MediaStore.Images.Media.DATA};
        Cursor cursor = getContentResolver().query(contentUri, proj, null, null, null);
        if (cursor.moveToFirst()) {
            int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            res = cursor.getString(column_index);
        }
        cursor.close();
        return res;
    }

}