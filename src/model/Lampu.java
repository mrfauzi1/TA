/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import util.Warna;

/**
 *
 * @author bukanjoker
 */
public class Lampu {
    private Warna warna;
    private long durasi;

    public Lampu() {
        this.warna = warna.merah;
    }

    public Warna getWarna() {
        return warna;
    }

    public void setWarna(Warna warna) {
        this.warna = warna;
    }

    public long getDurasi() {
        return durasi;
    }

    public void setDurasi(long durasi) {
        this.durasi = durasi;
    }
    
    
}
