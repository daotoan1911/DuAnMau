package com.example.duanmau.fragment;

import android.os.Bundle;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.duanmau.R;
import com.example.duanmau.dao.ThuThuDAO;

import com.example.duanmau.model.ThuThu;
import com.google.android.material.textfield.TextInputEditText;


public class ThemMoiFragment extends Fragment {
    ThuThuDAO dao;
    TextInputEditText edUserNew,edHoTenNew,edUPassNew,edReUPassNew;
    Button btnLuuUN,btnHuyUN;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_them_moi, container, false);
        edUserNew = v.findViewById(R.id.edUserNew);
        edHoTenNew = v.findViewById(R.id.edHoTenNew);
        edUPassNew = v.findViewById(R.id.edUPassNew);
        edReUPassNew = v.findViewById(R.id.edReUPassNew);
        btnLuuUN = v.findViewById(R.id.btnLuuUN);
        btnHuyUN = v.findViewById(R.id.btnHuyUN);
        dao = new ThuThuDAO(getActivity());

        btnHuyUN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edUserNew.setText("");
                edHoTenNew.setText("");
                edUPassNew.setText("");
                edReUPassNew.setText("");
            }
        });
        btnLuuUN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ThuThu thuThu = new ThuThu();
                thuThu.maTT =edUserNew.getText().toString();
                thuThu.hoTen = edHoTenNew.getText().toString();
                thuThu.matKhau = edUPassNew.getText().toString();
                if (validate()>0){
                    if (dao.insert(thuThu)>0){
                        Toast.makeText(getContext(),"Lưu thành công",Toast.LENGTH_SHORT).show();
                        edUserNew.setText("");
                        edHoTenNew.setText("");
                        edUPassNew.setText("");
                        edReUPassNew.setText("");
                    }
                    else{
//                        edUserNew.setInputType(InputType.TYPE_CLASS_TEXT|InputType.TYPE_TEXT_FLAG_CAP_SENTENCES);
                        Toast.makeText(getContext(),"Lưu thất bại",Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        return v;
    }
    private int validate(){
        int check = 1;
        if (edUserNew.getText().length()==0
                ||edHoTenNew.getText().length()==0||edUPassNew.getText().length()==0
                ||edReUPassNew.getText().length()==0)
        {
            Toast.makeText(getContext(),"Bạn phải nhập đầy đủ thông tin",Toast.LENGTH_SHORT).show();
            check = -1;
        }
        else if (edUserNew.getText().length()<5 || edUserNew.getText().length()>15){
            Toast.makeText(getContext(), "User name tối thiểu 5 tối đa 15", Toast.LENGTH_SHORT).show();
            check = -1;
        }
        else
        {
            String pass= edUPassNew.getText().toString();
            String rePass = edReUPassNew.getText().toString();

            if (!pass.equals(rePass)){
                Toast.makeText(getContext(),"Mật khẩu không trùng khớp",Toast.LENGTH_SHORT).show();
            }
        }
        return check;
    }
}