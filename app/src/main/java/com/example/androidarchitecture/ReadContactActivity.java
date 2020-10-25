package com.example.androidarchitecture;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentResolver;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class ReadContactActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_contact);

        ListView list = findViewById(R.id.listview);
        ArrayList arrayList = fetchContacts();
        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, arrayList);
        list.setAdapter(arrayAdapter);
    }

    private ArrayList fetchContacts() {
        ArrayList<String> arrayList = new ArrayList<>();
        ContentResolver cr = getContentResolver();
        String[] mprojection = new String[]{
                ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME,
                ContactsContract.CommonDataKinds.Phone.NUMBER,
        };

        String mSelection = " display_name = ? OR display_name = ? ";
        String[] mSelectionArgs = new String[]{"UTE", "Maxi"};
        Cursor cursor = cr.query(
                ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                mprojection,
                mSelection,
                mSelectionArgs,
                "display_name ASC limit 5"
        );

        if (cursor != null && cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                String name = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
                String number = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                arrayList.add(name + "\n" + number);
            }
        }

        if (cursor != null) {
            cursor.close();
        }

        return arrayList;
    }
}