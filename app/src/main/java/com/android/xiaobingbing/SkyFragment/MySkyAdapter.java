package com.android.xiaobingbing.SkyFragment;

import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.android.xiaobingbing.R;
import com.android.xiaobingbing.util.GameManager;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by liurenyi on 2017/5/16.
 */
public class MySkyAdapter extends BaseExpandableListAdapter {

    private Context context;
    private List<String> skyGroup;
    private List<List<String>> skyChild;

    public MySkyAdapter(Context context) {
        this.context = context;
        initSkyData();
    }

    private void initSkyData() {
        skyGroup = new ArrayList<>();
        skyChild = new ArrayList<>();
        String[] skyFloor = context.getResources().getStringArray(R.array.data_sky_battle);
        for (int i = 0; i < skyFloor.length; i++) {
            String[] skyWin = GameManager.querySkySQL(skyFloor[i]);
            addInfo(skyFloor[i], skyWin);
        }
    }

    public void addInfo(String string, String[] s) {
        skyGroup.add(string);
        List<String> childItem = new ArrayList<>();
        for (int i = 0; i < s.length; i++) {
            childItem.add(s[i]);
        }
        skyChild.add(childItem);
    }

    @Override
    public int getGroupCount() {
        return skyGroup.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return skyChild.get(groupPosition).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return skyGroup.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return skyChild.get(groupPosition).get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        return getGroupView(skyGroup.get(groupPosition));
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        return getchildView(skyChild.get(groupPosition).get(childPosition));
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }

    //创建组/子视图
    public TextView getGroupView(String s) {
        // Layout parameters for the ExpandableListView
        AbsListView.LayoutParams lp = new AbsListView.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, 80);

        TextView text = new TextView(context);
        text.setLayoutParams(lp);
        // Center the text vertically
        text.setGravity(Gravity.CENTER_VERTICAL | Gravity.LEFT);
        // Set the text starting position
        text.setPadding(50, 0, 0, 0);
        text.setTextColor(Color.BLUE);
        text.setTextSize(20);
        text.setText(s);
        return text;
    }

    public TextView getchildView(String s) {
        // Layout parameters for the ExpandableListView
        AbsListView.LayoutParams lp = new AbsListView.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, 80);

        TextView text = new TextView(context);
        text.setLayoutParams(lp);
        // Center the text vertically
        text.setGravity(Gravity.CENTER | Gravity.LEFT);
        // Set the text starting position
        text.setPadding(30, 0, 0, 0);
        text.setTextColor(Color.RED);
        text.setTextSize(14);
        text.setText(s);
        return text;
    }
}
