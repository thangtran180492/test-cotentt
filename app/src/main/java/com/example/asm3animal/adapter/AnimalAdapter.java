package com.example.asm3animal.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.asm3animal.model.Animal;
import com.example.asm3animal.MainActivity;
import com.example.asm3animal.R;

import java.util.List;

public class AnimalAdapter extends RecyclerView.Adapter<AnimalAdapter.ViewHolder> {
    Context context;
    List<Animal> listAnimal;
    public AnimalAdapter(List<Animal> listAnimal, Context context){
        this.listAnimal = listAnimal;
        this.context = context;
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvNameAnimal;
        ImageView ivAnimal, ivFav;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvNameAnimal = itemView.findViewById(R.id.tv_animal_topic);
            ivAnimal = itemView.findViewById(R.id.iv_animal_topic);
            // tạo hiệu ứng alpha cho icon
            ivAnimal.startAnimation(AnimationUtils.loadAnimation(context, R.anim.press_alpha));
            ivFav = itemView.findViewById(R.id.iv_fav_menu);
            // tạo sự kiện khi kích vào item
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    itemView.setAnimation(AnimationUtils.loadAnimation(context, R.anim.press_scale));
                    // show show ra thông tin animal
                    ((MainActivity) context).showDetail(listAnimal, listAnimal.get(listAnimal.indexOf((Animal) itemView.getTag())));
                }
            });
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_animal, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        // định nghĩa đối tượng tag
        viewHolder.itemView.setTag(listAnimal.get(i));
        // nếu Bimap rỗng
        if(listAnimal.get(i).getPhoto() != null){
            viewHolder.ivAnimal.setImageBitmap(listAnimal.get(i).getPhoto());
        }
        viewHolder.tvNameAnimal.setText(listAnimal.get(i).getName()
                .substring(0, listAnimal.get(i).getName().indexOf(".")));
        // hiện thị like
        if(listAnimal.get(i).isFav()){
            viewHolder.ivFav.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public int getItemCount() {
        return listAnimal.size();
    }
}
