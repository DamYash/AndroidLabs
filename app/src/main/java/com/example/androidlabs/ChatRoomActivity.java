package com.example.androidlabs;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class ChatRoomActivity extends AppCompatActivity {
    public static final String ITEM_SELECTED = "ITEM";
    public static final String ITEM_POSITION = "POSITION";
    public static final String ITEM_ID = "ID";

    private MessageAdapter messageAdapter;
    private ListView listView;
    private List<Message> messages;
    private EditText newMessageView;
    private String newMessage;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_room);
        setTitle("Messages");
        context = this;
        messages = new ArrayList<>();
        messageAdapter = new MessageAdapter(this, messages);
        listView = (ListView) findViewById(R.id.theListView);
        listView.setAdapter(messageAdapter);

        boolean isTablet = findViewById(R.id.fragmentLocation) != null;

        listView.setOnItemClickListener((list, item, position, id) ->{
            Bundle dataToPass = new Bundle();
            dataToPass.putString(DetailsFragment.message_text, messages.get(position).getText());
            dataToPass.putLong(DetailsFragment.message_id, messages.get(position).getId());
            dataToPass.putBoolean(DetailsFragment.message_tab, isTablet);
            dataToPass.putBoolean(DetailsFragment.message_isSent, messages.get(position).isSend());


            if (isTablet)
            {
                DetailsFragment dfragment = new DetailsFragment();
                dfragment.setArguments(dataToPass);
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fragmentLocation, dfragment)
                        .commit();
            } else
            {
                Intent nextActivity = new Intent(this, EmptyActivity.class);
                nextActivity.putExtras(dataToPass);
                startActivity(nextActivity);
            }
        });

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
                deleteMessage(position);
                return true;
            }
        });

        newMessageView = (EditText) findViewById(R.id.EditText);
        findViewById(R.id.send_button).setOnClickListener(view -> {
            View focusView = ChatRoomActivity.this.getCurrentFocus();
            if(newMessageView != null){
                newMessage = newMessageView.getText().toString();
                if(!newMessage.isEmpty()){
                    saveMessage(true, newMessage);
                    newMessageView.setText("");
                    if (focusView != null) {
                        InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                    }
                }
            }
        });
        findViewById(R.id.receive_button).setOnClickListener(view -> {
            View focusView = ChatRoomActivity.this.getCurrentFocus();
            if(newMessageView != null){
                newMessage = newMessageView.getText().toString();
                if(!newMessage.isEmpty()){
                    saveMessage(false, newMessage);
                    newMessageView.setText("");
                    if (focusView != null) {
                        InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                    }
                }
            }
        });
        updateInterface();
    }
    public void purgeMessage(int position){
        SQLiteDatabase database = new DatabaseHelper(context).getWritableDatabase();
        try {
            database.delete(DatabaseSchema.Messages.NAME, DatabaseSchema.Messages.Columns.ID + " LIKE ?", new String[]{messages.get(position).getId() + ""});
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            database.close();
        }
        updateInterface();
    }
    public void deleteMessage(int position){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage("Do you want to delete this row at position " + " with id " + messages.get(position).getId());
        alertDialogBuilder.setPositiveButton("Yes", (arg0, arg1) -> purgeMessage(position));
        alertDialogBuilder.setNegativeButton("No", null);
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }
    public void updateInterface(){
        SQLiteDatabase database = null;
        try {
            database = new DatabaseHelper(context).getReadableDatabase();
            Cursor cursor = database.query(DatabaseSchema.Messages.NAME, new String[]{DatabaseSchema.Messages.Columns.ID, DatabaseSchema.Messages.Columns.IS_SEND, DatabaseSchema.Messages.Columns.TEXT}, null, null, null, null, DatabaseSchema.Messages.Columns.ID);
            if(cursor.getCount() >= 0){
                cursor.moveToFirst();
                messages.clear();
                while (!cursor.isAfterLast()) {
                    @SuppressLint("Range") Long id = cursor.getLong(cursor.getColumnIndex(DatabaseSchema.Messages.Columns.ID));
                    @SuppressLint("Range") String text = cursor.getString(cursor.getColumnIndex(DatabaseSchema.Messages.Columns.TEXT));
                    @SuppressLint("Range") boolean isSend = cursor.getInt(cursor.getColumnIndex(DatabaseSchema.Messages.Columns.IS_SEND)) != 0;
                    messages.add(new Message(id, isSend, text));
                    cursor.moveToNext();
                }
            }
            printCursor(cursor, database.getVersion());
            cursor.close();
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            if(database != null){
                database.close();
            }
        }
        messageAdapter.notifyDataSetChanged();
    }
    public void saveMessage(boolean isSend, String text){
        if(text != null && !text.isEmpty()){
            SQLiteDatabase database = new DatabaseHelper(context).getWritableDatabase();
            try {
                ContentValues values = new ContentValues();
                values.put(DatabaseSchema.Messages.Columns.IS_SEND, isSend);
                values.put(DatabaseSchema.Messages.Columns.TEXT, text);
                database.insert(DatabaseSchema.Messages.NAME, null, values);
            } catch (Exception e){
                e.printStackTrace();
            } finally {
                database.close();
            }
            updateInterface();
        }
    }
    public void printCursor(Cursor c, int version){
        Log.d(getPackageName(), "Number of columns in cursor = " + c.getColumnCount());
        Log.d(getPackageName(), "Names of columns in cursor = " + c.getColumnNames()[0] + ", " + c.getColumnNames()[1] + ", " + c.getColumnNames()[2]);
        Log.d(getPackageName(), "Number of results in cursor = " + c.getCount());
        Log.d(getPackageName(), "Rows in cursor = ");
        if(c.getCount() > 0 && c.moveToFirst()){
            while (!c.isAfterLast()) {
                @SuppressLint("Range") Long id = c.getLong(c.getColumnIndex(DatabaseSchema.Messages.Columns.ID));
                @SuppressLint("Range") String text = c.getString(c.getColumnIndex(DatabaseSchema.Messages.Columns.TEXT));
                @SuppressLint("Range") boolean isSend = c.getInt(c.getColumnIndex(DatabaseSchema.Messages.Columns.IS_SEND)) != 0;
                Log.d(getPackageName(), id + " " + text + " " + isSend);
                c.moveToNext();
            }
        }
        Log.d(getPackageName(), "Database version = " + version);
    }
}
