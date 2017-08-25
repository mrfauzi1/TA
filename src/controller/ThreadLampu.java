/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.util.logging.Level;
import java.util.logging.Logger;
import model.Jalan;
import util.Warna;

/**
 *
 * @author bukanjoker
 */
public class ThreadLampu extends Thread {
    private Jalan[] jalan;
    private String kondisi;
    private double ratioMax;
    private int current = 0;
    private int nextCurr = 0;

    public ThreadLampu(Jalan[] jalan, String kondisi) {
        this.jalan = jalan;
        this.kondisi = kondisi;
    }
    
    public double HRRN(double wait, long servis)
    {
        double ratio = (wait+servis)/servis;
        return ratio;
    }
    
    public void changeLampu()
    {
        jalan[current].setStatus(true);
        
        if (kondisi == "dinamis") 
        {
            for (int i = 0; i < jalan.length; i++) {
                if (i != current) 
                {
                    jalan[i].setStatus(false);
                    //hrrn calculation
                    jalan[i].setRatio(HRRN(jalan[i].getWait(), jalan[i].getLampu().getDurasi()));
                    if (jalan[i].getRatio() > ratioMax) 
                    {
                        ratioMax = jalan[i].getRatio();
                        nextCurr = i;
                    }
                }
            }
        }
        else
        {
            for (int i = 0; i < jalan.length; i++) {
                if (i != current) 
                {
                    jalan[i].setStatus(false);
                }
            }
            if (nextCurr < 3)
            {
                nextCurr++;
            }
            else
            {
                nextCurr = 0;
            }
        }
        
        System.out.println("["+jalan[current].getPosisi()+"] "+jalan[current].getLampu().getWarna());
        long durasi = jalan[current].getLampu().getDurasi();
        current = nextCurr;
        try {
            sleep(durasi);
        } catch (InterruptedException ex) {
            Logger.getLogger(ThreadLampu.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    public void run()
    {
        
        
        while (true)
        {
            changeLampu();
//            tesThread();
        }
    }
    
    public void tesThread()
    {
        try {
            System.out.println("thread jalan1");
            sleep(2000);
        } catch (InterruptedException ex) {
            Logger.getLogger(ThreadLampu.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
