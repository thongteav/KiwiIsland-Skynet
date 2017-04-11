/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nz.ac.aut.ense701.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.UIManager;
import nz.ac.aut.ense701.assets.Assets;
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
    private Backgroundpanel panel = new Backgroundpanel();

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
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        canvas = new DrawingCanvas(width, height);
        frame.add(canvas);
        frame.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                if (JOptionPane.showConfirmDialog(frame,
                        "Are you sure to exit the game? progress will be lost", "Exit Game?",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {
                    System.exit(0);
                }
            }
        });
        frame.pack();
        frame.addKeyListener(game.getKeyManager());

        timer = new Timer(20, this);
        timer.start();

    }

    /**
     * The Mainmenu method contains code for the main menu of the game.
     */
    public final void Mainmenu() {
        frame = new JFrame("Kiwi Island");

        frame.setPreferredSize(
                new Dimension(width, height));

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        panel.setSize(width, height);
        JButton button = new JButton("New Game");
        JButton button2 = new JButton("Load Game");
        JButton button3 = new JButton("High Score");
        JButton button4 = new JButton("Exit Game");

        JLabel title = new JLabel("Kiwi Island");

        title.setFont(new Font("Serif", Font.BOLD, 36));
        title.setForeground(Color.WHITE);

        button.addActionListener(
                new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e
            ) {
                frame.remove(panel);
                state = UIState.Game;
                game(frame);
            }
        }
        );

        button4.addActionListener(
                new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e
            ) {
                System.exit(0);
            }
        }
        );

        panel.setLayout(null);

        title.setBounds(
                300, 30,
                200,
                100);

        button.setBounds(width
                / 2, 250,
                200,
                60);
        button2.setBounds(width
                / 2, 325,
                200,
                60);
        button3.setBounds(width
                / 2, 400,
                200,
                60);
        button4.setBounds(width
                / 2, 475,
                200,
                60);

        title.setText("Kiwi Island");

        panel.add(title);
        panel.add(button);
        panel.add(button2);
        panel.add(button3);
        panel.add(button4);

        //frame.add(background1);
        frame.add(panel);
 
        
        frame.pack();
        frame.repaint();
        frame.setLocationRelativeTo(null);
        frame.revalidate();
        frame.setVisible(true);
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

        @Override
        public void paintComponent(Graphics g) {

            super.paintComponent(g);
            game.render(g);
        }
    }

}
