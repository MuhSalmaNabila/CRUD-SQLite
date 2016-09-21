package com.JonesRandom.BasicCRUDSQLite;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import org.w3c.dom.Text;

public class Barang extends AppCompatActivity implements AdapterView.OnItemClickListener {

    ListView ls;
    DBHandler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.barang);
        handler = new DBHandler(this);
        ls = (ListView) findViewById(R.id.listBarang);
        ls.setOnItemClickListener(this);
        setupListView();

    }

    public void setupListView() {
        Cursor cursor = handler.semuaData();
        CustomCursorAdapter customCursorAdapter = new CustomCursorAdapter(this, cursor, 1);
        ls.setAdapter(customCursorAdapter);
        ls.setDividerHeight(0);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        TextView getid = (TextView) view.findViewById(R.id.listID);
        final long id = Long.parseLong(getid.getText().toString());
        Cursor cur = handler.satuData(id);
        cur.moveToFirst();

        String ket = cur.getString(cur.getColumnIndex(DBHandler.row_keterangan));
        final String namaBarang = cur.getString(cur.getColumnIndex(DBHandler.row_nama_barang));
        final AlertDialog.Builder builder = new AlertDialog.Builder(Barang.this);
        builder.setTitle("Daftar Barang");
        builder.setMessage(ket);
        builder.setPositiveButton("HAPUS", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogHapusItem(id, namaBarang);
            }
        });
        builder.setNegativeButton("EDIT", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Intent editBarang = new Intent(Barang.this, EditBarang.class);
                editBarang.putExtra(DBHandler.row_id, id);
                startActivity(editBarang);
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    public void dialogHapusItem(final long id, String nama) {
        AlertDialog.Builder builder1 = new AlertDialog.Builder(Barang.this);
        builder1.setTitle("Hapus Data " + nama);
        builder1.setMessage("Apakah Anda Yakin Ingin Menghapus Data " + nama + " ?");
        builder1.setPositiveButton("HAPUS", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                handler.hapusData(id);
                setupListView();
            }
        });
        builder1.setNegativeButton("BATAL", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                ///CANCEL
            }
        });
        AlertDialog dialog = builder1.create();
        dialog.show();
    }

    @Override
    protected void onResume() {
        super.onResume();
        setupListView();
    }
}
