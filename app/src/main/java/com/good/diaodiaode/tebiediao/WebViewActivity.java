package com.good.diaodiaode.tebiediao;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.webkit.ConsoleMessage;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class WebViewActivity extends AppCompatActivity {

    WebView mWebView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);
        mWebView = (WebView) findViewById(R.id.webview);
        mWebView.getSettings().setJavaScriptEnabled(true);

        mWebView.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                return super.shouldOverrideUrlLoading(view, url);
            }

            @Override
            public void onPageFinished(WebView webView, String url) {

                mWebView.loadUrl("javascript:JavascriptBridge.js");

            }
        });

        mWebView.setWebChromeClient(new WebChromeClient(){
            @Override

            public boolean onConsoleMessage(ConsoleMessage cm) {

                Log.d("test", cm.message() + " -- From line " + cm.lineNumber() + " of " + cm.sourceId() );

                return true;

            }

            @Override

            public boolean onJsAlert(WebView view, String url, String message, JsResult result) {

//                Toast.makeText(WebViewActivity.this, message, Toast.LENGTH_SHORT).show();

                return false;

            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        mWebView.loadData("","text/html","UTF-8");
//        mWebView.loadUrl("file:///android_asset/test.html");
        mWebView.loadUrl("http://www.baidu.com");
    }
}
