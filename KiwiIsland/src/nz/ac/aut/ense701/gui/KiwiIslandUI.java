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
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.UIManager;
import nz.ac.aut.ense701.gameModel.Assets;
import nz.ac.aut.ense701.gameModel.Game;
import nz.ac.aut.ense701.gameModel.MoveDirection;

/**
 *
 * @author Thong,Harindu
 */
public class KiwiIslandUI implements ActionListener {

    private JFrame frame;
    private DrawingCanvas canvas;
    private Timer timer;
    private Game game;
    private UIState state = UIState.Mainmenu;

    Toolkit kit = Toolkit.getDefaultToolkit();
    Dimension screenSize = kit.getScreenSize();
    int width = screenSize.width * 4 / 5;
    int height = screenSize.height * 4 / 5;

    public KiwiIslandUI() {

       

    }

    private void game(JFrame jframe) {
        Assets.init();

        game = new Game();

        frame.repaint();
        frame = jframe;

        frame.requestFocus();

        frame.setSize(width, height);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        canvas = new DrawingCanvas(width, height);
        frame.add(canvas);
        frame.pack();
        frame.addKeyListener(game.getKeyManager());

        timer = new Timer(20, this);
        timer.start();

    }

    public final void Mainmenu() {
        frame = new JFrame("Kiwi Island");
        try {

            JPanel panel = new JPanel() {

                private Image backgroundImage = ImageIO.read(new File("res/background.jpg"));

                public void paint(Graphics g) {
                    super.paint(g);
                     g.drawImage(backgroundImage, 0, 0, this.getWidth(), this.getHeight(), null);
                }
            };
            frame.setPreferredSize(new Dimension(width, height));

            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
            
            panel.setSize(width, height);
            JButton button = new JButton("new game");
            

            button.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {

                    state = UIState.Game;
                    game(frame);
                }
            });

            panel.add(button);
            frame.add(panel);
            frame.pack();
            frame.setVisible(true);
        } catch (IOException ex) {
            Logger.getLogger(KiwiIslandUI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public UIState getuistate() {
        return state;
    }

    public void update() {
        game.getKeyManager().update();

        if (game.getKeyManager().keyJustPressed(KeyEvent.VK_W)) {
            game.playerMove(MoveDirection.NORTH);
        }
        if (game.getKeyManager().keyJustPressed(KeyEvent.VK_S)) {
            game.playerMove(MoveDirection.SOUTH);
        }
        if (game.getKeyManager().keyJustPressed(KeyEvent.VK_A)) {
            game.playerMove(MoveDirection.WEST);
        }
        if (game.getKeyManager().keyJustPressed(KeyEvent.VK_D)) {
            game.playerMove(MoveDirection.EAST);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == timer) {
            update();
            canvas.repaint();
        }
    }

    private class DrawingCanvas extends JPanel {

        public DrawingCanvas(int width, int height) {
            super();
            setLayout(new BorderLayout());
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception e) {
                e.printStackTrace();
            }
            setPreferredSize(new Dimension(width, height));
            setBackground(Color.BLACK);
        }

        public void paintComponent(Graphics g) {
            super.paintComponent(g);

            game.render(g);
        }
    }

}
