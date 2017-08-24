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
public class ThreadRemoveMobil extends Thread {
    private Jalan jalan;

    public ThreadRemoveMobil(Jalan jalan) {
        this.jalan = jalan;
    }
    
    public synchronized void remove()
    {
        if (jalan.getListMobil().isEmpty() && jalan.getLampu().getWarna() == Warna.HIJAU) 
        {
            long interval = jalan.getListMobil().get(0).getIntervalKeluar();
            
            try 
            {
                sleep(interval);
                jalan.getListMobil().remove(0);
                notifyAll();
                System.out.println("(mobil keluar - "+jalan.getPosisi()+")");
            } 
            catch (InterruptedException ex) {
                Logger.getLogger(ThreadRemoveMobil.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        else 
        {
            try {
                wait();
            } catch (InterruptedException ex) {
                Logger.getLogger(ThreadRemoveMobil.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    @Override
    public void run()
    {
        while (true)
        {
            remove();
//            tesThread();
        }
    }
    
    public void tesThread()
    {
        try {
            System.out.println("thread jalan3");
            sleep(2000);
        } catch (InterruptedException ex) {
            Logger.getLogger(ThreadLampu.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
