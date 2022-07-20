package com.example.duanmau.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.duanmau.database.DB_Helper;
import com.example.duanmau.model.Sach;

import java.util.ArrayList;
import java.util.List;

public class SachDAO {
    private SQLiteDatabase db;

    public SachDAO(Context context) {
        DB_Helper dbHelper = new DB_Helper(context);
        db = dbHelper.getWritableDatabase();
    }

    //insert
    public long insert(Sach obj) {
        ContentValues values = new ContentValues();
        values.put("tenSach", obj.tenSach);
        values.put("giaThue", obj.giaThue);
        values.put("trang", obj.trang);
        values.put("maLoai", obj.maLoai);
        return db.insert("Sach", null, values);
    }

    //update
    public int update(Sach obj) {
        ContentValues values = new ContentValues();
        values.put("tenSach", obj.tenSach);
        values.put("giaThue", obj.giaThue);
        values.put("trang", obj.trang);
        values.put("maLoai", obj.maLoai);
        return db.update("Sach", values, "maSach=?", new String[]{String.valueOf(obj.maSach)});
    }

    //delete
    public int delete(String id) {
        return db.delete("Sach", "maSach=?", new String[]{id});
    }

    // get tat ca data
    public List<Sach> getAll() {
        String sql = "SELECT * FROM Sach";
        return getData(sql);
    }


    //getData theo id
    public Sach getID(String id) {
        String sql = "SELECT * FROM Sach WHERE maSach=?";
        List<Sach> list = getData(sql, id);
        return list.get(0);
    }

    private List<Sach> getData(String sql, String... selectionArgs) {
        List<Sach> list = new ArrayList<>();
        Cursor c = db.rawQuery(sql, selectionArgs);
        while (c.moveToNext()) {
            Sach obj = new Sach();
            obj.maSach = Integer.parseInt(c.getString(c.getColumnIndex("maSach")));
            obj.tenSach = c.getString(c.getColumnIndex("tenSach"));
            obj.giaThue = Integer.parseInt(c.getString(c.getColumnIndex("giaThue")));
            obj.trang = Integer.parseInt(c.getString(c.getColumnIndex("trang")));
            obj.maLoai = Integer.parseInt(c.getString(c.getColumnIndex("maLoai")));
            list.add(obj);
        }
        return list;
    }
    public int checkLoaiSach(){
        int check = 1;
        String getLS = "SELECT * FROM LoaiSach";
        Cursor cursor = db.rawQuery(getLS,null);
        if (cursor.getCount()!=0){
            check = -1;
        }
        return check;
    }
    public List<Sach> getTrang(String id) {
        String sql = "SELECT * FROM Sach WHERE trang=?";
        List<Sach> list = getData(sql, id);
        return list;
    }
}
