/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Jalan;
import util.Posisi;

/**
 *
 * @author bukanjoker
 */
public class Manager extends Thread {
    private Jalan[] jalan;
    private int[] urutan = {1,0,2,3};

    public Manager()
    {
        //inisiasi 4 jalan
        jalan = new Jalan[4];
        jalan[0] = new Jalan(Posisi.atas, false);
        jalan[1] = new Jalan(Posisi.bawah, false);
        jalan[2] = new Jalan(Posisi.kanan, false);
        jalan[3] = new Jalan(Posisi.kiri, false);
    }
    
    public void run()
    {
        //thread jalan start
        jalan[0].start();
        jalan[1].start();
        jalan[2].start();
        jalan[3].start();
        
        //default first servis
        int current = urutan[3];
        
        //init for candidate
        int candidate = 0;
        
        //init for max value
        double max = 0;
        
        //set lampu dinamis / statis
        String lampu = "statis";
        
        //looping changing lamps
        while (true)
        {
            for (int j = 0; j < urutan.length; j++) 
            {
                for (int i = 0; i < jalan.length; i++)
                {
                    //lampu hijau
                    if (i == current)
                    {
                        //lampu hijau
                        jalan[i].setStatus(true);
                    }
                    //lampu merah
                    else
                    {
                        jalan[i].setStatus(false);

                        //lampu dinamis
                        if (lampu == "dinamis") 
                        {
                            //HRRN Calculation
                            jalan[i].setRatio(HRRN(jalan[i].getWait(), jalan[i].getLampu().getDurasi()));

                            //look up highest ratio
                            if (jalan[i].getRatio() > max)
                            {
                                max = jalan[i].getRatio();
                                candidate = i;
                            }  
                        }
                        //lampu statis
                        else
                        {
                            candidate = urutan[j];
                        }           
                    }
                    print(i);
                }
            
                //added print
                System.out.println("Durasi: "+jalan[current].getLampu().getDurasi()+" miliseconds");
                System.out.println("");

                //tunggu durasi lampu hijau
                try 
                {
                    sleep(jalan[current].getLampu().getDurasi());
                } 
                catch (InterruptedException ex) {
                    Logger.getLogger(Manager.class.getName()).log(Level.SEVERE, null, ex);
                }

                current = candidate;
            }
        }
    }
    
    public double HRRN(double wait, long servis)
    {
        double ratio = (wait+servis)/servis;
        return ratio;
    }
    
    public void print(int i)
    {
        System.out.print("["+jalan[i].getPosisi()+"]");
        System.out.print("["+jalan[i].getLampu().getWarna()+"]");
        System.out.print("[Ratio:"+jalan[i].getRatio()+"]");
        System.out.print("[Waktu Tunggu:"+jalan[i].getWait()/1000+"]");
        System.out.print("[Jumlah Mobil: "+jalan[i].getListMobil().size()+"]");
        System.out.println("");
    }
    
    public void setDurasi(long jalan1, long jalan2, long jalan3, long jalan4)
    {
        jalan[0].getLampu().setDurasi(jalan1);
        jalan[1].getLampu().setDurasi(jalan2);
        jalan[2].getLampu().setDurasi(jalan3);
        jalan[3].getLampu().setDurasi(jalan4);
    }
            
    public Jalan[] getJalan() {
        return jalan;
    }
    
    public Jalan getJalan(int i)
    {
        return jalan[i];
    }

    public void setJalan(Jalan[] jalan) {
        this.jalan = jalan;
    }
}
