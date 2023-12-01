package com.example.asm3animal.adapter;

import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.example.asm3animal.model.Animal;
import com.example.asm3animal.MainActivity;
import com.example.asm3animal.R;

import java.util.List;

public class DetailAdapter extends PagerAdapter {

    List<Animal> listAnimals;
    Context mContext;

    public DetailAdapter(List<Animal> listAnimals, Context mContext) {
        this.listAnimals = listAnimals;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_detail, viewGroup,false);
        //
        TextView tvName = view.findViewById(R.id.tv_name_sceen3);
        TextView txtAnimal = view.findViewById(R.id.txt_animal_screen3);
        TextView tvPhone = view.findViewById(R.id.tv_number_screen3);
        //
        ImageView background = view.findViewById(R.id.iv_background_screen3);
        ImageView fav = view.findViewById(R.id.iv_fav_screen3);
        // nếu bitmap ảnh nền rỗng
        if(listAnimals.get(i).getPhotoBg() != null){
            background.setImageBitmap(listAnimals.get(i).getPhotoBg());
        }
        // nếu đã tích like
        if(listAnimals.get(i).isFav()){
            fav.setImageResource(R.drawable.ic_fav2);
        }
        // xét sự kiện khi kích vào like
        fav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String path = listAnimals.get(i).getPath() + "/photo/animal/"+ listAnimals.get(i).getName();
                if(listAnimals.get(i).isFav()){
                    fav.setImageResource(R.drawable.ic_fav1);
                    listAnimals.get(i).setFav(false);
                    saveData(path, false);
                    }else {
                    fav.setImageResource(R.drawable.ic_fav2);
                    listAnimals.get(i).setFav(true);
                    saveData(path, true);
                }
            }
        });
        tvName.setText(listAnimals.get(i).getName()
                .substring(0, listAnimals.get(i).getName().indexOf(".")));
        txtAnimal.setText(listAnimals.get(i).getContent());
        String pathPhone = listAnimals.get(i).getPath() +"/photo/animal/"+
                listAnimals.get(i).getName() +"_phone";
        SharedPreferences pref = mContext.getSharedPreferences(MainActivity.DATABASE, Context.MODE_PRIVATE);
        tvPhone.setText(pref.getString(pathPhone, ""));
        tvPhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog editPhone = new Dialog(mContext);
                editPhone.setContentView(R.layout.phone_number);

                EditText etPhone = editPhone.findViewById(R.id.et_number_dialog);
                ImageView ivAnimalDialog = editPhone.findViewById(R.id.iv_animal_dialog);
                Button btnSave = editPhone.findViewById(R.id.btn_save);
                Button btnDelete = editPhone.findViewById(R.id.btn_delete);

                ivAnimalDialog.setImageBitmap(listAnimals.get(i).getPhoto());
                etPhone.setText(tvPhone.getText().toString().trim());
                // khi ấn vào save
                btnSave.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        SharedPreferences pref = mContext.getSharedPreferences(MainActivity.DATABASE, Context.MODE_PRIVATE);
                        String phone = etPhone.getText().toString().trim();
                        String path = pref.getString(phone, null);
                        if(path != null) {
                            deleteData(path + "_phone");
                            deleteData(phone);
                        }
                            tvPhone.setText(phone);
                            // key (đường dẫn) VD: seas/photo/animal/animal.png_phone
                            // values VD : 096467513
                            saveData(listAnimals.get(i).getPath() + "/photo/animal/" +
                                            listAnimals.get(i).getName() + "_phone"
                                    , phone);
                            // key VD : 096467513
                            // values (đường dẫn) VD: seas/photo/animal/animal.png
                            saveData(phone,
                                    listAnimals.get(i).getPath() + "/photo/animal/" +
                                            listAnimals.get(i).getName());
                            editPhone.dismiss();
                    }
                });
                // khi ấn vào delete
                btnDelete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String numberPhone = etPhone.getText().toString().trim();
                        SharedPreferences pref = mContext.getSharedPreferences(MainActivity.DATABASE, Context.MODE_PRIVATE);
                        String pathPhone = pref.getString(numberPhone,null);
                        if(pathPhone != null) {
                            pathPhone += "_phone";
                            // key (phone) VD: 0988888888
                            deleteData(numberPhone);
                            // key (đường dẫn) VD: seas/photo/animal/animal.png_phone
                            deleteData(pathPhone);
                            tvPhone.setText("");
                            editPhone.dismiss();
                        }else {
                            Toast.makeText(mContext, "Can't delete data", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                editPhone.show();
            }
        });
        // thêm đối tượng vào
        viewGroup.addView(view);
        return view;
    }

    public void saveData(String key, String value){
        SharedPreferences pref = mContext.getSharedPreferences(MainActivity.DATABASE, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString(key, value);
        boolean check = editor.commit();
        if(!check){
            Toast.makeText(mContext, "Can't save data.....", Toast.LENGTH_SHORT).show();
        }
    }
    public void saveData(String key, boolean value){
        SharedPreferences pref = mContext.getSharedPreferences(MainActivity.DATABASE, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        // key (đường dẫn) VD: seas/photo/animal/animal.png
        // value : false/true
        editor.putBoolean(key, value);
        boolean check = editor.commit();
        if(!check){
            Toast.makeText(mContext, "Can't save data.....", Toast.LENGTH_SHORT).show();
        }
    }
    public void deleteData(String key){
        SharedPreferences pref = mContext.getSharedPreferences(MainActivity.DATABASE, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.remove(key);
        boolean check = editor.commit();
        if(!check){
            Toast.makeText(mContext, "Can't save data.....", Toast.LENGTH_SHORT).show();
        }
    }
    @Override
    public int getCount() {
        return listAnimals.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view.equals(object);
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }
}
