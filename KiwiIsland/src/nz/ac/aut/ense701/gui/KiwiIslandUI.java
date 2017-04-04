/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nz.ac.aut.ense701.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.UIManager;
import nz.ac.aut.ense701.gameModel.Assets;
import nz.ac.aut.ense701.gameModel.Game;
import nz.ac.aut.ense701.gameModel.GameEventListener;
import nz.ac.aut.ense701.gameModel.Island;
import nz.ac.aut.ense701.gameModel.Player;
import nz.ac.aut.ense701.gameModel.Position;

/**
 *
 * @author Thong
 */
public class KiwiIslandUI extends JPanel implements ActionListener{
    
    private DrawingCanvas canvas;
    private Timer timer;
    private Game game;
    
    public KiwiIslandUI() {
        super();        
        setLayout(new BorderLayout());
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        Assets.init();
        game = new Game();
        canvas = new DrawingCanvas();
        add(canvas);
        
        timer = new Timer(20, this);
        timer.start();
    }
    
    public void update(){
        
    }
    
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == timer){
            canvas.repaint();
        }
    }
   
    private class DrawingCanvas extends JPanel{
        private int width, height;
        
        public DrawingCanvas(){
            Toolkit kit = Toolkit.getDefaultToolkit();
            Dimension screenSize = kit.getScreenSize();
            width = screenSize.width * 4 / 5;
            height = screenSize.height * 4 / 5;
            setPreferredSize(new Dimension(width, height));
            setBackground(Color.green);
        }
        
        public void paintComponent(Graphics g){
            super.paintComponent(g);
            
            game.render(g);
        }
    }
    
    public static void main(String[] args){
        JFrame frame = new JFrame("Kiwi Island");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(new KiwiIslandUI());
        frame.pack();
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
    }
}
