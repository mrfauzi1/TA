/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.Random;
import util.Posisi;

/**
 *
 * @author bukanjoker
 */
public class Mobil {
    private long intervalDatang, intervalKeluar;
    private long waktuDatang;
    private Posisi posisi;

    public Mobil(String randomIN, String randomOUT) 
    {
        this.intervalDatang = random(randomIN);
        this.intervalKeluar = random(randomOUT);
    }
    
    public long random(String pil)
    {
        long val = 0;
        switch (pil)
        {
            //default random
            case "default": val = new Random().nextInt(3)+1;
                    break;
            //random distribution
            case "pertama": val = new Random().nextInt(2)+1;
                    break;
            case "kedua": val = new Random().nextInt(3)+1;
                    break;
        }
        
        return val*1000;
    }

    public long getIntervalDatang() {
        return intervalDatang;
    }

    public void setIntervalDatang(long intervalDatang) {
        this.intervalDatang = intervalDatang;
    }

    public long getIntervalKeluar() {
        return intervalKeluar;
    }

    public void setIntervalKeluar(long intervalKeluar) {
        this.intervalKeluar = intervalKeluar;
    }

    public long getWaktuDatang() {
        return waktuDatang;
    }

    public void setWaktuDatang(long waktuDatang) {
        this.waktuDatang = waktuDatang;
    }
    
    
}
