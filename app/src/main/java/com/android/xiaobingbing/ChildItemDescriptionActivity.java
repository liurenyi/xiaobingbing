package com.android.xiaobingbing;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.android.xiaobingbing.util.GameManager;

public class ChildItemDescriptionActivity extends AppCompatActivity {

    public static final String TAG = "liu-ChildItemDescriptionActivity";

    private TextView childTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_child_item_description);
        init();
    }

    private void init() {
        Intent intent = getIntent();
        String childExtra = intent.getStringExtra("child");
        String groupExtra = intent.getStringExtra("group");
        childTitle = (TextView) this.findViewById(R.id.child_title);
        if (groupExtra != null) {
            childTitle.setText(groupExtra);
        }
        String url = GameManager.queryDreamSQL(groupExtra, childExtra);
        Log.e(TAG, "url = " + url);
        if (url != null) {
            // TODO: 2017/5/17
        }
    }


}
