package com.example.smarttable;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.DialogFragment;

import java.util.concurrent.TimeUnit;

public class DialogSMSFragment extends DialogFragment {

    View view;
    public static final String TAG = DialogSMSFragment.class.getCanonicalName();
    CountDownTimer countDownTimer;
    TextView tvTimer;
    AppCompatButton btnSendCode;
    LinearLayout lnResend;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.layout_dialog,container,false);
        tvTimer = view.findViewById(R.id.tvTimer);
        btnSendCode  = view.findViewById(R.id.btnSendCode);
        lnResend = view.findViewById(R.id.lnResend);
        lnResend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startCownDownTimer();
            }
        });
        startCownDownTimer();
        return view;
    }

    private void startCownDownTimer() {
        //300000
        if(countDownTimer!=null){
            countDownTimer.cancel();
        }
        countDownTimer = new CountDownTimer(300000,1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                long m = TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished);
                long s = TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished - m*60*1000);
                tvTimer.setText(""+String.format("%02d:%02d",m,s));
                Log.d("testing",""+String.format("%02d:%02d",m,s));
            }

            @Override
            public void onFinish() {
                lnResend.setVisibility(View.VISIBLE);
            }
        };
        countDownTimer.start();
    }

    @Override
    public void onStart() {
        super.onStart();
        getDialog().getWindow().setLayout( WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.WRAP_CONTENT);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        countDownTimer.cancel();
    }
}
