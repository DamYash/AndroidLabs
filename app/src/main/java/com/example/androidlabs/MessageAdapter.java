package com.example.androidlabs;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;
public class MessageAdapter extends ArrayAdapter<Message> {
    private final Activity context;
    private final List<Message> messages;

    public MessageAdapter(Activity context, List<Message> messages) {
        super(context, 0, messages);
        this.context = context;
        this.messages = messages;
    }

    @Override
    public int getCount() {
        return messages.size();
    }

    @Override
    public Message getItem(int position) {
        return messages.get(position);
    }

    public View getView(int position, View view, ViewGroup parent) {

        if (view == null) {
            view = LayoutInflater.from(getContext()).inflate(R.layout.message, parent, false);
        }

        TextView incoming_message_view = view.findViewById(R.id.incoming_message_view);
        TextView outgoing_message_view = view.findViewById(R.id.outgoing_message_view);
        LinearLayout incoming_message_container = view.findViewById(R.id.incoming_message_container);
        LinearLayout outgoing_message_container = view.findViewById(R.id.outgoing_message_container);

        Message message = messages.get(position);

        //FOR INCOMING MESSAGES
        if(message.getDirection() == 0){
            incoming_message_view.setText(message.getText());
            incoming_message_container.setVisibility(View.VISIBLE);
            outgoing_message_container.setVisibility(View.GONE);
        }

        //FOR OUTGOING MESSAGES
        else {
            outgoing_message_view.setText(message.getText());
            outgoing_message_container.setVisibility(View.VISIBLE);
            incoming_message_container.setVisibility(View.GONE);
        }
        return view;
    }

    }
}
