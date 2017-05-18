package com.android.xiaobingbing.ArenaFragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.xiaobingbing.Cache.CacheManager;
import com.android.xiaobingbing.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class ArenaFragment extends Fragment {

    public ArenaFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        Intent intent = new Intent();
        intent.setAction(CacheManager.KEY_FIRST_FRAGMENT);
        getContext().sendBroadcast(intent);
        return inflater.inflate(R.layout.fragment_arena, container, false);
    }

}
