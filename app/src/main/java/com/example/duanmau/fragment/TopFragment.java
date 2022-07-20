package com.example.duanmau.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.fragment.app.Fragment;


import com.example.duanmau.R;
import com.example.duanmau.adapter.TopAdapter;
import com.example.duanmau.dao.ThongKeDAO;
import com.example.duanmau.model.Top;

import java.util.ArrayList;

public class TopFragment extends Fragment {
    ListView listView;
    ArrayList<Top> list;
    TopAdapter adapter;
    ThongKeDAO thongKeDAO;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_top, container, false);
        listView = v.findViewById(R.id.lvTop);
        thongKeDAO = new ThongKeDAO(getActivity());
        capNhatLv();
        return v;
    }
    void capNhatLv(){
        list = (ArrayList)thongKeDAO.getTop();
        adapter = new TopAdapter(getActivity(),this,list);
        listView.setAdapter(adapter);
    }
}