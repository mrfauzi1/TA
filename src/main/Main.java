/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import controller.Manager;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Jalan;
import view.GUI;

/**
 *
 * @author bukanjoker
 */
public class Main {
    public static void main(String[] args) 
    {
        Manager main = new Manager();
        
        //set durasi jalan1, jalan2, jalan3, jalan4
        main.setDurasi(3000, 1000, 2000, 1000);
        
        main.start();
        
//        GUI gui = new GUI("Traffic Simulation", 1000, 700);
    }
}
