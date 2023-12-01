package com.example.asm3animal.fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.asm3animal.model.Animal;
import com.example.asm3animal.MainActivity;
import com.example.asm3animal.R;
import com.example.asm3animal.adapter.AnimalAdapter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;


public class MenuFragment extends Fragment {
    public static Context mContext;

    public RecyclerView rvAnimal;

    public List<Animal> listAnimals;

    public DrawerLayout mDrawer;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_menu, container, false);
        initView(v);
        return v;

    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mContext = context;
    }
    private void initView(View v) {
        mDrawer = v.findViewById(R.id.drawer);
        rvAnimal = v.findViewById(R.id.rv_animals);
        ImageView ivSeas = v.findViewById(R.id.iv_sea);
        ImageView ivMammal = v.findViewById(R.id.iv_mammal);
        ImageView ivBird = v.findViewById(R.id.iv_bird);
        ImageView ivLogo = v.findViewById(R.id.iv_logo);
        ivLogo.setImageBitmap(getRes(R.drawable.ic_logo, 300, 300));
        //Xử lý mở menu trái
        v.findViewById(R.id.iv_btn_list).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ivSeas.startAnimation(AnimationUtils.loadAnimation(mContext, R.anim.press_alpha));
                ivMammal.startAnimation(AnimationUtils.loadAnimation(mContext, R.anim.press_alpha));
                ivBird.startAnimation(AnimationUtils.loadAnimation(mContext, R.anim.press_alpha));
                mDrawer.openDrawer(GravityCompat.START);
            }
        });

        ivSeas.setImageBitmap(getRes(R.drawable.iv_seas, 200, 200));
        //Hiển thị ảnh động vật biển
        ivSeas.setOnClickListener(v1 -> {
            ivSeas.startAnimation(AnimationUtils.loadAnimation(mContext, androidx.appcompat.R.anim.abc_fade_in));
            showAnimals("seas");
        });

        ivMammal.setImageBitmap(getRes(R.drawable.iv_mammals, 200, 200));
        //Hiển thị ảnh động vật có vú
        ivMammal.findViewById(R.id.iv_mammal).setOnClickListener(v1 -> {
            ivMammal.startAnimation(AnimationUtils.loadAnimation(mContext, androidx.appcompat.R.anim.abc_fade_in));
            showAnimals("mammals");
        });

        ivBird.setImageBitmap(getRes(R.drawable.iv_bird, 200, 200));
        //Hiển thị ảnh chim muông
        ivBird.findViewById(R.id.iv_bird).setOnClickListener(v1 -> {
            ivBird.startAnimation(AnimationUtils.loadAnimation(mContext, androidx.appcompat.R.anim.abc_fade_in));
            showAnimals("bird");
        });

        //Hiển thị danh sách ảnh lên RecyclerView
        if (listAnimals != null) {
            AnimalAdapter animalAdapter = new AnimalAdapter(listAnimals, mContext);
            rvAnimal.setAdapter(animalAdapter);
        }
    }
    public void setData(List<Animal> listAnimals){
        this.listAnimals = listAnimals;
    }
    public boolean getDatafav(String key){
        SharedPreferences pref = mContext.getSharedPreferences(MainActivity.DATABASE, Context.MODE_PRIVATE);
        return pref.getBoolean(key, false);
    }
    /* đọc dự liệu và show ra mang hình menu
     * animalType chỉ định tên mục để thao tác */
    public void showAnimals(String animalType) {
        listAnimals = new ArrayList<>();
        rvAnimal.removeAllViews();
        String pathText = animalType+"/text";
        String pathIcon = animalType+"/photo/animal";
        String pathBackground = animalType+"/photo/background";
        try {
            String[] listIcon= mContext.getAssets().list(pathIcon);
            String[] listBackgound = mContext.getAssets().list(pathBackground);
            String[] listText = mContext.getAssets().list(pathText);
            for (int i = 0; i < listIcon.length; i++){
                Bitmap icon = decodeSampleDrawableFromFile(pathIcon + "/" + listIcon[i], 200, 200);
                Bitmap background = decodeSampleDrawableFromFile(pathBackground+"/"+listBackgound[i], 500, 500);
                String name = listIcon[i];
                String content = "";
                InputStream inp = mContext.getAssets().open(pathText+"/"+listText[i]);
                BufferedReader br = new BufferedReader(new InputStreamReader(inp, StandardCharsets.UTF_8));
                String tr;
                while ((tr = br.readLine()) != null){
                    if(tr.isEmpty()){
                        continue;
                    }
                    content += tr + "\n";
                }
                br.close();
                SharedPreferences pref = mContext.getSharedPreferences(MainActivity.DATABASE, Context.MODE_PRIVATE);
                String getPathFav = pathIcon + "/" + listIcon[i];
                boolean fav = pref.getBoolean(getPathFav, false);
                listAnimals.add(new Animal(icon, background, animalType, name, content, fav));
            }

        } catch (IOException e) {
            // thê 1 đối tượng rỗng nếu xảy ra lỗi
            listAnimals.add(new Animal(null, null, "path false", "data false", "data false", false));
            Toast.makeText(mContext, "Error Read file program!!!!!!!!!", Toast.LENGTH_SHORT).show();
        }

        GridLayoutManager gridLayoutManager = new GridLayoutManager(mContext, 3);
        gridLayoutManager.setOrientation(GridLayoutManager.VERTICAL);
        rvAnimal.setLayoutManager(gridLayoutManager);
        AnimalAdapter animalAdapter = new AnimalAdapter(listAnimals, mContext);
        rvAnimal.setAdapter(animalAdapter);
        mDrawer.closeDrawers();
    }
    private void doCLickAnimal(Animal animal) {
        //Chuyển sang màn hình chi tiết
        MainActivity act = (MainActivity) mContext;
        act.showDetail(listAnimals, animal);
    }
    /* lấy kích cỡ nhỏ hơn hoặc bằng kích cỡ chỉ định */
    public static int calculateInSampleSize(BitmapFactory.Options options, int reqWidth, int reqHeight) {

        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;

        if (height > reqHeight || width > reqWidth) {
            final int halfHeight = height / 2;
            final int halfWidth = width / 2;
            // tính kích cỡ size nhỏ hơn >= (chiều cao/chiều rộng) chỉ định
            while ((halfHeight / inSampleSize) >= reqHeight && (halfWidth / inSampleSize) >= reqWidth) {
                inSampleSize *= 2;
            }
        }

        return inSampleSize;
    }
    public static Bitmap decodeSampleDrawableFromFile(String path, int reqWidth, int reqHeight) throws IOException {
        InputStream inp = mContext.getAssets().open(path);
        // đối tượng để định nghĩa cho image
        BitmapFactory.Options options1 = new BitmapFactory.Options();
        // chỉ cho phép đọc thông tin
        options1.inJustDecodeBounds = true;
        BitmapFactory.decodeStream(inp, null, options1);
        inp.close();

        BitmapFactory.Options options2 = new BitmapFactory.Options();
        // lấy cỡ nhỏ hơn hoặc bằng kích thước chỉ định
        options2.inSampleSize = calculateInSampleSize(options1, reqWidth, reqHeight);

        InputStream inp2 = mContext.getAssets().open(path);
        Bitmap bitmap = BitmapFactory.decodeStream(inp2, null, options2);
        inp2.close();
        return bitmap;
    }
    public Bitmap getRes(int path, int reqWidth, int reqHeight){
        BitmapFactory.Options options1 = new BitmapFactory.Options();
        options1.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(getResources(), path, options1);

        BitmapFactory.Options options2 = new BitmapFactory.Options();
        // lấy cỡ nhỏ hơn hoặc bằng kích thước chỉ định
        options2.inSampleSize = calculateInSampleSize(options1, reqWidth, reqHeight);
        return BitmapFactory.decodeResource(getResources(), path, options2);
    }
}