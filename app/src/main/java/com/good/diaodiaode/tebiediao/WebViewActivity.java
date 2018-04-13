package com.good.diaodiaode.tebiediao;

import android.annotation.TargetApi;
import android.graphics.Bitmap;
import android.os.Build;
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

            @TargetApi(Build.VERSION_CODES.M)
            @Override
            public void onPageFinished(WebView webView, String url) {

                mWebView.loadUrl("javascript:JavascriptBridge.js");
            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
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
//        mWebView.loadData("","text/html","UTF-8");
//        mWebView.loadUrl("file:///android_asset/test.html");
        mWebView.loadUrl("https://www.baidu.com/s/?ie=utf-8&f=8&rsv_bp=1&rsv_idx=2&tn=baiduhome_pg&wd=android&rsv_spt=1&oq=%25E5%259C%25B0%25E5%259B%25BE&rsv_pq=cb099b4500082b38&rsv_t=3634BMU6WIVhhsixuAFZYNCqF7kH2HqQV3rIR15qdPWGJeLf0fcj%2BhUXIOTO1hpUrUYO&rqlang=cn&rsv_enter=1&rsv_sug3=8&rsv_sug1=1&rsv_sug7=100&rsv_sug2=0&inputT=1050&rsv_sug4=25230&rsv_sug=1");
    }
}
