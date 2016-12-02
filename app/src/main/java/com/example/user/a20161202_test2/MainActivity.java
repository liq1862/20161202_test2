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
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;

public class MainActivity extends AppCompatActivity {
    Button showbutton, writebutton, readbutton, readrawbutton, writeexternalbutton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        showbutton =(Button) findViewById(R.id.button);
        writebutton =(Button) findViewById(R.id.button2);
        readbutton = (Button) findViewById(R.id.button3);
        readrawbutton =(Button) findViewById(R.id.button4);
        writeexternalbutton = (Button) findViewById(R.id.button5);
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
//  ========================================================================
/*讀取res/raw的test1*/
        readrawbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InputStream is = null;
                InputStreamReader reader = null;
                StringBuilder sb = new StringBuilder();
                is = getResources().openRawResource(R.raw.test1);

                char[] buffer = new char[1];
                try {
                    reader = new InputStreamReader(is, "UTF-8");
                    while (reader.read(buffer) != -1) {
                        sb.append(new String(buffer));
                    }

                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                Log.d("RAWREAD", sb.toString());
            }
        });
//  ====================================================================
        /*外部儲存空間*/
//  ====================================================================
        //因寫入的地方為android預設，故不需設定權限
        writeexternalbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                File f3 = getExternalFilesDir("");
                Log.d("FILE", f3.toString());
                String wFile = f3.toString() + File.separator + "myfile2.txt";
                Log.d("FILE", "wFile:" + wFile);
                try {
                    FileOutputStream fos = new FileOutputStream(wFile);
                    OutputStreamWriter osw = new OutputStreamWriter(fos);  // 寫入資料
                    osw.write("She sell sea shells on the sea shore .");
                    osw.close();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
