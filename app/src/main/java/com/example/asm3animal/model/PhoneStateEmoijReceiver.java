package com.example.asm3animal.model;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.asm3animal.MainActivity;
import com.example.asm3animal.fragment.MenuFragment;

import java.io.IOException;

public class PhoneStateEmoijReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        TelephonyManager telephony = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        // lắng nghe cuộc gọi đến
        telephony.listen(new PhoneStateListener() {
            @SuppressLint("WrongConstant")
            @Override
            // được gọi lại khi trạng trái của thiết bị thay dổi
            public void onCallStateChanged(int state, String phoneNumber) {
                super.onCallStateChanged(state, phoneNumber);
                // khi trạng thái cuộc gọi không hoạt động
                // hoặc cuộc gọi ngắt kết nối
                // hoặc khi có điện thoại đổ chuông
                if (state == TelephonyManager.CALL_STATE_IDLE ||
                        state == TelephonyManager.CALL_STATE_OFFHOOK ||
                        state == TelephonyManager.CALL_STATE_RINGING) {
                    try {
                        SharedPreferences pref = context.getSharedPreferences(MainActivity.DATABASE, Context.MODE_PRIVATE);
                        // lấy dữ liêu dựa vào key : phoneNumer
                        String path = pref.getString(phoneNumber, null);
                        // nếu có dữ liệu
                        if (path != null) {
                            Bitmap bipmapAnimal = MenuFragment.decodeSampleDrawableFromFile(path, 400, 400);
                            ImageView ivAnimal = new ImageView(context);
                            ivAnimal.setImageBitmap(bipmapAnimal);
                            ivAnimal.setMaxHeight(200);
                            Toast toast = new Toast(context);
                            toast.setView(ivAnimal);
                            toast.setDuration(Toast.LENGTH_SHORT);
                            toast.show();
                        }
                    } catch (IOException e) {
                        Toast.makeText(context, "DATA ERROR!!!", Toast.LENGTH_SHORT).show();
                    }
                }
            }
            // LISTEN_CALL_STATE lắng nghe các thay đổi từ trạng thái cuộc gọi của thiết bị
        }, PhoneStateListener.LISTEN_CALL_STATE);
    }
}
