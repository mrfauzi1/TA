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
    private boolean val = false;
    private long arrival;

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
    
    public synchronized void add(int jml)
    {
//        while (val == false)
//        {
            if (listMobil.size() < jml) 
            {
                Mobil m = new Mobil("default", "default");
                long interval = m.getIntervalDatang();

                try 
                {
                    sleep(interval);
                    arrival = arrival + System.currentTimeMillis();
                    m.setWaktuDatang(arrival);
                    listMobil.add(m);
                    System.out.println("["+posisi+"]mobil masuk");
//                    wait(1000);
                } 
                catch (InterruptedException ex) {
                    Logger.getLogger(Jalan.class.getName()).log(Level.SEVERE, null, ex);
                }
//                val = true;
                notifyAll();
            }
            else
            {
                try {
                    wait(1000);
//                    System.out.println("["+posisi+"]no cars");
                } catch (InterruptedException ex) {
                    Logger.getLogger(Jalan.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
//        }
    }
    
    public synchronized void remove()
    {
//        while (val == true)
//        {
            if (!listMobil.isEmpty() && lampu.getWarna() == Warna.HIJAU)
            {
                long interval = listMobil.get(0).getIntervalKeluar();
                
                try 
                {
                    sleep(interval);
                    listMobil.remove(0);
                    System.out.println("["+posisi+"]mobil keluar");
//                    wait(1000);
                }
                catch (InterruptedException ex) {
                    Logger.getLogger(Jalan.class.getName()).log(Level.SEVERE, null, ex);
                }
//                val = false;
                notifyAll();
            }
            else
            {
                try {
                    wait(1000);
//                    System.out.println("["+posisi+"]no cars");
                } catch (InterruptedException ex) {
                    Logger.getLogger(Jalan.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
//        }
    }

    public double getWait() {
        if (!listMobil.isEmpty()) 
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
