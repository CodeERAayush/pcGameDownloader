package com.aayushpandey.gamedownloader;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
Button oneClick,parts,others;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
oneClick=findViewById(R.id.oneClick);
parts=findViewById(R.id.parts);
others=findViewById(R.id.others);
Intent intent=new Intent(this,webSite.class);
oneClick.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        intent.putExtra("Url","http://oceanofgames.com/");
        startActivity(intent);
    }
});
parts.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        intent.putExtra("Url","https://thepcgames.net/");
        startActivity(intent);
    }
});
others.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        intent.putExtra("Url","www.google.com");
        startActivity(intent);
    }
});

    }


}