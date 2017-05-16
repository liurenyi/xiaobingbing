package com.android.xiaobingbing.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

import com.android.xiaobingbing.Cache.CacheManager;
import com.android.xiaobingbing.data.DreamBattleArray;
import com.android.xiaobingbing.data.HeroicInfo;
import com.android.xiaobingbing.data.SkyBattleArray;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.litepal.crud.DataSupport;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by liurenyi on 2017/5/12.
 */
public class GameManager {

    public static String TAG = "liu-GameManager";
    public static final String HEROIC_INIT_KEY = "heroic_init";
    public static final String DREAM_BATTLE_ARRAY_INIT_KEY = "dream_battle_array";
    public static final String SKY_BATTLT_ARRAY_INIT_KEY = "sky_battle_array";

    public static void getLocationFile(Context context, String fileName) {
        try {
            InputStream stream = context.getAssets().open(fileName);
            InputStreamReader inputStreamReader = new InputStreamReader(stream, "UTF-8");
            BufferedReader br = new BufferedReader(inputStreamReader);
            String line;
            StringBuilder sb = new StringBuilder();
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
            if (CacheManager.FILE_NAME_1.equals(fileName)) {
                parseHeroicLocalFile(sb.toString(), context);
            } else if (CacheManager.FILE_NAME_2.equals(fileName)) {
                parseDreamLocalFile(sb.toString(), context);
            } else if (CacheManager.FILE_NAME_3.equals(fileName)) {

            }
        } catch (IOException e) {
            e.printStackTrace();
            Log.e(TAG, "file read error");
        }
    }

    public static void parseHeroicLocalFile(String data, Context context) {
        try {
            JSONArray array = new JSONArray(data);
            List<HeroicInfo> lists = new ArrayList<>();
            for (int i = 0; i < array.length(); i++) {
                JSONObject jsonObject = array.getJSONObject(i);
                HeroicInfo heroicInfo = new HeroicInfo();
                heroicInfo.setHeroicId(jsonObject.getString("id"));
                heroicInfo.setHeroicName(jsonObject.optString("heroicName"));
                heroicInfo.setAttribute(jsonObject.optString("attribute"));
                heroicInfo.setStation(jsonObject.optString("Station"));
                lists.add(heroicInfo);
            }
            DataSupport.saveAll(lists);
            savePreference(context, HEROIC_INIT_KEY, "all heroic data init");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public static void parseDreamLocalFile(String data, Context context) {
        try {
            JSONArray array = new JSONArray(data);
            List<DreamBattleArray> lists = new ArrayList<>();
            for (int i = 0; i < array.length(); i++) {
                JSONObject jsonObject = array.getJSONObject(i);
                DreamBattleArray dream = new DreamBattleArray();
                dream.setBossId(jsonObject.getString("id"));
                dream.setBossName(jsonObject.getString("bossName"));
                dream.setBossRaider(jsonObject.getString("raiders"));
                dream.setBossVersion(jsonObject.getString("version"));
                lists.add(dream);
            }
            DataSupport.saveAll(lists);
            savePreference(context, DREAM_BATTLE_ARRAY_INIT_KEY, "dream battle array data alread init");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public static void parseSkyLocalFile(String data, Context context) {
        try {
            JSONArray array = new JSONArray(data);
            List<SkyBattleArray> lists = new ArrayList<>();
            for (int i = 0; i < array.length(); i++) {
                JSONObject jsonObject = array.getJSONObject(i);
                SkyBattleArray sky = new SkyBattleArray();
                sky.setFloor(jsonObject.getInt("floor"));
                sky.setFloorBoss(jsonObject.getString("floorBoss"));
                sky.setWin(jsonObject.getString("win"));
                sky.setDescription(jsonObject.optString("description"));
                lists.add(sky);
            }
            DataSupport.saveAll(lists);
            savePreference(context, SKY_BATTLT_ARRAY_INIT_KEY, "sky battle array data alread init");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public static void savePreference(Context context, String key, String values) {
        SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(context).edit();
        editor.putString(key, values);
        editor.apply();
    }

    public static String[] queryDreamSQL(String string) {
        List<DreamBattleArray> dreamBattleArrays = DataSupport.where("bossName = ?", string).find(DreamBattleArray.class);
        StringBuilder sb = new StringBuilder();
        for (DreamBattleArray dreamBattleArray : dreamBattleArrays) {
            sb.append(dreamBattleArray.getBossVersion() + ": " + dreamBattleArray.getBossRaider() + "-");
        }
        return sb.toString().split("-");
    }
}
