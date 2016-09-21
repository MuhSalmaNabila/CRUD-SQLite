package com.JonesRandom.BasicCRUDSQLite;

import android.content.ContentValues;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    DBHandler handler;
    EditText NamaBarang, JenisBarang, Keterangan;
    Button TambahData, LihatData;
    CheckBox keepData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        handler = new DBHandler(this);

        NamaBarang = (EditText) findViewById(R.id.editNama);
        JenisBarang = (EditText) findViewById(R.id.editJenis);
        Keterangan = (EditText) findViewById(R.id.editKeterangan);

        TambahData = (Button) findViewById(R.id.btnTambah);
        LihatData = (Button) findViewById(R.id.btnLihat);

        keepData = (CheckBox) findViewById(R.id.keepData);

        TambahData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String namaBarang = NamaBarang.getText().toString().trim();
                String jenisBarang = JenisBarang.getText().toString().trim();
                String keterangan = Keterangan.getText().toString().trim();

                if (namaBarang.equals("") || jenisBarang.equals("") || keterangan.equals("")) {
                    Toast.makeText(MainActivity.this, "Pastikan Semua Form Data Terisi", Toast.LENGTH_LONG).show();
                } else {
                    ContentValues values = new ContentValues();
                    values.put(DBHandler.row_nama_barang, namaBarang);
                    values.put(DBHandler.row_jenis_barang, jenisBarang);
                    values.put(DBHandler.row_keterangan, keterangan);
                    handler.tambahData(values);
                    if (!keepData.isChecked()) {
                        NamaBarang.setText("");
                        JenisBarang.setText("");
                        Keterangan.setText("");
                        NamaBarang.requestFocus();
                    }
                    Toast.makeText(MainActivity.this, "Berhasil Input Data", Toast.LENGTH_LONG).show();
                }
            }
        });

        LihatData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, Barang.class));
            }
        });

    }
}
