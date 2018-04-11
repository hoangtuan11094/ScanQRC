package com.hoangtuan.scanqrc.ActivityView;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.balysv.materialmenu.MaterialMenuDrawable;
import com.hoangtuan.scanqrc.R;

public class WebViewActivity extends AppCompatActivity {
    private WebView mWebview;
    private Toolbar tBar;
    private MaterialMenuDrawable materialMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);
        mWebview = (WebView) findViewById(R.id.webView);


        final Activity activity = this;

        mWebview.setWebViewClient(new WebViewClient() {
            @SuppressWarnings("deprecation")
            @Override
            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                Toast.makeText(activity, description, Toast.LENGTH_SHORT).show();
            }

            @TargetApi(android.os.Build.VERSION_CODES.M)
            @Override
            public void onReceivedError(WebView view, WebResourceRequest req, WebResourceError rerr) {
                // Redirect to deprecated method, so you can use it in all SDK versions
                onReceivedError(view, rerr.getErrorCode(), rerr.getDescription().toString(), req.getUrl().toString());
            }
        });


        final Intent intent = getIntent();
        final String link = intent.getStringExtra("Link");
        WebSettings settings = mWebview.getSettings();
        settings.setJavaScriptEnabled(true);
        mWebview.loadUrl(link);

        tBar = (Toolbar) findViewById(R.id.tBarWeb);
        setSupportActionBar(tBar);
        materialMenu = new MaterialMenuDrawable(this, Color.WHITE, MaterialMenuDrawable.Stroke.THIN);
        materialMenu.setIconState(MaterialMenuDrawable.IconState.ARROW);
        tBar.setNavigationIcon(materialMenu);
        tBar.setTitleTextColor(Color.WHITE);
        tBar.setTitle("");
        tBar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });



    }
}
