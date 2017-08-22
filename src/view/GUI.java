/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author bukanjoker
 */
public class GUI {
    
    private JFrame frame;
    private JPanel panel;
    private int width, height;
    private String title;
    
    
    public GUI(String title, int width, int height)
    {
        this.title = title;
        this.width = width;
        this.height = height;
        
        init();
    }
    
    public void init()
    {
        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(width, height);
        frame.setLocation(200, 20);
        frame.setVisible(true);
    }
    
    public void drawBorder()
    {
        
    }
}
