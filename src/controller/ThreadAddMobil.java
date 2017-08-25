/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.util.logging.Level;
import java.util.logging.Logger;
import model.Jalan;
import model.Mobil;

/**
 *
 * @author bukanjoker
 */
public class ThreadAddMobil extends Thread {
    private Jalan jalan;
    private int size;
    private long time;

    public ThreadAddMobil(Jalan jalan, int jml) {
        this.jalan = jalan;
        this.size = jml;
    }
    
    @Override
    public void run()
    {
        while (true)
        {
            jalan.add(10);
//            tesThread();
        }
    }
    
    public void tesThread()
    {
        try {
            System.out.println("thread jalan2");
            sleep(2000);
        } catch (InterruptedException ex) {
            Logger.getLogger(ThreadLampu.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
