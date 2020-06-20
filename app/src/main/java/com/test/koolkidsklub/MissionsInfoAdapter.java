package com.test.koolkidsklub;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class MissionsInfoAdapter extends ArrayAdapter<MissionsInfo> {

    public MissionsInfoAdapter(Activity context, ArrayList<MissionsInfo> mission) {
        super(context, 0, mission);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item_mission, parent, false);
        }

        MissionsInfo curr_mission = getItem(position);

        TextView name = listItemView.findViewById(R.id.mission_name);
        name.setText(curr_mission.getname());

        TextView short_desc = listItemView.findViewById(R.id.mission_short_desc);
        short_desc.setText(curr_mission.getShort_desc());

        return listItemView;

    }


}
