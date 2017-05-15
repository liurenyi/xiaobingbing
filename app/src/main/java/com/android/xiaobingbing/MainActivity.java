package com.android.xiaobingbing;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.preference.PreferenceManager;
import android.support.v4.app.FragmentTabHost;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.xiaobingbing.ArenaFragment.ArenaFragment;
import com.android.xiaobingbing.Cache.CacheManager;
import com.android.xiaobingbing.SkyFragment.SkyFragment;
import com.android.xiaobingbing.TeamFragment.TeamFragment;
import com.android.xiaobingbing.data.DreamBattleArray;
import com.android.xiaobingbing.util.GameManager;

import org.litepal.crud.DataSupport;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = "liu-MainActivity";
    private FragmentTabHost tabHost;
    private View inflate;
    private TextView tabItemText;
    private ImageView tabItemImage;

    private Class[] fragment = new Class[]{ArenaFragment.class, TeamFragment.class, SkyFragment.class};
    private String[] tabTitles;
    private int[] imgSelectors = new int[]{R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//            getWindow().getDecorView().setFitsSystemWindows(true);
//            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_LAYOUT_FLAGS);
//            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }
        setContentView(R.layout.activity_main);
        initGameData();
        tabHost = (FragmentTabHost) this.findViewById(R.id.tabHost);
        tabHost.setup(MainActivity.this, getSupportFragmentManager(), android.R.id.tabcontent);
        for (int i = 0; i < fragment.length; i++) {
            inflate = LayoutInflater.from(this).inflate(R.layout.tab_item, null);
            tabItemText = (TextView) inflate.findViewById(R.id.tab_item_text);
            tabItemImage = (ImageView) inflate.findViewById(R.id.tab_item_image);
            tabItemText.setText(tabTitles[i]);
            tabItemImage.setImageResource(imgSelectors[i]);
            tabHost.addTab(tabHost.newTabSpec("" + i).setIndicator(inflate), fragment[i], null);
        }

    }

    private void initGameData() {
        tabTitles = getResources().getStringArray(R.array.home_tab);
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        String heroString = preferences.getString(GameManager.HEROIC_INIT_KEY, null);
        String dreamBattleString = preferences.getString(GameManager.DREAM_BATTLE_ARRAY_INIT_KEY, null);
        Log.e(TAG, "heroString = " + heroString);
        Log.e(TAG, "dreamBattleString = " + dreamBattleString);
        if (heroString == null) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    GameManager.getLocationFile(MainActivity.this, CacheManager.FILE_NAME_1);
                }
            }).start();
        }
        if (dreamBattleString == null) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    GameManager.getLocationFile(MainActivity.this, CacheManager.FILE_NAME_2);
                }
            }).start();
        }
    }


}
