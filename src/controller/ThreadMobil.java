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
    
    public void aktivitas()
    {
        if (kondisi == "masuk") 
        {
            jalan.add(jml);
        }
        else if (kondisi == "keluar")
        {
            jalan.remove();
        }
    }
    
    @Override
    public void run()
    {
        while (true)
        {
            aktivitas();
        }
    }
}
