package com.javaclass.anima.android19customview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    private MySurfaceView msv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        msv = new MySurfaceView(this);
        setContentView(msv);
    }
}
