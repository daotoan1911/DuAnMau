package com.example.duanmau.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.duanmau.R;
import com.example.duanmau.fragment.ThanhVienFragment;
import com.example.duanmau.model.ThanhVien;

import java.util.ArrayList;

public class ThanhVienAdapter extends ArrayAdapter<ThanhVien> {
    private Context context;
    ThanhVienFragment fragment;
    private ArrayList<ThanhVien> lists;
    TextView tvMaTV,tvTenTV,tvNamSinh;
    ImageView imgXoaTV;

    public ThanhVienAdapter(@NonNull Context context, ThanhVienFragment fragment, ArrayList<ThanhVien> lists) {
        super(context, 0,lists);
        this.context = context;
        this.lists = lists;
        this.fragment = fragment;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v = convertView;
        if (v == null){
            LayoutInflater inflater = (LayoutInflater)
                    context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v=inflater.inflate(R.layout.thanhvien_item,null);
        }
        final  ThanhVien item = lists.get(position);
        if (item != null){

            tvMaTV = v.findViewById(R.id.tvMaTV);
            tvMaTV.setText("Mã thành viên: "+item.maTV);
            tvTenTV = v.findViewById(R.id.tvHoTen);
            tvTenTV.setText("Tên thành viên: "+item.hoTen);
            tvNamSinh = v.findViewById(R.id.tvNamSinh);
            tvNamSinh.setText("Năm sinh: "+item.namSinh);

            imgXoaTV = v.findViewById(R.id.imgXoaTV);
        }
//        if (position % 2==0){
//            tvMaTV.setTextColor(Color.GREEN);
//            tvTenTV.setTextColor(Color.GREEN);
//            tvNamSinh.setTextColor(Color.GREEN);
//        }else {
//            tvMaTV.setTextColor(Color.RED);
//            tvTenTV.setTextColor(Color.RED);
//            tvNamSinh.setTextColor(Color.RED);
//        }
        imgXoaTV.setOnClickListener(v1 -> {
            fragment.xoa(String.valueOf(item.maTV));
        });
        return v;
    }
}
