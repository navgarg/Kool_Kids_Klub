package com.test.koolkidsklub;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class UserInfoAdapter extends ArrayAdapter<UserInfo> {

    public UserInfoAdapter(Activity context, ArrayList<UserInfo> user) {
        super(context, 0, user);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item_location, parent, false);
        }

        UserInfo currentUser = getItem(position);

        TextView nameTextView = (TextView) listItemView.findViewById(R.id.name_text_view);
        nameTextView.setText(currentUser.getName());

        TextView latTextView = (TextView) listItemView.findViewById(R.id.lat_text_view);
        latTextView.setText("lat: " + currentUser.getLat());

        TextView longTextView = (TextView) listItemView.findViewById(R.id.long_text_view);
        longTextView.setText("long: " + currentUser.getLong());



        return listItemView;
    }
}
