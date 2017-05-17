package com.android.xiaobingbing.TeamFragment;

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
 * Created by liurenyi on 2017/5/15.
 */
public class MyDreamDataAdapter extends BaseExpandableListAdapter {

    public static final String TAG = "liu-MyDreamDataAdapter";

    private Context mContext;
    private List<String> group;
    private List<List<String>> child;


    public MyDreamDataAdapter(Context mContext) {
        this.mContext = mContext;
        initializeData();
    }

    public void initializeData() {
        group = new ArrayList<>();
        child = new ArrayList<>();
        String[] dreamBosses = mContext.getResources().getStringArray(R.array.dreamBoss);
        for (int i = 0; i < dreamBosses.length; i++) {
            String[] result = GameManager.queryDreamSQL(dreamBosses[i]);
            addInfo(dreamBosses[i], result);
        }
    }

    public void addInfo(String string, String[] s) {
        group.add(string);
        List<String> childItem = new ArrayList<>();
        for (int i = 0; i < s.length; i++) {
            childItem.add(s[i]);
        }
        child.add(childItem);
    }

    @Override
    public int getGroupCount() {
        return group.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return child.get(groupPosition).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return group.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return child.get(groupPosition).get(childPosition);
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
        String title = group.get(groupPosition);
        return getGroupView(title);
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        String childContext = child.get(groupPosition).get(childPosition);
        return getchildView(childContext);

    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    //创建组/子视图
    public TextView getGroupView(String s) {
        // Layout parameters for the ExpandableListView
        AbsListView.LayoutParams lp = new AbsListView.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, 80);

        TextView text = new TextView(mContext);
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

        TextView text = new TextView(mContext);
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
