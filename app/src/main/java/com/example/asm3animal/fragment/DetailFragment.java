package com.example.asm3animal.fragment;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.asm3animal.model.Animal;
import com.example.asm3animal.MainActivity;
import com.example.asm3animal.R;
import com.example.asm3animal.adapter.DetailAdapter;

import java.util.List;

public class DetailFragment extends Fragment {
    List<Animal> listAnimals;
    Animal animal;
    Context mContext;
    public DetailFragment(List<Animal> listAnimals, Animal animal) {
        this.listAnimals = listAnimals;
        this.animal = animal;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.mContext = context;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_detail, container, false);
        initViews(view);
        return view;
    }
    public void initViews(View view){
        ImageView btnBack = view.findViewById(R.id.iv_btn_list);
        btnBack.setImageResource(R.drawable.ic_back);
        // xét sự kiện ấn vào nút back trở về fragment menu
        btnBack.setOnClickListener(v12 -> ((MainActivity)mContext).gotoMenu(listAnimals));

        ViewPager viewPager = view.findViewById(R.id.info_animal);
        // đổ dự liệu vào viewpager
        DetailAdapter detailAdapter = new DetailAdapter(listAnimals,mContext);
        viewPager.setAdapter(detailAdapter);
        // hiển thị thông tin tương ứng với animal đã ấn vào
        viewPager.setCurrentItem(listAnimals.indexOf(animal), true);
    }
}