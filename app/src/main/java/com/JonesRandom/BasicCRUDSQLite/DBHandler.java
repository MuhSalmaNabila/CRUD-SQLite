package com.JonesRandom.BasicCRUDSQLite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by JonesRandom on 19/09/2016.
 */


public class DBHandler extends SQLiteOpenHelper {

    public static final String nama_database = "db_barang";
    public static final String nama_tabel = "tabel_barang";

    public static final String row_id = "_id";
    public static final String row_nama_barang = "Nama_barang";
    public static final String row_jenis_barang = "Jenis_barang";
    public static final String row_keterangan = "Keterangan";

    private SQLiteDatabase db;

    public DBHandler(Context context) {
        super(context, nama_database, null, 2);
        db = getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + nama_tabel + "(" + row_id + " INTEGER PRIMARY KEY AUTOINCREMENT," + row_nama_barang + " TEXT," + row_jenis_barang + " TEXT," + row_keterangan + " TEXT)";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + nama_tabel);
    }

    /// MENGAMBIL SEMUA DATA SQLITE
    public Cursor semuaData() {
        Cursor cur = db.rawQuery("SELECT * FROM " + nama_tabel, null);
        return cur;
    }
    
    /// MENGAMBIL 1 DATA BERDASARKAN ID
    public Cursor satuData(long id) {
        Cursor cur = db.rawQuery("SELECT * FROM " + nama_tabel + " WHERE " + row_id + "=" + id, null);
        return cur;
    }

    /// MENAMBAH DATA
    public void tambahData(ContentValues values) {
        db.insert(nama_tabel, null, values);
    }

    /// UPDATE DATA BERDASARKAN ID
    public void editData(ContentValues values, long id) {
        db.update(nama_tabel, values, row_id + "=" + id, null);
    }

    /// HAPUS DATA BERDASARKAN ID
    public void hapusData(long id) {
        db.delete(nama_tabel, row_id + "=" + id, null);
    }
}
