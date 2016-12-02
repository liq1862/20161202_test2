package com.example.user.a20161202_test2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity {
    Button showbutton, writebutton, readbutton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        showbutton =(Button) findViewById(R.id.button);
        writebutton =(Button) findViewById(R.id.button2);
        readbutton = (Button) findViewById(R.id.button3);

//  =================================================================
 /*顯示路徑*/
        showbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                File f1 = getFilesDir();
                Log.d("FILE", f1.toString());
                File f2 = getCacheDir();
                Log.d("FILE", f2.toString());
                File f3 = getExternalFilesDir("");
                Log.d("FILE", f3.toString());
            }
        });
//  =============================================================
/*寫入資料*/
        writebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FileOutputStream fOut = null;
                try {

                    fOut = openFileOutput("mydata.txt", MODE_PRIVATE);
                    OutputStreamWriter osw = new OutputStreamWriter(fOut);
                    osw.write("She sell sea shells on the sea shore .");
                    osw.close();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
//  ====================================================================
/*讀取資料*/
        readbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                char[] buffer = new char[1];
                FileReader fr = null;
                StringBuilder sb = new StringBuilder();
                File file = new File(getFilesDir() + "/" + "mydata.txt");

                try {
                    fr = new FileReader(file);
                    while (fr.read(buffer)!= -1) {
                        sb.append(new String(buffer));
                    }
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Log.d("READFILE", sb.toString());
            }
        });
    }
}
