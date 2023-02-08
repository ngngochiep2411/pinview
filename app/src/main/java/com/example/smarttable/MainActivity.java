package com.example.smarttable;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

import com.chaos.view.PinView;

public class MainActivity extends AppCompatActivity {

    Dialog dialog;
    PinView pinView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
//        transaction.add(R.id.container, new HomeFragment());
//        transaction.addToBackStack("kkk");
//        transaction.commit();
//        dialog = new Dialog(this);
//        dialog.setContentView(R.layout.layout_dialog);
//        dialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
    }

    public void showDialog(View view) {
//        dialog.show();
//        pinView = dialog.findViewById(R.id.firstPinView);
//        pinView.requestFocus();
//
//        pinView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                dialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
//            }
//        });

        DialogSMSFragment fragment = new DialogSMSFragment();
        fragment.show(getSupportFragmentManager(),DialogSMSFragment.TAG);

    }
}