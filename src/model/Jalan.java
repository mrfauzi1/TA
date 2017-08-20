/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import controller.ThreadMobil;
import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import util.Posisi;
import util.Warna;

/**
 *
 * @author bukanjoker
 */
public class Jalan extends Thread {
    private Lampu lampu;
    private ArrayList<Mobil> listMobil;
    private double ratio;
    private boolean status;
    private Posisi posisi;
    private double wait;
    private long servis;
    private long start;

    public Jalan(Posisi posisi, boolean status)
    {
        this.start = System.currentTimeMillis();
        this.posisi = posisi;
        this.status = status;
        
        lampu = new Lampu();
        
        listMobil = new ArrayList<>();
    }
    
    @Override
    public void run()
    {        
        //input listmobil & jumlah        
        ThreadMobil masuk = new ThreadMobil(this, "masuk");
        masuk.setJumlahMobil(10);
        
        ThreadMobil keluar = new ThreadMobil(this, "keluar");
        
        //thread start
        masuk.start();
        keluar.start();
    }

    public double getWait() {
        if (listMobil.size() != 0) 
        {
            wait = System.currentTimeMillis() - listMobil.get(0).getWaktuDatang();
        }
        return wait;
    }

    public void setWait(long wait) {
        this.wait = wait;
    }

    public Lampu getLampu() {
        return lampu;
    }

    public void setLampu(Lampu lampu) {
        this.lampu = lampu;
    }

    public ArrayList<Mobil> getListMobil() {
        return listMobil;
    }

    public void setListMobil(ArrayList<Mobil> listMobil) {
        this.listMobil = listMobil;
    }

    public double getRatio() {
        return ratio;
    }

    public void setRatio(double ratio) {
        this.ratio = ratio;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
        if (status == true) 
        {
            lampu.setWarna(Warna.HIJAU);
        }
        else   
        {
            lampu.setWarna(Warna.merah);
        }
    }

    public Posisi getPosisi() {
        return posisi;
    }

    public void setPosisi(Posisi posisi) {
        this.posisi = posisi;
    }
}
