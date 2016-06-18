package com.example.kenzo.paintapplication;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    CustomView view1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        view1 = (CustomView)findViewById(R.id.paintView);


        Button redbtn = (Button)findViewById(R.id.red);
        redbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                view1.mColor = Color.RED;
                view1.setColor(view1.mColor);
            }
        });
        Button blackbtn = (Button)findViewById(R.id.black);
        blackbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                view1.mColor = Color.BLACK;
                view1.setColor(view1.mColor);
            }
        });
        Button yellowbtn = (Button)findViewById(R.id.yellow);
        yellowbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                view1.mColor = Color.YELLOW;
                view1.setColor(view1.mColor);
            }
        });
        Button erasebtn = (Button)findViewById(R.id.erase);
        erasebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                view1.mColor = Color.WHITE;
                view1.setColor(view1.mColor);
            }
        });


        Button deletebtn =(Button)findViewById(R.id.delete);
        deletebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                view1.all_delete();
            }
        });
        Button redobtn =(Button)findViewById(R.id.redo);
        redobtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                view1.onClickRedo();
            }
        });
        Button undobtn =(Button)findViewById(R.id.undo);
        undobtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                view1.onClickUndo();
            }
        });
        }
}
