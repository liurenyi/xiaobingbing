package com.android.xiaobingbing.TeamFragment;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.android.xiaobingbing.R;

import org.w3c.dom.Text;

import java.util.HashMap;
import java.util.List;

/**
 * Created by liurenyi on 2017/5/13.
 */
public class DreamAdapter extends BaseAdapter {

    public static final String TAG = "liu-DreamAdapter";

    public Context context;
    // public List<String> dreamList;
    List<HashMap<String,Object>> dreamLists;
    public LayoutInflater inflater;

    public DreamAdapter(Context context,List<HashMap<String,Object>> dreamLists) {
        this.context = context;
        this.dreamLists = dreamLists;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return dreamLists.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.dream_battle,null);
            viewHolder = new ViewHolder();
            viewHolder.bossName = (TextView) convertView.findViewById(R.id.boosName);
            viewHolder.bossBattle = (TextView) convertView.findViewById(R.id.boosBattle);
            viewHolder.bossVersion = (TextView) convertView.findViewById(R.id.bossVersion);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.bossName.setText(dreamLists.get(position).get("bossName").toString());
        viewHolder.bossVersion.setText(dreamLists.get(position).get("bossVersion").toString());
        viewHolder.bossBattle.setText(dreamLists.get(position).get("bossRaider").toString());
        return convertView;
    }

    class ViewHolder{
        public TextView bossName;
        public TextView bossBattle;
        public TextView bossVersion;
    }
}
