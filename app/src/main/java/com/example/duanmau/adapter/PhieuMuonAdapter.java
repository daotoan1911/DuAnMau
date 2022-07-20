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
import com.example.duanmau.dao.SachDAO;
import com.example.duanmau.dao.ThanhVienDAO;
import com.example.duanmau.fragment.PhieuMuonFragment;
import com.example.duanmau.model.PhieuMuon;
import com.example.duanmau.model.Sach;
import com.example.duanmau.model.ThanhVien;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class PhieuMuonAdapter extends ArrayAdapter<PhieuMuon> {
    private Context context;
    PhieuMuonFragment fragment;
    private ArrayList<PhieuMuon> lists;
    TextView tvMaPM,tvHoTen,tvTenSach,tvTienThue,tvTraSach,tvNgay;
    ImageView imgXoaPM;
    SachDAO sachDAO;
    ThanhVienDAO thanhVienDAO;
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    public PhieuMuonAdapter(@NonNull Context context, PhieuMuonFragment fragment, ArrayList<PhieuMuon> lists) {
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
            v=inflater.inflate(R.layout.phieumuon_item,null);
        }
        final  PhieuMuon item = lists.get(position);
        if (item != null){

            tvMaPM = v.findViewById(R.id.tvMaPM);
            tvMaPM.setText("Mã phiếu: "+item.maPM);
            sachDAO  = new SachDAO(context);
            Sach sach = sachDAO.getID(String.valueOf(item.maSach));
            tvTenSach = v.findViewById(R.id.tvTenSach);
            tvTenSach.setText("Tên sách: "+sach.tenSach);
            thanhVienDAO  = new ThanhVienDAO(context);
            ThanhVien thanhVien = thanhVienDAO.getID(String.valueOf(item.maTV));
            tvHoTen = v.findViewById(R.id.tvTenPM);
            tvHoTen.setText("Thành viên: "+thanhVien.hoTen);
            tvTienThue = v.findViewById(R.id.tvTienThue);
            tvTienThue.setText("Tiền thuê: "+item.tienThue);
            tvNgay = v.findViewById(R.id.tvNgay);
            tvNgay.setText("Ngày thuê: "+item.ngay);
            //+sdf.format(item.ngay)
            tvTraSach = v.findViewById(R.id.tvTraSach);
            if (item.traSach==1){
                tvTraSach.setText("Đã trả sách");
//                tvTraSach.setTextColor(Color.BLUE);
            }else {
//                tvTraSach.setTextColor(Color.RED);
                tvTraSach.setText("Chưa trả sách");
            }
            imgXoaPM = v.findViewById(R.id.imgXoaPM);
        }
        imgXoaPM.setOnClickListener(v1 -> {
            fragment.xoa(String.valueOf(item.maPM));
        });
        return v;
    }
}

