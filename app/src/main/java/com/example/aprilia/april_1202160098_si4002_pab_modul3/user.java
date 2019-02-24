package com.example.aprilia.april_1202160098_si4002_pab_modul3;

public class user {

    private String nama;
    private String job;
    private final int avatar;

    public user (String nama, String job, int avatar){
        this.nama   = nama;
        this.job    = job;
        this.avatar = avatar;
    }

    public String getName(){
        return nama;
    }

    public String getJob(){
        return job;
    }

    public int getAvatar(){
        return avatar;
    }
}
