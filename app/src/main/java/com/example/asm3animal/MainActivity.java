package com.example.asm3animal;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;

import com.example.asm3animal.fragment.DetailFragment;
import com.example.asm3animal.fragment.MenuFragment;
import com.example.asm3animal.model.Animal;
import com.example.asm3animal.model.PhoneStateEmoijReceiver;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    public final static String DATABASE = "save_pref";
    final static int UNIQUE_REQUEST_CODE = 101;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
    }
    /* đi đến fragment menu*/
    private void initViews() {
        MenuFragment menuFragment = new MenuFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.ln_main, menuFragment, null).commit();
        // nếu chưa cấp 2 quyền READ_PHONE_STATE và READ_CALL_LOG
        if (checkSelfPermission(Manifest.permission.READ_PHONE_STATE)
                != PackageManager.PERMISSION_GRANTED
                || checkSelfPermission(Manifest.permission.READ_CALL_LOG)
                != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{
                    Manifest.permission.READ_PHONE_STATE,
                    Manifest.permission.READ_CALL_LOG,
            }, UNIQUE_REQUEST_CODE);
        }
    }

    /* quay trở lại màn hình menu
     * listAniamls đổ dữ liệu trở lại menu */
    public void gotoMenu(List<Animal> listAnimals){
        MenuFragment menuFragment = new MenuFragment();
        // đổ dữ liệu trở lại khi quay lại menu
        menuFragment.setData(listAnimals);
        getSupportFragmentManager().beginTransaction()
                // hiêu ứng khi chuyển sang fragment
                .setCustomAnimations(R.anim.alpha_in,R.anim.alpha_out)
                .replace(R.id.ln_main, menuFragment, null).commit();
    }
    /* đi đến màn hình thứ 2
     * listAnimals và Animal đổ dữ liệu sang màn hình thứ 2*/
    public void showDetail(List<Animal> listAnimals, Animal animal) {
        DetailFragment detailFragment = new DetailFragment(listAnimals, animal);
        getSupportFragmentManager().beginTransaction()
                // hiệu ứng khi chuyển sang màn hình  2
                .setCustomAnimations(R.anim.alpha_in,R.anim.alpha_out)
                .replace(R.id.ln_main,detailFragment, null).commit();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }
}