package com.android.xiaobingbing.SkyFragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;

import com.android.xiaobingbing.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class SkyFragment extends Fragment {

    public static final String TAG = "liu-SkyFragment";

    private ExpandableListView skyListView;
    private View view;
    private MySkyAdapter adapter;
    public SkyFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_team, null);
        skyListView = (ExpandableListView) view.findViewById(R.id.listView);
        adapter = new MySkyAdapter(getContext());
        skyListView.setAdapter(adapter);
        return view;
    }

}
