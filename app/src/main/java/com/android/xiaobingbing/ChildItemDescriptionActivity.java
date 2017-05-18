package com.android.xiaobingbing;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import com.android.xiaobingbing.util.GameManager;

public class ChildItemDescriptionActivity extends AppCompatActivity {

    public static final String TAG = "liu-ChildItemDescriptionActivity";

    private TextView childTitle;
    private TextView nullContent;
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
        nullContent = (TextView) this.findViewById(R.id.null_content);
        webView = (WebView) this.findViewById(R.id.webView);
        if (groupExtra != null) {
            childTitle.setText(groupExtra);
        }
        String url = GameManager.queryDreamSQL(groupExtra, childExtra);
        Log.e(TAG, "url = " + url.length());
        if (url.length() > 0) {
            GameManager.createProgressDialog(ChildItemDescriptionActivity.this);
            webView.setVisibility(View.VISIBLE);
            nullContent.setVisibility(View.GONE);
            webView.loadUrl(url);
            webView.getSettings().setJavaScriptEnabled(true); // 如果访问的页面中有Javascript，则webview必须设置支持Javascript
            //设置自适应屏幕，两者合用
            webView.getSettings().setUseWideViewPort(true);
            webView.getSettings().setLoadWithOverviewMode(true);
            //缩放操作
            webView.getSettings().setSupportZoom(true); //支持缩放，默认为true。是下面那个的前提。
            webView.getSettings().setBuiltInZoomControls(true); //设置内置的缩放控件。若为false，则该WebView不可缩放
            webView.getSettings().setDisplayZoomControls(false); //隐藏原生的缩放控件
            //其他细节操作
            webView.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK); //关闭webview中缓存
            webView.getSettings().setAllowFileAccess(true); //设置可以访问文件
            webView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true); //支持通过JS打开新窗口
            webView.getSettings().setLoadsImagesAutomatically(true); //支持自动加载图片
            webView.getSettings().setDefaultTextEncodingName("utf-8");//设置编码格式
            webView.setWebViewClient(new WebViewClient() {
                @Override
                public boolean shouldOverrideUrlLoading(WebView view, String url) {
                    view.loadUrl(url);
                    return true;
                }
            });
            // 网页加载的状态情况100代码加载完成
            webView.setWebChromeClient(new WebChromeClient() {
                @Override
                public void onProgressChanged(WebView view, int newProgress) {
                    if (newProgress >= 80) {
                        GameManager.dismissProgressDialog(ChildItemDescriptionActivity.this);
                    }
                    super.onProgressChanged(view, newProgress);
                }
            });
        } else if (url.length() == 0) {
            webView.setVisibility(View.GONE);
            nullContent.setVisibility(View.VISIBLE);
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

    @Override
    protected void onDestroy() {
        if (webView != null) {
            webView.destroy();
            webView = null;
        }
        super.onDestroy();
    }
}
