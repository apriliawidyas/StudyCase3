package com.example.aprilia.april_1202160098_si4002_pab_modul3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class Detail extends AppCompatActivity {

    //inisialisasi
    private TextView nama_detail, jobDetail;
    private ImageView pictDetail;
    private int avatarCode;
    private String mNama, mJob;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        nama_detail = findViewById(R.id.detail_nama);
        jobDetail = findViewById(R.id.detail_pekerjaan);
        pictDetail = findViewById(R.id.imagedetail);

        mNama = getIntent().getStringExtra("nama");
        mJob = getIntent().getStringExtra("pekerjaan");
        avatarCode = getIntent().getIntExtra("gender", 2);

        nama_detail.setText(mNama);
        jobDetail.setText(mJob);
        switch (avatarCode){
            case 1 :
                pictDetail.setImageResource(R.drawable.l); //untuk laki-laki
                break;
            case 2 :
                default :
                    pictDetail.setImageResource(R.drawable.p); //untuk perempuan
                    break;

        }
    }
}
