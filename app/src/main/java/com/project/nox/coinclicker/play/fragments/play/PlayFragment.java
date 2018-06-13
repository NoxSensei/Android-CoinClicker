package com.project.nox.coinclicker.play.fragments.play;

import android.annotation.SuppressLint;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.TextView;

import com.project.nox.coinclicker.R;
import com.project.nox.coinclicker.play.Data;

@SuppressLint("ValidFragment")
public class PlayFragment extends Fragment {

    private Data data;

    private long lastUpdate;

    private ImageButton play_button;

    private Animation coin_animation;

    private TextView textView;

    @SuppressLint("ValidFragment")
    public PlayFragment(Data data)
    {
        this.data = data;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstance)
    {
        return inflater.inflate(R.layout.fragment_play, null);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        initCoinClickButtonAction();
        initCoinValueText();
        coin_animation = AnimationUtils.loadAnimation(getContext(), R.anim.rotation_animation);
        textView = getView().findViewById(R.id.coins_number_textView);

        SensorEventListener sensorListener = new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent event) {
                float x = event.values[0];
                float y = event.values[1];
                float z = event.values[2];

                float accelerationSquareRoot = (x * x + y * y + z * z) / (SensorManager.GRAVITY_EARTH * SensorManager.GRAVITY_EARTH);
                long actualTime = event.timestamp;
                if (accelerationSquareRoot >= 2) //
                {
                    if (actualTime - lastUpdate < 500) {
                        return;
                    }
                    lastUpdate = actualTime;

                    addCoins();
                }
            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int accuracy) {

            }
        };
        SensorManager sensorManager = (SensorManager) getActivity().getSystemService(Context.SENSOR_SERVICE);
        sensorManager.registerListener(sensorListener, sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER), SensorManager.SENSOR_DELAY_NORMAL);
        lastUpdate = System.currentTimeMillis();

        coinsRefresher();
    }

    private void coinsRefresher()
    {
        final Handler mHandler = new Handler();
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                {
                    long coinsValue = data.getCoinsValue();
                    String text;

                    if(coinsValue<data.getMaxCoinsValue())
                        text="Coins: "+coinsValue;
                    else
                        text="You are so rich!";

                    textView.setText(text);

                    mHandler.postDelayed(this, 100);
                }
            }
        };
        mHandler.post(runnable);
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onStart() {
        super.onStart();

        coinsRefresher();
    }

    public void initCoinClickButtonAction()
    {
        play_button = (ImageButton) getView().findViewById(R.id.coin_click_button);
        play_button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v)
            {
                addCoins();
            }
        });
    }

    private void initCoinValueText() {
        String text="Coins: "+data.getCoinsValue();

        TextView textView = getView().findViewById(R.id.coins_number_textView);
        textView.setText(text);
    }

    void addCoins()
    {
        play_button.startAnimation(coin_animation);

        data.setCoinsValue(data.getCoinsValue() + (1 + data.getSeriousClickNum()));
    }
}
