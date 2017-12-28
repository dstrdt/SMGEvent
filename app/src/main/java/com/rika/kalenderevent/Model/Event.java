package com.rika.kalenderevent.Model;

/**
 * Created by User on 27/12/2017.
 */


public class Event {
    String nama,keterangan,detail,gambar;
    public Event(){

    }

    public Event(String nama, String keterangan, String detail, String gambar) {
        this.nama = nama;
        this.keterangan = keterangan;
        this.detail = detail;
        this.gambar = gambar;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getGambar() {
        return gambar;
    }

    public void setGambar(String gambar) {
        this.gambar = gambar;
    }
}