package com.rika.kalenderevent;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MenuUtama extends AppCompatActivity {

    ImageView masuk1;
    ImageView  masuk2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_utama);

        masuk1 = (ImageView)findViewById(R.id.sport);
        masuk1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mainIntent =  new Intent(MenuUtama.this,Sport.class);
                mainIntent.putExtra("Event", true);
                startActivity(mainIntent);
            }
        });

        masuk2 = (ImageView)findViewById(R.id.hiburan);
        masuk2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mainIntent = new Intent(MenuUtama.this,Sport.class);
                mainIntent.putExtra("Event", false);
                startActivity(mainIntent);
            }
        });
    }
}
