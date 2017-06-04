/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nz.ac.aut.ense701.gui;

import nz.ac.aut.ense701.audio.AudioPlayer;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.LayoutManager;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.Timer;
import javax.swing.UIManager;
import nz.ac.aut.ense701.gameModel.Difficulty;
import nz.ac.aut.ense701.gameModel.Fauna;
import nz.ac.aut.ense701.gameModel.Food;
import nz.ac.aut.ense701.gameModel.Game;
import nz.ac.aut.ense701.gameModel.GameEventListener;
import nz.ac.aut.ense701.gameModel.GameState;
import nz.ac.aut.ense701.gameModel.GridSquare;
import nz.ac.aut.ense701.gameModel.HighScore;
import nz.ac.aut.ense701.gameModel.Kiwi;
import nz.ac.aut.ense701.gameModel.MoveDirection;
import nz.ac.aut.ense701.gameModel.Occupant;
import nz.ac.aut.ense701.gameModel.Predator;
import nz.ac.aut.ense701.gameModel.Tool;

/**
 *
 * @author Thong,Harindu
 */
public class KiwiIslandUI implements ActionListener, GameEventListener {

    //variables-----------------------------------------------------------------
    private JFrame frame;
    private DrawingCanvas canvas;
    private JLabel titleLabel;
    private JLabel predatorLabel;
    private JLabel predatorLeftLabel;
    private JButton newGameButton;
    private JButton highscoreButton;
    private JButton helpButton;
    private JButton exitButton;
    private BackgroundPanel backgroundPanel;
    private StatusBarPanel statusbarPanel;
    private JPanel gamePanel;
    private JProgressBar staminaProgressBar;
    private JLabel staminaLable;
    private JLabel kiwiCountLabel;
    private JLabel overallScoreLabel;

    private Timer timer;
    private Game game;
    private boolean hastrap;
    private CheatCodeInput code;

    //Audio Elements
    private AudioPlayer bgMusic;
    private HashMap<String, AudioPlayer> sfx;
    
    //HighScore List Creation
    HighScore highScores = new HighScore();
    
    public static int width, height;
    public static Difficulty difficulty = Difficulty.EASY;    
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
        frame.setPreferredSize(new Dimension(height+(height/4), height));
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.setResizable(false);
        //addd a listener to close the application
        frame.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                if (JOptionPane.showConfirmDialog(frame,
                        "Are you sure to exit the game?", "Exit Game?",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {
                    System.exit(0);
                }
            }
        });
        //create the main menu user interface
        createMainMenuView();
        code=new CheatCodeInput(frame);
        bgMusic = new AudioPlayer(new File("res/audio/music/bird_in_rain.mp3"));
        bgMusic.play();
        sfx = new HashMap<String, AudioPlayer>();
        sfx.put("eat", new AudioPlayer(new File("res/audio/sfx/apple_bite.mp3")));
        //Weka sound used since Kiwi call is very harsh
        sfx.put("kiwi", new AudioPlayer(new File("res/audio/sfx/weka-song.mp3")));
        sfx.put("predator", new AudioPlayer(new File("res/audio/sfx/146960__zabuhailo__cathisses.wav")));
    }

    /**
     * Creates a game user interface.
     */
    private void createGameView(String name) {
        Object[] possibilities = {"Easy", "Medium", "Hard"};
        String s = (String)JOptionPane.showInputDialog(
                    frame,
                    "Choose a difficulty for the game:",
                    "Game Difficulty",
                    JOptionPane.PLAIN_MESSAGE,
                    null,
                    possibilities,
                    "Easy");
        if(s == "Medium"){
            difficulty = Difficulty.MEDIUM;
        } 
        else if (s == "Hard") {
            difficulty = Difficulty.HARD;
        } 
        else {
            difficulty = Difficulty.EASY;
        }
        
        Assets.init();//initialize the assets
        game = new Game();//create a game
        game.getPlayer().setName(name);//set the player name

        bgMusic.stop();
        bgMusic = new AudioPlayer(new File("res/audio/music/Puzzle-Game_Looping.mp3"));
        bgMusic.play();

        frame.requestFocus();
        frame.setResizable(false);
        canvas = new DrawingCanvas(height, height);

        statusbarPanel = new StatusBarPanel(width / 5, height);
        
        setupPredatorLabel();

        setUpKiwiCountText();
        setOverallScoreText();
        //initialize status bar components
        setupStatusBarComponent();
       
        gamePanel = new JPanel(new BorderLayout());
        gamePanel.add(statusbarPanel, BorderLayout.EAST);
        gamePanel.add(canvas, BorderLayout.CENTER);
//        gamePanel.setSize(height, height);
        frame.add(gamePanel);

        frame.repaint();
        frame.revalidate();
        frame.pack();
        frame.addKeyListener(game.getKeyManager());

        //initialized the timer to call repeatedly
        timer = new Timer(20, this);
        timer.start();
    }

    /*
 adding predator label
     */
    public void setupPredatorLabel() {
        predatorLabel = new JLabel("Predator remaining: " + game.getPredatorsRemaining());
        predatorLabel.setForeground(Color.WHITE);
    }

    /*
 adding predator count text
     */
    public void setupPredatorCountText() {
        predatorLeftLabel = new JLabel("");
        predatorLeftLabel.setForeground(Color.WHITE);
        statusbarPanel.add(predatorLeftLabel);
    }
    
    public void setUpKiwiCountText() {
        kiwiCountLabel = new JLabel("Kiwis counted: " + game.getOverallScore());
        kiwiCountLabel.setForeground(Color.WHITE);
    }
    
    public void setOverallScoreText() {
        overallScoreLabel = new JLabel("Score: " + game.getKiwiCount());
        overallScoreLabel.setForeground(Color.WHITE);
    }

     /**
     adding components to status bar
     */
    public void setupStatusBarComponent() {
        statusbarPanel.setLayout(new BoxLayout(statusbarPanel, BoxLayout.Y_AXIS));
        JLabel playerName = new JLabel("Player name: " + game.getPlayerName());
        playerName.setForeground(Color.WHITE);
        staminaLable = new JLabel("Stamina");
        staminaLable.setForeground(Color.WHITE);

        staminaProgressBar = new JProgressBar();

        JLabel creditLbl = new JLabel("**CREDITS**");
        creditLbl.setForeground(Color.WHITE);
        creditLbl.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                JOptionPane.showMessageDialog(frame, readTextFile("credits.txt"));
            }

            @Override
            public void mousePressed(MouseEvent e) {
            }

            @Override
            public void mouseReleased(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
            }

            @Override
            public void mouseExited(MouseEvent e) {
            }
        });
        JLabel highscores = new JLabel("**HIGHSCORES**");
        highscores.setForeground(Color.WHITE);
        highscores.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                displayHighScore();
            }

            @Override
            public void mousePressed(MouseEvent e) {
            }

            @Override
            public void mouseReleased(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
            }

            @Override
            public void mouseExited(MouseEvent e) {
            }
        });
        statusbarPanel.add(playerName);
        statusbarPanel.add(staminaLable);
        statusbarPanel.add(staminaProgressBar);
        statusbarPanel.add(predatorLabel);
        statusbarPanel.add(kiwiCountLabel);
        statusbarPanel.add(overallScoreLabel);
        statusbarPanel.add(creditLbl);
        statusbarPanel.add(highscores);
    }
    
    /**
     * This createMainMenuView method contains code for the main menu of the
     * game.
     */
    public void createMainMenuView() {
        //create the background panel
        backgroundPanel = new BackgroundPanel();
        backgroundPanel.setSize(height, height);
        backgroundPanel.setLayout(null);

        //create the buttons
        newGameButton = new JButton("New Game");
        highscoreButton = new JButton("High Score");
        exitButton = new JButton("Exit Game");
        helpButton = new JButton("Help");
        titleLabel = new JLabel("Kiwi Island");
        titleLabel.setFont(new Font("Serif", Font.BOLD, 36));
        titleLabel.setForeground(Color.WHITE);

        //set the size and position of the components
        titleLabel.setBounds(width / 2 - 250, 30, 200, 100);
        newGameButton.setBounds(width / 2 - 250, 220, 200, 70);
        highscoreButton.setBounds(width / 2 - 250, 300, 200, 70);
        helpButton.setBounds(width / 2 - 250, 380, 200, 70);
        exitButton.setBounds(width / 2 - 250, 460, 200, 70);

        //add the components to the panel
        backgroundPanel.add(titleLabel);
        backgroundPanel.add(newGameButton);
        backgroundPanel.add(highscoreButton);
        backgroundPanel.add(helpButton);
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
                    frame.setResizable(true);
                    createGameView(getName.getValidatedText());
                }
                getName.exit();
            }
        }
        );
        
        //Listener for the highscore button
        highscoreButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                displayHighScore();
            }
        });
        
        //listen for help button press
        helpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                helpList();
                
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
        frame.setResizable(false);
        frame.revalidate();
        frame.setLocationRelativeTo(null);
    }

    /**
     * This method is called repeatedly to update different variables in the
     * background
     */
    public void update() {
        game.tick();
        //updates the size of each grid square dynamically from the size of the frame
        GridSquare.width = Math.min(frame.getContentPane().getHeight(), frame.getContentPane().getWidth()) / game.getNumColumns();
        GridSquare.height = GridSquare.width = Math.min(frame.getContentPane().getHeight(), frame.getContentPane().getWidth()) / game.getNumRows();
        //updates the number of predator left
        predatorLabel.setText("Predators remaining: " + game.getPredatorsRemaining());
        kiwiCountLabel.setText("Kiwis counted: " + game.getKiwiCount());
        overallScoreLabel.setText("Score: " + game.getOverallScore());
        //repaint the canvas with the updated information
        canvas.repaint();
       //repaint staminabar alignment according to frame size      
//        resizeComponentsAlignments(frame.getContentPane().getHeight(),frame.getContentPane().getHeight());     
        //with each move player status will updated
        SetPlayerStatus();
        //show help menu
        if (game.getKeyManager().keyJustPressed(KeyEvent.VK_H)) {
            helpList();
        }
          if (game.getKeyManager().keyJustPressed(KeyEvent.VK_BACK_QUOTE)) {
            cheatCodePopUp();
               game.staminaCheat(code.getcheat());
               game.wonCheat(code.getcheat());
          }
        if(game.getPlayer().isStopped()){            
            //check the game state
            gameStateChanged();
            
            for (Occupant occupant : game.getOccupantsPlayerPosition()) {
                if (!occupant.isInteracted() || game.getKeyManager().keyJustPressed(KeyEvent.VK_E)) {
                    if (occupant instanceof Food) {
                        showFoodPopUp(occupant);
                    } else if (occupant instanceof Tool) {
                        showToolPopUp(occupant);
                    } else if (occupant instanceof Kiwi) {
                        showKiwiPopUp(occupant);
                        sfx.get("kiwi").play();
                    } else if (occupant instanceof Predator) {
                        showCatchPredatorPopUp(occupant);
                        sfx.get("predator").play();
                    } else if(occupant instanceof Fauna){
                        showFaunaPopUp(occupant);
                    } 
                }
            }
        }
    }
    
     //shows help menu
    public void helpList() {
        JOptionPane.showMessageDialog(
                frame,
                "<html><b>Player Movement Controls</b> <br>Move North: W /north arrow<br>Move South: S /south arrow<br> Move East: D /East arrow<br> Move West: A /West arrow<br>"
                        + "<br>Player Actions<br>Pick Item: E<br>"
                        + "<br>Inventory Controls<br>Open Inventory: I<br>Use Item from Inventory: E<br>Drop Item from Inventory: Space "
                       +"<br>Cheat Code Input<br>Open cheat code input: `<br>Win the game: winNow<br>Increase stamina to max: staminaNow </html>"
                , "Help",
                JOptionPane.INFORMATION_MESSAGE);
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
    public void cheatCodePopUp(){
                code.setLocationRelativeTo(null);
                code.setVisible(true);
}
    
    public void displayHighScore(){
        JOptionPane.showMessageDialog(
                frame,
                HighScore.getScoreList()
                , "High Scores",
                JOptionPane.INFORMATION_MESSAGE);
    }
    
    public void displayNewHighScore(){
        JOptionPane.showMessageDialog(
                    frame,
                    HighScore.getScoreList(), "New high score "+ game.getPlayerName() +"!",
                    JOptionPane.INFORMATION_MESSAGE);
    }
  
    @Override
    /**
     * Check if the game state has changed.
     */
    public void gameStateChanged() {
        // check for "game over" or "game won"
        if (game.getState() == GameState.LOST) {
            JOptionPane.showMessageDialog(
                    frame,
                    game.getLoseMessage(), "Game over!",
                    JOptionPane.INFORMATION_MESSAGE);
            if(HighScore.newHighScore){
                displayNewHighScore();
                HighScore.newHighScore = false;
            }
            game.createNewGame();
            frame.addKeyListener(game.getKeyManager());//add the listener again for the new game
        } else if (game.getState() == GameState.WON) {
            JOptionPane.showMessageDialog(
                    frame,
                    game.getWinMessage(), "Well Done!",
                    JOptionPane.INFORMATION_MESSAGE);
            if(HighScore.newHighScore){
                displayNewHighScore();
                HighScore.newHighScore = false;
            }
            game.createNewGame();
            frame.addKeyListener(game.getKeyManager());//add the listener for the name game
        } else if (game.messageForPlayer()) {
            JOptionPane.showMessageDialog(
                    frame,
                    game.getPlayerMessage(), "Important Information",
                    JOptionPane.INFORMATION_MESSAGE);
        }

    }

    public void showFoodPopUp(Occupant occupant) {
        Object[] options = {"Collect", "Eat"};
        int userInput = JOptionPane.showOptionDialog(
                frame,
                "You have encountered: " + occupant.getDescription(),
                "Food",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                options[0]);
        if (userInput == JOptionPane.YES_OPTION || userInput == JOptionPane.NO_OPTION) {
            if (!game.collectItem(occupant)) {
                JOptionPane.showConfirmDialog(frame, "Sorry, you can't collect this item. Free some space from the bag.", "Inventory full", JOptionPane.WARNING_MESSAGE);
            }
            if (userInput == JOptionPane.NO_OPTION) {
                sfx.get("eat").play();
                game.useItem(occupant);
            }
        }
        occupant.setInteracted(true);
    }

    public void showCatchPredatorPopUp(Occupant occupant) {
        Object[] options = {"Use Trap"};
        int userInput = JOptionPane.showOptionDialog(
                frame,
                "You have encountered: " + occupant.getDescription(),
                "Predator",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                options[0]);
        if(userInput == JOptionPane.YES_OPTION){
            if(game.useItem(game.getPlayer().getTrap())){                
                game.trapPredator();
            } else {
                JOptionPane.showMessageDialog(frame, "Please collect the trap first", "Can't trap predator", JOptionPane.WARNING_MESSAGE);
            }
        } 

        occupant.setInteracted(true);
    }

    public void showToolPopUp(Occupant occupant) {
        Object[] options = {"Collect", "Ignore"};
        int userInput = JOptionPane.showOptionDialog(
                frame,
                "You have encountered: " + occupant.getDescription(),
                "Tool",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                options[0]);
        if (userInput == JOptionPane.YES_OPTION) {
            hastrap = true;
            if (!game.collectItem(occupant)) {
                JOptionPane.showMessageDialog(frame, "Item is not collected.", "Can't collect the item.", JOptionPane.WARNING_MESSAGE);
            }
        }
        occupant.setInteracted(true);
    }

    public void showKiwiPopUp(Occupant occupant) {
        Object[] options = {"Count", "Ignore"};
        int userInput = JOptionPane.showOptionDialog(
                frame,
                "You have encountered: " + occupant.getDescription(),
                "Kiwi",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                options[0]);
        if (userInput == JOptionPane.YES_OPTION) {
            if (game.canCount(occupant)) {
                game.countKiwi();
            } else {
                JOptionPane.showMessageDialog(frame, "Kiwi already been counted.", "Kiwi", JOptionPane.WARNING_MESSAGE);
            }
        }
        occupant.setInteracted(true);
    }

    public void showFaunaPopUp(Occupant occupant){
        JOptionPane.showMessageDialog(frame, "You have encountered: " + occupant.getDescription(), occupant.getName(), JOptionPane.INFORMATION_MESSAGE);
        occupant.setInteracted(true);
    }   

    /**
     * gets player values from game object and updates player games status
     * accordingly.
     */
    public void SetPlayerStatus() {
        int[] playerValues = game.getPlayerValues();
        staminaProgressBar.setMaximum(playerValues[Game.MAXSTAMINA_INDEX]);
        staminaProgressBar.setValue(playerValues[Game.STAMINA_INDEX]);
    }

    private String readTextFile(String path){
        String content = "";
        FileReader fr = null;
        try {            
            fr = new FileReader(path);
            BufferedReader inputStream = new BufferedReader(fr);
            String line = null;
            while((line=inputStream.readLine())!=null){
                content += line + "\n";
            }  
        } catch (FileNotFoundException ex) {
            System.out.println("File not found: " + ex);
        } catch (IOException ex) {
            System.out.println("Error opening file: " + ex);
        } finally {
            try {
                if(fr != null){
                    fr.close();
                }
            } catch (IOException ex) {
                System.out.println("Can't open file: " + ex); 
            }
        }
        return content;
    }
    
    /**
     * This is a private class to draw all the objects existed in the game class
     * into the screen.
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
            setBackground(Color.GRAY);
        }

        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            setSize(GridSquare.width * game.getNumColumns(), GridSquare.height * game.getNumRows());
            game.render(g);//render the game and the objects it contains
        }
    }
}
