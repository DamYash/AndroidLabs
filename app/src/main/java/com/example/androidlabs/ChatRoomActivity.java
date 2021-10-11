package com.example.androidlabs;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ListView;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import java.util.List;

public class ChatRoomActivity extends AppCompatActivity {
    private MessageAdapter messageAdapter;
    private ListView listView;
    private List<Message> messages;

    private EditText newMessageView;
    private String newMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_room);

        setTitle("Messages");

        messages = new ArrayList<>();

        messageAdapter = new MessageAdapter(this, messages);
        listView = (ListView) findViewById(R.id.theListView);
        listView.setAdapter(messageAdapter);

        listView.setOnItemClickListener((parent, view, position, id) -> {
            Message message = messages.get(position);

            if(message != null){
                delete(position);
            }
        });

        newMessageView = (EditText) findViewById(R.id.EditText);

        findViewById(R.id.receive_button).setOnClickListener(view -> {
            View focusView = ChatRoomActivity.this.getCurrentFocus();
            if(newMessageView != null){
                newMessage = newMessageView.getText().toString();
                if(!newMessage.isEmpty()){
                    messages.add(new Message(1, newMessage));
                    newMessageView.setText("");
                    messageAdapter.notifyDataSetChanged();

                    if (focusView != null) {
                        InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                    }
                }
            }
        });

        findViewById(R.id.send_button).setOnClickListener(view -> {
            View focusView = ChatRoomActivity.this.getCurrentFocus();
            if(newMessageView != null){
                newMessage = newMessageView.getText().toString();
                if(!newMessage.isEmpty()){
                    messages.add(new Message(0, newMessage));
                    newMessageView.setText("");
                    messageAdapter.notifyDataSetChanged();

                    if (focusView != null) {
                        InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                    }
                }
            }
        });
    }

    public void purge(int position){
        messages.remove(position);
        messageAdapter.notifyDataSetChanged();
    }

    public void delete(int position){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);

        alertDialogBuilder.setMessage("Do you want to delete this row at position " + position);
        alertDialogBuilder.setPositiveButton("Yes", (arg0, arg1) -> purge(position));
        alertDialogBuilder.setNegativeButton("No", (dialog, which) -> finish());

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }
}

