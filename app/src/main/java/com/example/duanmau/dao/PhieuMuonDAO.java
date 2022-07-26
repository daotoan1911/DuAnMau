package com.example.duanmau.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.duanmau.database.DB_Helper;
import com.example.duanmau.model.PhieuMuon;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class PhieuMuonDAO {
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
    private SQLiteDatabase db;

    public PhieuMuonDAO(Context context) {
        DB_Helper dbHelper = new DB_Helper(context);
        db = dbHelper.getWritableDatabase();
    }

    //insert
    public long insert(PhieuMuon obj){
        ContentValues values = new ContentValues();
        values.put("maTT",obj.maTT);
        values.put("maTV",obj.maTV);
        values.put("maSach",obj.maSach);
        values.put("tienThue",obj.tienThue);
        values.put("traSach",obj.traSach);
        values.put("ngay", String.valueOf(obj.ngay));
        return db.insert("PhieuMuon",null,values);
    }

    //update
    public int update(PhieuMuon obj){
        ContentValues values = new ContentValues();
        values.put("maTT",obj.maTT);
        values.put("maTV",obj.maTV);
        values.put("maSach",obj.maSach);
        values.put("tienThue",obj.tienThue);
        values.put("traSach",obj.traSach);
        values.put("ngay", String.valueOf(obj.ngay));
        return db.update("PhieuMuon",values,"maPM=?",new String[]{String.valueOf(obj.maPM)});
    }

    //delete
    public int delete(String id){
        return db.delete("PhieuMuon","maPM=?",new String[]{id});
    }

    // get tat ca data
    public List<PhieuMuon> getAll(){
        String sql = "SELECT * FROM PhieuMuon";
        return getData(sql);
    }



    //getData theo id
    public  PhieuMuon getID(String id){
        String sql = "SELECT * FROM PhieuMuon WHERE maPM=?";
        List<PhieuMuon> phieuMuonList = getData(sql,id);
        return phieuMuonList.get(0);
    }
    private List<PhieuMuon> getData(String sql,String...selectionArgs) {
        List<PhieuMuon> phieuMuonList = new ArrayList<>();
        Cursor c = db.rawQuery(sql,selectionArgs);
        while (c.moveToNext()){
            PhieuMuon obj = new PhieuMuon();
            obj.maPM = Integer.parseInt(c.getString(c.getColumnIndex("maPM")));
            obj.maTT = c.getString(c.getColumnIndex("maTT"));
            obj.maTV = Integer.parseInt(c.getString(c.getColumnIndex("maTV")));
            obj.maSach = Integer.parseInt(c.getString(c.getColumnIndex("maSach")));
            obj.tienThue = Integer.parseInt(c.getString(c.getColumnIndex("tienThue")));
            obj.traSach = Integer.parseInt(c.getString(c.getColumnIndex("traSach")));
            obj.ngay = java.sql.Date.valueOf(c.getString(c.getColumnIndex("ngay")));
            phieuMuonList.add(obj);
        }
        return phieuMuonList;
    }
}
