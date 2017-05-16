package com.android.xiaobingbing.TeamFragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ExpandableListView;
import android.widget.ListView;

import com.android.xiaobingbing.R;
import com.android.xiaobingbing.data.DreamBattleArray;

import org.litepal.crud.DataSupport;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class TeamFragment extends Fragment {

//    private ListView listView;
        private ExpandableListView listView;
//    private DreamAdapter adapter;
        private MyDreamDataAdapter adapter;
    private List<String> dreamList = new ArrayList<>();
    private List<HashMap<String, Object>> dreamLists = new ArrayList<>();

    public TeamFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_team, null);
        listView = (ExpandableListView) view.findViewById(R.id.listView);
        adapter = new MyDreamDataAdapter(getContext());
        listView.setAdapter(adapter);
        return view;
    }

    public void getDreamInfo() {
        List<DreamBattleArray> dreamBattleArrayList = DataSupport.findAll(DreamBattleArray.class);
        for (DreamBattleArray dreamBattleArray : dreamBattleArrayList) {
            dreamList.add(dreamBattleArray.getBossName() + " " + dreamBattleArray.getBossRaider());
            HashMap<String, Object> hashMap = new HashMap<>();
            hashMap.put("bossName", dreamBattleArray.getBossName());
            hashMap.put("bossRaider", dreamBattleArray.getBossRaider());
            hashMap.put("bossVersion", dreamBattleArray.getBossVersion());
            dreamLists.add(hashMap);
        }
    }
}
