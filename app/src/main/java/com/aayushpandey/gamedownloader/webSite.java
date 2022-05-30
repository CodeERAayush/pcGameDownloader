package com.aayushpandey.gamedownloader;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DownloadManager;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.webkit.DownloadListener;
import android.webkit.URLUtil;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

public class webSite extends AppCompatActivity {
WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_site);
        webView=findViewById(R.id.webView);
        Intent intent = new Intent();
        String st= getIntent().getStringExtra("Url");
        webView.loadUrl(st);
        webView.setWebViewClient(new WebViewClient());
        WebSettings webSettings=webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webView.setDownloadListener(new DownloadListener() {
            @Override
            public void onDownloadStart(String url, String useragent, String contentDisposition, String mimetype, long contentLength) {
                // Getting the name of the file to be downloaded
                final String filename= URLUtil.guessFileName(url,contentDisposition,mimetype);
                //REquesting the Download manager to start download
                DownloadManager.Request myReq = new DownloadManager.Request(Uri.parse(url));
                myReq.allowScanningByMediaScanner();
                //to show the donloading notification
                myReq.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
                Toast.makeText(webSite.this, "Downloading", Toast.LENGTH_SHORT).show();
                //To show the downloaded item in the folder
                DownloadManager myManager=(DownloadManager) getSystemService(DOWNLOAD_SERVICE);
                myReq.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS,filename);
                //To start download
                myManager.enqueue(myReq);
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater=getMenuInflater();
        menuInflater.inflate(R.menu.menu_opt,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.backPress:
                onBackPressed();
                break;
            case R.id.forPress:
                onForwardPressed();
                break;
            case R.id.homePress:
                onHome();
                break;
            case R.id.refPress:
                onReload();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        if(webView.canGoBack()){
            webView.goBack();
        }else{
        super.onBackPressed();
    }}

        private void onForwardPressed(){
            if(webView.canGoForward()){
                webView.goForward();
            }
            else{
                Toast.makeText(this, "Cant go!", Toast.LENGTH_SHORT).show();
            }

}
private void onHome(){

    Intent intent = new Intent();
    String st= getIntent().getStringExtra("Url");
    webView.loadUrl(st);
}
private void onReload(){
        webView.reload();
}
}