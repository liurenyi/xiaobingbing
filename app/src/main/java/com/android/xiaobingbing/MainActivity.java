package com.android.xiaobingbing;

import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Build;
import android.preference.PreferenceManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTabHost;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

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
    private TextView homeTitle;

    private Class[] fragment = new Class[]{ArenaFragment.class, TeamFragment.class, SkyFragment.class};
    private String[] tabTitles;
    private int[] imgSelectors = new int[]{R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher};

    private List<String> permissionList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//            getWindow().getDecorView().setFitsSystemWindows(true);
//            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_LAYOUT_FLAGS);
//            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }
        setContentView(R.layout.activity_main);
        IntentFilter filter = new IntentFilter();
        filter.addAction(CacheManager.KEY_FIRST_FRAGMENT);
        filter.addAction(CacheManager.KEY_SECOND_FRAGMENT);
        filter.addAction(CacheManager.KEY_THIRD_FRAGMENT);
        registerReceiver(receiver, filter);
        initPermission();
        initGameData();
        homeTitle = (TextView) this.findViewById(R.id.home_title);
        tabHost = (FragmentTabHost) this.findViewById(R.id.tabHost);
        tabHost.setup(MainActivity.this, getSupportFragmentManager(), android.R.id.tabcontent);
        for (int i = 0; i < fragment.length; i++) {
            inflate = LayoutInflater.from(this).inflate(R.layout.tab_item, null);
            tabItemText = (TextView) inflate.findViewById(R.id.tab_item_text);
            tabItemImage = (ImageView) inflate.findViewById(R.id.tab_item_image);
            Log.e(TAG, "tabTitles[] = " + tabTitles.length);
            if (tabTitles.length == 0) {
                tabTitles = getResources().getStringArray(R.array.home_tab);
            }
            tabItemText.setText(tabTitles[i]);
            tabItemImage.setImageResource(imgSelectors[i]);
            tabHost.addTab(tabHost.newTabSpec("" + i).setIndicator(inflate), fragment[i], null);
        }
    }

    private void initPermission() {
        if (ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            permissionList.add(Manifest.permission.READ_EXTERNAL_STORAGE);
        }
        if (ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
            permissionList.add(Manifest.permission.READ_PHONE_STATE);
        }
        if (!permissionList.isEmpty()) {
            String[] permissions = permissionList.toArray(new String[permissionList.size()]);
            ActivityCompat.requestPermissions(MainActivity.this, permissions, 1);
        }
    }

    private void initGameData() {
        tabTitles = getResources().getStringArray(R.array.home_tab);
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        String heroString = preferences.getString(GameManager.HEROIC_INIT_KEY, null);
        String dreamBattleString = preferences.getString(GameManager.DREAM_BATTLE_ARRAY_INIT_KEY, null);
        String skyBattle = preferences.getString(GameManager.SKY_BATTLT_ARRAY_INIT_KEY, null);
        Log.e(TAG, "heroString = " + heroString);
        if (heroString == null) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    GameManager.getLocationFile(MainActivity.this, CacheManager.FILE_NAME_1);
                    GameManager.getLocationFile(MainActivity.this, CacheManager.FILE_NAME_2);
                    GameManager.getLocationFile(MainActivity.this, CacheManager.FILE_NAME_3);
                }
            }).start();
        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case 1:
                if (grantResults.length > 0) {
                    for (int result : grantResults) {
                        if (result != PackageManager.PERMISSION_GRANTED) {
                            Toast.makeText(MainActivity.this, "All permissions must be agreed to use the location function", Toast.LENGTH_LONG).show();
                        }
                    }
                } else {
                    Toast.makeText(MainActivity.this, "aaaaa", Toast.LENGTH_LONG).show();
                    finish();
                }
                break;
        }
    }

    private BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (action.equals(CacheManager.KEY_FIRST_FRAGMENT)) {
                String title = getResources().getString(R.string.home_tab_jjc);
                updateUI(title);
            } else if (action.equals(CacheManager.KEY_SECOND_FRAGMENT)) {
                String title = getResources().getString(R.string.home_tab_dream);
                updateUI(title);
            } else if (action.equals(CacheManager.KEY_THIRD_FRAGMENT)) {
                String title = getResources().getString(R.string.home_tab_sky);
                updateUI(title);
            }
        }
    };

    private void updateUI(String title) {
        if (homeTitle != null) {
            homeTitle.setText(title);
        }
    }
}


