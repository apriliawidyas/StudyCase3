package com.example.aprilia.april_1202160098_si4002_pab_modul3;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class adapter extends RecyclerView.Adapter<adapter.ViewHolder> {

    private ArrayList<user>daftarUser;
    private Context mContext;

    public adapter (ArrayList<user> daftarUser, Context mContext){
        this.daftarUser = daftarUser;
        this.mContext = mContext;
    }

    public adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i){
        return new ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.activity_list, viewGroup, false));
    }

    public void onBindViewHolder (adapter.ViewHolder viewHolder, int urutan ){
        user currentUser = daftarUser.get(urutan);
        viewHolder.bindTo(currentUser);
    }
    public int getItemCount() {
        return daftarUser.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private TextView nama, job;
        private ImageView image;
        private int avatarCode;

        public ViewHolder(View itemView){
            super(itemView);

            nama = itemView.findViewById(R.id.tv_nama);
            job = itemView.findViewById(R.id.tv_pekerjaan);
            image = itemView.findViewById(R.id.imageView);

            itemView.setOnClickListener(this);
        }

        void bindTo(user currentUser){
            nama.setText(currentUser.getName());
            job.setText(currentUser.getJob());

            avatarCode = currentUser.getAvatar();
            switch (currentUser.getAvatar()){
                case 1:
                    image.setImageResource(R.drawable.l);
                    break;
                case 2:
                default:
                    image.setImageResource(R.drawable.p);
                    break;
            }
        }

        public void onClick(View view){
            //untuk dapat membuat activity detail dengan beberapa extras
            Intent toDetailActivity = new Intent (view.getContext(), Detail.class);
            toDetailActivity.putExtra("nama", nama.getText().toString());
            toDetailActivity.putExtra("gender",avatarCode);
            toDetailActivity.putExtra("pekerjaan", job.getText().toString());
            view.getContext().startActivity(toDetailActivity);
        }
    }
}
