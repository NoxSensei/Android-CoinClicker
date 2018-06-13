package com.project.nox.coinclicker.play;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.project.nox.coinclicker.MainActivity;
import com.project.nox.coinclicker.R;
import com.project.nox.coinclicker.play.fragments.play.PlayFragment;
import com.project.nox.coinclicker.play.fragments.shop.ShopFragment;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;

public class PlayActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener{

    Data data;

    private String fileName = "data.txt";

    private boolean flagThread;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_play);

        flagThread = true;

        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(this);

        loadJson();

        loadFragment(new PlayFragment(data));

        Bundle bundle = new Bundle();
        bundle.putString("edttext", "From Activity");

        PlayFragment fragobj = new PlayFragment(data);
        fragobj.setArguments(bundle);
    }

    private void threads()
    {
        //<editor-fold desc="piggyRunnable">
        Runnable piggyRunnable = new Runnable() {
            @Override
            public void run() {
                synchronized (this) {
                    while (flagThread) {
                        long coinsValue = data.getCoinsValue();

                        if(coinsValue<data.getMaxCoinsValue()) {
                            data.setCoinsValue(coinsValue
                                    + data.getGreedyPiggyNum()
                                    + 10 * data.getLittleTommyNum()
                                    + 50 * data.getBusinessPackNum()
                                    + 200 * data.getSlyMarioNum());
                            try {
                                wait(1000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                        else
                            data.setCoinsValue(data.getMaxCoinsValue());

                    }
                }
            }
        };
        //</editor-fold>

        Thread piggyThread = new Thread(piggyRunnable);
        piggyThread.start();
    }

    @Override
    public void onStop() {
        super.onStop();

        flagThread = false;
        saveJson();
    }

    @Override
    public void onStart() {
        super.onStart();

        flagThread = true;
        threads();
    }

    @Override
    public void  onBackPressed()
    {
        Intent i = new Intent(PlayActivity.this, MainActivity.class);
        startActivity(i);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item)
    {
        Fragment fragment=null;

        switch (item.getItemId())
        {
            case R.id.navigation_play:
            {
                fragment = new PlayFragment(data);
                break;
            }
            case R.id.navigation_shop:
            {
                fragment = new ShopFragment(data);
                break;
            }
            case R.id.navigation_back:
            {
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                return true;
            }
        }
        return loadFragment(fragment);
    }

    private boolean loadFragment(Fragment fragment){
        if(fragment != null)
        {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container_play, fragment).commit();

            return true;
        }
        return false;
    }


    private void saveJson() {

        File file = new File(getApplicationContext().getDir("" ,Context.MODE_PRIVATE), fileName);
        OutputStream outputStream = null;
        Gson gson = new GsonBuilder().enableComplexMapKeySerialization().setPrettyPrinting()
                .create();
        try {
            outputStream = new FileOutputStream(file);
            BufferedWriter bufferedWriter;
            if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream,
                        StandardCharsets.UTF_8));
            } else {
                bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
            }

            gson.toJson(data, Data.class, bufferedWriter);
            bufferedWriter.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (outputStream != null) {
                try {
                    outputStream.flush();
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }


    private void loadJson() {
        data = new Data();

        Object jsonData = null;

        File file = new File(getApplicationContext().getDir("", Context.MODE_PRIVATE), fileName);
        InputStream inputStream = null;
        Gson gson = new GsonBuilder().enableComplexMapKeySerialization().setPrettyPrinting()
                .create();
        try {
            inputStream = new FileInputStream(file);
            InputStreamReader streamReader;
            if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                streamReader = new InputStreamReader(inputStream,
                        StandardCharsets.UTF_8);
            } else {
                streamReader = new InputStreamReader(inputStream, "UTF-8");
            }

            data = gson.fromJson(streamReader, Data.class);
            streamReader.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
