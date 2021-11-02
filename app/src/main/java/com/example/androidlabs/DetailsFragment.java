package com.example.androidlabs;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import androidx.fragment.app.Fragment;

public class DetailsFragment extends Fragment {

    public static final String message_id = "msg_id";
    public static final String message_text = "msg_text";
    public static final String message_isSent = "msg_isSent";
    public static final String message_tab = "message_tab";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View result =  LayoutInflater.from(getContext()).inflate(R.layout.fragment_details, container, false);

        Bundle bundle = getArguments();

        if (bundle != null)
        {
            Button hide_button = result.findViewById(R.id.hide_button);
            hide_button.setOnClickListener(v -> {
                if (!bundle.getBoolean(message_tab)) {
                    requireActivity().finish();
                } else {
                    requireActivity().getSupportFragmentManager()
                            .beginTransaction()
                            .remove(this)
                            .commit();
                }
            });

            //show the message
            TextView message = (TextView) result.findViewById(R.id.messagehere);
            String text = "Message Here: " + bundle.getString(message_text);
            message.setText(text);

            //show the id:
            TextView messageID = (TextView) result.findViewById(R.id.id);
            String id = "ID = " + bundle.getLong(message_id);
            messageID.setText(id);

            //check the box
            CheckBox isSendCheckbox = (CheckBox) result.findViewById(R.id.isSend_checkbox);
            isSendCheckbox.setChecked(bundle.getBoolean(message_isSent));

        }
        return result;
    }
}