package com.testapp.ui.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.testapp.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import im.delight.android.webview.AdvancedWebView;

public class WebViewActivity extends AppCompatActivity {
    @BindView(R.id.wwOpenExternalLink) AdvancedWebView wwOpenExternalLink;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        String url = intent.getStringExtra("url");

        if (url != null) {
            wwOpenExternalLink.loadUrl(url);
        }
        else
        {
            Toast.makeText(this, getResources().getString(R.string.nothing_to_show), Toast.LENGTH_SHORT).show();
        }
    }
}
