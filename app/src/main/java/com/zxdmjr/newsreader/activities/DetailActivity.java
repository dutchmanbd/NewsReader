package com.zxdmjr.newsreader.activities;

import android.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.zxdmjr.newsreader.R;
import com.zxdmjr.newsreader.constants.Constant;

import butterknife.BindView;
import butterknife.ButterKnife;
import dmax.dialog.SpotsDialog;

public class DetailActivity extends AppCompatActivity {

    @BindView(R.id.wv_detail_news)
    WebView wvDetailNews;

    AlertDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        ButterKnife.bind(this);

        dialog = new SpotsDialog(this);
        dialog.show();

        //webview
        wvDetailNews.getSettings().setJavaScriptEnabled(true);
        wvDetailNews.setWebChromeClient(new WebChromeClient());
        wvDetailNews.setWebViewClient(new WebViewClient(){
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);

                dialog.dismiss();

            }
        });

        if(getIntent() != null){

            if(!getIntent().getStringExtra(Constant.INTENT_WEB_URL).isEmpty()){

                wvDetailNews.loadUrl(getIntent().getStringExtra(Constant.INTENT_WEB_URL));
            }
        }
    }
}
