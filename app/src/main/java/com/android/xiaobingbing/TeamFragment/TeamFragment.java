package com.android.xiaobingbing.TeamFragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.Toast;

import com.android.xiaobingbing.Cache.CacheManager;
import com.android.xiaobingbing.ChildItemDescriptionActivity;
import com.android.xiaobingbing.R;
import com.android.xiaobingbing.data.DreamBattleArray;

import org.litepal.crud.DataSupport;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class TeamFragment extends Fragment implements ExpandableListView.OnChildClickListener {

    public static final String TAG = "liu-TeamFragment";

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
        Intent intent = new Intent();
        intent.setAction(CacheManager.KEY_SECOND_FRAGMENT);
        getContext().sendBroadcast(intent);
        listView = (ExpandableListView) view.findViewById(R.id.listView);
        adapter = new MyDreamDataAdapter(getContext());
        listView.setAdapter(adapter);
        listView.setOnChildClickListener(this);
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

    @Override
    public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
        Object child = adapter.getChild(groupPosition, childPosition);  // 获取当前child显示内容。
        String childResult = child.toString().split(":")[1]; // 去掉单人或者联机的内容
        long childId = adapter.getChildId(groupPosition, childPosition);
        int childrenCount = adapter.getChildrenCount(groupPosition);
        Object group = adapter.getGroup(groupPosition); // 获取父条目的内容
        Log.e(TAG, "childResult = " + childResult + " childId = " + childId + " childrenCount = " + childrenCount);
        Intent intent = new Intent();
        intent.setClass(getContext(), ChildItemDescriptionActivity.class);
        intent.putExtra("child", childResult);
        intent.putExtra("group", group.toString());
        startActivity(intent);
        return true;
    }
}
