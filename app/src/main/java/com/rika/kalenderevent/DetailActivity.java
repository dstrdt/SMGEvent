package com.rika.kalenderevent;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.NavUtils;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.rika.kalenderevent.Model.Event;

import java.util.ArrayList;

/**
 * Created by User on 27/12/2017.
 */

public class DetailActivity extends AppCompatActivity {
    private ArrayList<Integer> mImageID;
    private boolean isEvent = true;
    private Event mData;
    private String tipe;

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailsport);
        isEvent = getIntent().getBooleanExtra("Event", true);
        if(isEvent){
            tipe = "Sport";
        }
        else{
            tipe = "Hiburan";
        }
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        convertData();
        //addImageID();
        //bindData();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
    private void  convertData(){
        Intent intent = getIntent();
        String data = intent.getStringExtra(Intent.EXTRA_TEXT);
        Gson gson = new Gson();
        mData = gson.fromJson(data, new TypeToken<Event>(){}.getType());
        TextView tv_judul = (TextView) findViewById(R.id.judul);
        TextView tv_info = (TextView) findViewById(R.id.info);
        TextView tv_keterangan = (TextView) findViewById(R.id.keterangan);
        ImageView image = (ImageView) findViewById(R.id.gambar);
        //getSupportActionBar().setTitle("Event " + tipe);
        tv_judul.setText(mData.getNama());
        tv_info.setText(mData.getDetail());
        tv_keterangan.setText(mData.getKeterangan());
        Glide.with(this).load(mData.getGambar()).into(image);
    }
    private void bindData() {
        int position;
        String judul;
        String info[] = getResources().getStringArray(R.array.detail_event);
        String keterangan[] = getResources().getStringArray(R.array.keterangan_event);

        //lokasi = getResources().getStringArray(R.array.koordinat_umum);

        Intent intent = getIntent();
        judul = intent.getStringExtra(Intent.EXTRA_TEXT);
        position = intent.getIntExtra(Sport.POSITION, 0);
        TextView tv_judul = (TextView) findViewById(R.id.judul);
        TextView tv_info = (TextView) findViewById(R.id.info);
        TextView tv_keterangan = (TextView) findViewById(R.id.keterangan);
        ImageView image = (ImageView) findViewById(R.id.gambar);
        tv_judul.setText(judul);
        tv_info.setText(info[position]);
        tv_keterangan.setText(keterangan[position]);
        image.setImageResource(mImageID.get(position));

    }

    private void addImageID() {
        mImageID = new ArrayList<>();
        mImageID.add(R.drawable.cats);
        mImageID.add(R.drawable.cats);
        mImageID.add(R.drawable.cats);
        mImageID.add(R.drawable.cats);
        mImageID.add(R.drawable.cats);
        mImageID.add(R.drawable.cats);
        mImageID.add(R.drawable.cats);
        mImageID.add(R.drawable.cats);
    }

}
