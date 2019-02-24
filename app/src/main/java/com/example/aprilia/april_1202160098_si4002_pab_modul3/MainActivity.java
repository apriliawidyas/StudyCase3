package com.example.aprilia.april_1202160098_si4002_pab_modul3;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;

public class MainActivity extends AppCompatActivity {

    Dialog dialog;
    private RecyclerView mRecyclerView;
    private ArrayList<user> daftarUser;
    private adapter adapter; //adapter untuk user
    private FloatingActionButton fab;
    private Spinner gender;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        daftarUser = new ArrayList<>();

        mRecyclerView = findViewById(R.id.idRecyclerView);

        //grid ini buat column nya yang ditampilin atau gridnya biar bisa lanscape atau portrait muncul berapa tampilan cek integer
        int gridColumnCount = getResources().getInteger(R.integer.grid_column);
        mRecyclerView.setLayoutManager(new GridLayoutManager(this, gridColumnCount));

        daftarUser = new ArrayList<>();
        //melakukan pengecekan apakah ada data pada array
        if (savedInstanceState!=null){
            daftarUser.clear();
            for (int i=0; i<savedInstanceState.getStringArrayList("nama").size();i++){
                daftarUser.add(new user(savedInstanceState.getStringArrayList("nama").get(i),
                    savedInstanceState.getStringArrayList("job").get(i),
                    savedInstanceState.getIntegerArrayList("gender").get(i)));
            }
        } else {
            setRecyclerViewData();
        }
        adapter = new adapter(daftarUser, this);
        mRecyclerView.setAdapter(adapter);

        //touchelper bantu buat swipe buat hapus
        ItemTouchHelper help= new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT | ItemTouchHelper.DOWN | ItemTouchHelper.UP,
                ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT){
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target){
                int from = viewHolder.getAdapterPosition();
                int to = target.getAdapterPosition();
                Collections.swap(daftarUser, from, to);
                adapter.notifyItemMoved(from,to);

                return true;
            }
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction){
                daftarUser.remove(viewHolder.getAdapterPosition());
                adapter.notifyItemRemoved(viewHolder.getAdapterPosition());
            }
        });
        //masukan helper pada recyclerview
        help.attachToRecyclerView(mRecyclerView);
    }

    private void setRecyclerViewData(){
        daftarUser.clear();
        //Dummy data
        daftarUser.add(new user ("Astrella", "Data Analyst", 2));
        daftarUser.add(new user("Jeconia", "Back End Developer", 1));
    }

    //membuat dialog tambah user
    public void add(View view){
        dialog = new Dialog(this);
        dialog.setContentView(R.layout.activity_add_dialog);
        final TextView mNama,mPekerjaan;
        final Spinner mGender;
        mNama = dialog.findViewById(R.id.eTnama);
        mPekerjaan = dialog.findViewById(R.id.editText_pekerjaan);

        TextView add=dialog.findViewById(R.id.tv_add);
        TextView batal = dialog.findViewById(R.id.tv_cancel);

        mGender = dialog.findViewById(R.id.spinner_pilih);

        String[]list={"Male","Female"};

        ArrayAdapter<String>adapterX = new ArrayAdapter(dialog.getContext(),android.R.layout.simple_spinner_item,list);
        mGender.setAdapter(adapterX);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                daftarUser.add(new user(mNama.getText().toString(),mPekerjaan.getText().toString(),mGender.getSelectedItemPosition()+1));
                adapter.notifyDataSetChanged();
                dialog.dismiss();
            }
        });
        batal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    protected void onSaveInstanceState(Bundle outState) {
        //melakukan convert arrayList untuk dreakdown menu lebih banyak lagi
        ArrayList<String>tempListNama = new ArrayList<>();
        ArrayList<String>tempListPekerjaan = new ArrayList<>();
        ArrayList<Integer>tempListGender = new ArrayList<>();
        for (int i = 0; i <daftarUser.size() ; i++) {
            tempListNama.add(daftarUser.get(i).getName());
            tempListPekerjaan.add(daftarUser.get(i).getJob());
            tempListGender.add(daftarUser.get(i).getAvatar());
        }
        //simpan pada bagian outstate
        outState.putStringArrayList("nama",tempListNama);
        outState.putStringArrayList("job",tempListPekerjaan);
        outState.putIntegerArrayList("gender",tempListGender);
        super.onSaveInstanceState(outState);

    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


}
