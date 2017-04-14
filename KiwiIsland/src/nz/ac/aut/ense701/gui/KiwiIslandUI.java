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
import nz.ac.aut.ense701.gameModel.GameEventListener;
import nz.ac.aut.ense701.gameModel.GameState;
import nz.ac.aut.ense701.gameModel.MoveDirection;

/**
 *
 * @author Thong,Harindu
 */
public class KiwiIslandUI implements ActionListener, GameEventListener{

    private JFrame frame;
    private DrawingCanvas canvas;

    private Timer timer;
    private Game game;
    private UIState state = UIState.Mainmenu;
    private Backgroundpanel backgroundPanel = new Backgroundpanel();

    public static int width, height;
    
    public KiwiIslandUI() {
        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension screenSize = kit.getScreenSize();
        width = screenSize.width * 4 / 5;
        height = screenSize.height * 4 / 5;
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
        canvas = new DrawingCanvas(height, height);
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

        backgroundPanel.setSize(width, height);
        JButton newGameButton = new JButton("New Game");
        JButton loadGameButton = new JButton("Load Game");
        JButton highscoreButton = new JButton("High Score");
        JButton exitButton = new JButton("Exit Game");

        JLabel title = new JLabel("Kiwi Island");

        title.setFont(new Font("Serif", Font.BOLD, 36));
        title.setForeground(Color.BLACK);

        newGameButton.addActionListener(
                new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e
            ) {
                frame.remove(backgroundPanel);
                state = UIState.Game;
                game(frame);
            }
        }
        );

        exitButton.addActionListener(
                new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e
            ) {
                System.exit(0);
            }
        }
        );

        backgroundPanel.setLayout(null);

        title.setBounds(
                300, 30,
                200,
                100);
        
        newGameButton.setBounds(width
                / 2, 250,
                200,
                60);
        loadGameButton.setBounds(width
                / 2, 325,
                200,
                60);
        highscoreButton.setBounds(width
                / 2, 400,
                200,
                60);
        exitButton.setBounds(width
                / 2, 475,
                200,
                60);

        title.setText("Kiwi Island");

        backgroundPanel.add(title);
        backgroundPanel.add(newGameButton);
        backgroundPanel.add(loadGameButton);
        backgroundPanel.add(highscoreButton);
        backgroundPanel.add(exitButton);

        //frame.add(background1);
        frame.add(backgroundPanel);
 
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
        
        canvas.repaint();
        gameStateChanged();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == timer) {
            update();            
        }
    }

    @Override
    public void gameStateChanged() {
        // check for "game over" or "game won"
        if ( game.getState() == GameState.LOST )
        {
            JOptionPane.showMessageDialog(
                    frame, 
                    game.getLoseMessage(), "Game over!",
                    JOptionPane.INFORMATION_MESSAGE);
            game.createNewGame();
            frame.addKeyListener(game.getKeyManager());
        }
        else if ( game.getState() == GameState.WON )
        {
            JOptionPane.showMessageDialog(
                    frame, 
                    game.getWinMessage(), "Well Done!",
                    JOptionPane.INFORMATION_MESSAGE);
            game.createNewGame();
            frame.addKeyListener(game.getKeyManager());
        }
        else if (game.messageForPlayer())
        {
            JOptionPane.showMessageDialog(
                    frame, 
                    game.getPlayerMessage(), "Important Information",
                    JOptionPane.INFORMATION_MESSAGE);   
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
            setBackground(Color.WHITE);
        }

        @Override
        public void paintComponent(Graphics g) {

            super.paintComponent(g);
            game.render(g);
        }
    }

}
