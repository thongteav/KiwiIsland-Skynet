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
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.UIManager;
import javax.swing.WindowConstants;
import javax.swing.plaf.ComponentUI;
import nz.ac.aut.ense701.gameModel.Assets;
import nz.ac.aut.ense701.gameModel.Game;
import nz.ac.aut.ense701.gameModel.MoveDirection;

/**
 *
 * @author Thong,Harindu
 */
public class KiwiIslandUI implements ActionListener {

    private enum UIState {
        Mainmenu, Game
    };

    private JFrame frame;
    private DrawingCanvas canvas;
    private Timer timer;
    private Game game;

    Mainmenu main = new Mainmenu();
    UIState state = UIState.Game;
    JPanel pannel = new JPanel();
    JButton okButton = new JButton("OK");

    int width = 0;
    int height = 0;

    public KiwiIslandUI() {
        Assets.init();

        frame = new JFrame("Kiwi Island");
        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension screenSize = kit.getScreenSize();
        width = screenSize.width * 4 / 5;
        height = screenSize.height * 4 / 5;
        frame.setSize(width, height);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        pannel.add(okButton);
        okButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                state = UIState.Game;
                game = new Game();
                frame.remove(pannel);

                canvas = new DrawingCanvas(width, height);
                frame.add(canvas);

                frame.setVisible(true);

                frame.pack();
                frame.addKeyListener(game.getKeyManager());
                timer = new Timer(20, this);
                timer.start();

            }
        });
        frame.add(pannel);

        frame.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent ev) {
                //frame.dispose();
                System.out.println("works");
                if (state == UIState.Mainmenu) {
                    System.exit(0);
                } else {
                    state = UIState.Mainmenu;

                    frame.remove(canvas);
                    frame.repaint();
                    pannel.add(okButton);
                    frame.add(pannel);
                    frame.pack();
                    timer.stop();
                }
            }
        });
        frame.setVisible(true);

        frame.pack();

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
            setBackground(Color.white);
        }

        public void setUI(ComponentUI newUI) {
            frame.removeAll();
            JButton okButton = new JButton("OK");
            super.add(okButton);

        }

        public void paintComponent(Graphics g) {
            super.paintComponent(g);

            if (state == UIState.Game) {
                game.render(g);
            }

        }
    }

}
