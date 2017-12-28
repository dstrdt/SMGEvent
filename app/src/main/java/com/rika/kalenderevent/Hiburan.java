package com.rika.kalenderevent;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

/**
 * Created by User on 27/12/2017.
 */

public class Hiburan extends AppCompatActivity implements EventAdapter.EventListener {
    private EventAdapter adapter;
    public static final String POSITION = "position";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hiburan);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.rc);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        String judul [] = getResources().getStringArray(R.array.judul_event);
        Log.v("Main",judul[0]);
        String ket [] =  getResources().getStringArray(R.array.keterangan_event);
        adapter = new EventAdapter(judul, ket, this);
        recyclerView.setAdapter(adapter);

    }

    @Override
    public void onClick(int position) {
        Intent intent = new Intent(Hiburan.this,DetailActivity.class);
        intent.putExtra(Intent.EXTRA_TEXT,adapter.getJudul(position));
        intent.putExtra(POSITION,position);
        startActivity(intent);
    }
}
