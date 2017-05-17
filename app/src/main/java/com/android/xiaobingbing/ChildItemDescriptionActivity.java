package com.android.xiaobingbing;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import com.android.xiaobingbing.util.GameManager;

public class ChildItemDescriptionActivity extends AppCompatActivity {

    public static final String TAG = "liu-ChildItemDescriptionActivity";

    private TextView childTitle;
    private WebView webView;

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
        webView = (WebView) this.findViewById(R.id.webView);
        if (groupExtra != null) {
            childTitle.setText(groupExtra);
        }
        String url = GameManager.queryDreamSQL(groupExtra, childExtra);
        Log.e(TAG, "url = " + url);
        if (url != null) {
            webView.loadUrl(url);
            webView.getSettings().setJavaScriptEnabled(true); // 如果访问的页面中有Javascript，则webview必须设置支持Javascript
            webView.setWebViewClient(new WebViewClient() {
                @Override
                public boolean shouldOverrideUrlLoading(WebView view, String url) {
                    view.loadUrl(url);
                    return true;
                }
            });
        }
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (webView.canGoBack()) {
                webView.goBack();
                return true;
            }
        }
        return super.onKeyDown(keyCode, event);
    }
}
