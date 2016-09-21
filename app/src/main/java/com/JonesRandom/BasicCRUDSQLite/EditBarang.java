package com.JonesRandom.BasicCRUDSQLite;

import android.content.ContentValues;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class EditBarang extends AppCompatActivity {

    DBHandler handler;
    EditText NamaBarang, JenisBarang, Keterangan;
    Button Simpan;
    long id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_barang);

        handler = new DBHandler(this);

        id = getIntent().getLongExtra(DBHandler.row_id, 0);

        NamaBarang = (EditText) findViewById(R.id.editNama);
        JenisBarang = (EditText) findViewById(R.id.editJenis);
        Keterangan = (EditText) findViewById(R.id.editKeterangan);

        getData();

        Simpan = (Button) findViewById(R.id.btnSimpan);
        Simpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String namaBarang = NamaBarang.getText().toString().trim();
                String jenisBarang = JenisBarang.getText().toString().trim();
                String keterangan = Keterangan.getText().toString().trim();

                if (namaBarang.equals("") || jenisBarang.equals("") || keterangan.equals("")) {
                    Toast.makeText(EditBarang.this, "Pastikan Semua Form Data Terisi", Toast.LENGTH_LONG).show();
                } else {
                    ContentValues values = new ContentValues();
                    values.put(DBHandler.row_nama_barang,namaBarang);
                    values.put(DBHandler.row_jenis_barang, jenisBarang);
                    values.put(DBHandler.row_keterangan, keterangan);
                    handler.editData(values,id);
                    finish();
                }
            }
        });

    }

    public void getData() {
        Cursor cur = handler.satuData(id);
        if (cur.moveToFirst()) {
            String namaBarang = cur.getString(cur.getColumnIndex(DBHandler.row_nama_barang));
            String jenisBarang = cur.getString(cur.getColumnIndex(DBHandler.row_jenis_barang));
            String keterangan = cur.getString(cur.getColumnIndex(DBHandler.row_keterangan));
            NamaBarang.setText(namaBarang);
            JenisBarang.setText(jenisBarang);
            Keterangan.setText(keterangan);
        }
    }
}
