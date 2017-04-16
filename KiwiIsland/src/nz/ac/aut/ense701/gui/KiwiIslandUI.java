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
import nz.ac.aut.ense701.gameModel.GridSquare;
import nz.ac.aut.ense701.gameModel.MoveDirection;

/**
 *
 * @author Thong,Harindu
 */
public class KiwiIslandUI implements ActionListener, GameEventListener{
    //variables-----------------------------------------------------------------
    private JFrame frame;
    private DrawingCanvas canvas;
    private JLabel titleLabel;
    private JButton newGameButton;
    private JButton loadGameButton;
    private JButton highscoreButton;
    private JButton exitButton;
    private Backgroundpanel backgroundPanel;
    
    private Timer timer;
    private Game game;    

    public static int width, height;
    //--------------------------------------------------------------------------
    
    //constructor---------------------------------------------------------------
    /**
     * Sets up the user interface and displays the main menu
     */
    public KiwiIslandUI() {
        //get the screen size
        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension screenSize = kit.getScreenSize();
        width = screenSize.width * 4 / 5;
        height = screenSize.height * 4 / 5;
        
        //set up the frame
        frame = new JFrame("Kiwi Island");
        frame.setPreferredSize(new Dimension(width, height));
        frame.setVisible(true);       
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        //addd a listener to close the application
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
        
        //create the main menu user interface
        createMainMenuView();
    }
    
    /**
     * Creates a game user interface.
     */
    private void createGameView(String name) {
        Assets.init();//initialize the assets
        game = new Game();//create a game
        game.getPlayer().setName(name);//set the player name
        frame.repaint();
        frame.requestFocus();
        
        canvas = new DrawingCanvas(height, height);
        frame.add(canvas);
        frame.pack();
        frame.addKeyListener(game.getKeyManager());
        
        //initialized the timer to call repeatedly
        timer = new Timer(20, this);
        timer.start();
    }

    /**
     * This createMainMenuView method contains code for the main menu of the game.
     */
    public void createMainMenuView() {
        //create the background panel
        backgroundPanel = new Backgroundpanel();
        backgroundPanel.setSize(width, height);
        backgroundPanel.setLayout(null);
        
        //create the buttons
        newGameButton = new JButton("New Game");
        loadGameButton = new JButton("Load Game");
        highscoreButton = new JButton("High Score");
        exitButton = new JButton("Exit Game");
        titleLabel = new JLabel("Kiwi Island");
        titleLabel.setFont(new Font("Serif", Font.BOLD, 36));
        titleLabel.setForeground(Color.WHITE);
        
        //set the size and position of the components
        titleLabel.setBounds(width / 2 - 100, 30, 200,100);        
        newGameButton.setBounds(width / 2 - 100, 250, 200, 60);
        loadGameButton.setBounds(width / 2 - 100, 325, 200, 60);
        highscoreButton.setBounds(width / 2 - 100, 400, 200, 60);
        exitButton.setBounds(width / 2 - 100, 475, 200, 60);

        //add the components to the panel
        backgroundPanel.add(titleLabel);
        backgroundPanel.add(newGameButton);
        backgroundPanel.add(loadGameButton);
        backgroundPanel.add(highscoreButton);
        backgroundPanel.add(exitButton);

        //listen for button press for a new game
        newGameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GetNameDialog getName = new GetNameDialog(frame);
                getName.setLocationRelativeTo(null);
                getName.setVisible(true);
                if (getName.getValidatedText() != null) {
                    frame.remove(backgroundPanel);
                    createGameView(getName.getValidatedText());
                }
            }
        }
        );

        //listen for exit button press
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);//terminate the application
            }
        }
        );
        
        //add the background panel to the frame
        frame.add(backgroundPanel); 
        frame.pack();
        frame.repaint();
        frame.revalidate();              
        frame.setLocationRelativeTo(null);
    }

    /**
     * This method is called repeatedly to update different variables in the background
     */
    public void update() {
        game.getKeyManager().update();//update the key input

        //check if the player has pressed the keys representing the move direction and update the position of the player accordingly
        if (game.getKeyManager().keyJustPressed(KeyEvent.VK_W) || game.getKeyManager().keyJustPressed(KeyEvent.VK_UP)) {
            game.playerMove(MoveDirection.NORTH);
        }
        if (game.getKeyManager().keyJustPressed(KeyEvent.VK_S) || game.getKeyManager().keyJustPressed(KeyEvent.VK_DOWN)) {
            game.playerMove(MoveDirection.SOUTH);
        }
        if (game.getKeyManager().keyJustPressed(KeyEvent.VK_A) || game.getKeyManager().keyJustPressed(KeyEvent.VK_LEFT)) {
            game.playerMove(MoveDirection.WEST);
        }
        if (game.getKeyManager().keyJustPressed(KeyEvent.VK_D) || game.getKeyManager().keyJustPressed(KeyEvent.VK_RIGHT)) {
            game.playerMove(MoveDirection.EAST);
        }
        
        //updates the size of each grid square dynamically from the size of the frame
        GridSquare.width = Math.min(frame.getContentPane().getHeight(), frame.getContentPane().getWidth()) / game.getNumColumns();
        GridSquare.height = GridSquare.width = Math.min(frame.getContentPane().getHeight(), frame.getContentPane().getWidth()) / game.getNumRows();
        
        //repaint the canvas with the updated information
        canvas.repaint();
        
        //check the game state
        gameStateChanged();
    }

    @Override
    /**
     * Check for an action trigger.
     */
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == timer) {
            //if the action is triggered by the timer, update the variables and GUI
            update();            
        }
    }

    @Override
    /**
     * Check if the game state has changed.
     */
    public void gameStateChanged() {
        // check for "game over" or "game won"
        if ( game.getState() == GameState.LOST )
        {
            JOptionPane.showMessageDialog(
                    frame, 
                    game.getLoseMessage(), "Game over!",
                    JOptionPane.INFORMATION_MESSAGE);
            game.createNewGame();
            frame.addKeyListener(game.getKeyManager());//add the listener again for the new game
        }
        else if ( game.getState() == GameState.WON )
        {
            JOptionPane.showMessageDialog(
                    frame, 
                    game.getWinMessage(), "Well Done!",
                    JOptionPane.INFORMATION_MESSAGE);
            game.createNewGame();
            frame.addKeyListener(game.getKeyManager());//add the listener for the name game
        }
        else if (game.messageForPlayer())
        {
            JOptionPane.showMessageDialog(
                    frame, 
                    game.getPlayerMessage(), "Important Information",
                    JOptionPane.INFORMATION_MESSAGE);   
        }
    }

    /**
     * This is a private class to draw all the objects existed in the game class into the screen.
     */
    private class DrawingCanvas extends JPanel {
        /**
         * Constructor to set up the panel.
         * 
         * @param width the specified width for the drawing canvas
         * @param height the specified height for the drawing canvas
         */
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
            game.render(g);//render the game and the objects it contains
        }
    }
}
