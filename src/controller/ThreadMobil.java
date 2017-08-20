/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import static java.lang.Thread.sleep;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Jalan;
import model.Mobil;

/**
 *
 * @author bukanjoker
 */
public class ThreadMobil extends Thread
{
    private Jalan jalan;
    private String kondisi;
    private int jml;
    private long arrival;

    public ThreadMobil(Jalan jalan, String kondisi) {
        this.jalan = jalan;
        this.kondisi = kondisi;
    }
    
    public void setJumlahMobil(int jml)
    {
        this.jml = jml;
    }
    
    public synchronized void add()
    {
        while (true)
        {
            if (jalan.getListMobil().size() < jml) 
            {
                Mobil m = new Mobil("default", "default");
                long interval = m.getIntervalDatang();

                try 
                {
                    sleep(interval);
                } 
                catch (InterruptedException ex) {
                    Logger.getLogger(Jalan.class.getName()).log(Level.SEVERE, null, ex);
                }

                arrival = arrival + System.currentTimeMillis();
                m.setWaktuDatang(arrival);
                jalan.getListMobil().add(m);
                System.out.println("["+jalan.getPosisi()+"} mobil masuk");
                notifyAll();
            }
        }
    }
    
    public synchronized void remove()
    {
        while (true)
        {
            //if tidak kosong dan ketika lampu hijau
            if (!jalan.getListMobil().isEmpty()) 
            {
                long interval = jalan.getListMobil().get(0).getIntervalKeluar();
                
                try 
                {
                    sleep(interval);
                }
                catch (InterruptedException ex) {
                    Logger.getLogger(ThreadMobil.class.getName()).log(Level.SEVERE, null, ex);
                }
                jalan.getListMobil().remove(0);
                System.out.println("["+jalan.getPosisi()+"} mobil keluar");
                notifyAll();
            }
        }
    }
    
    public void aktivitas()
    {
        if (kondisi == "masuk") 
        {
            add();
        }
        else if (kondisi == "keluar")
        {
            remove();
        }
    }
    
    @Override
    public void run()
    {
        aktivitas();
    }
}
