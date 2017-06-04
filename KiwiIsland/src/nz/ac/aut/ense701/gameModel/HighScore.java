/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nz.ac.aut.ense701.gameModel;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Locale;
import java.util.Scanner;

/**
 *
 * @author David
 */
public class HighScore implements Comparable<HighScore> {
    private String name;
    private int score;

    //A single highscore object list that can be accessed from all classes through accessor methods
    private static ArrayList<HighScore> highScores;
    //Variable to declare if new score was added.
    public static boolean newHighScore;

    //Constructor for individual scores to be entered
    public HighScore(String name, int score) {
        this.name = name;
        this.score = score;
    }

    //Constructor to create HighScore list from file
    public HighScore() {
        highScores = new ArrayList<HighScore>();
        newHighScore = false;
        createHighScore();
    }

    @Override
    public String toString() {
        return this.name + ": " + this.score;
    }

    @Override
    public int compareTo(HighScore o) {
        return Integer.compare(this.score, o.score);
    }

    //Creates the highscore off of the text file
    public static ArrayList<HighScore> createHighScore() {
        try {
            Scanner input = new Scanner(new File("highscores.txt"));
            // make sure decimal numbers are read in the form "123.23"
            input.useLocale(Locale.US);
            input.useDelimiter("\\s*,\\s*");

            for (int i = 0; i < 5; i++) {
                highScores.add(new HighScore(input.next(), input.nextInt()));
            }
            Collections.sort(highScores);
            Collections.reverse(highScores);
            for (int i = 0; i < 5; i++) {
                System.out.println(highScores.get(i));
            }
            input.close();
            return highScores;

        } catch (FileNotFoundException e) {
            System.err.println("Unable to find data file");
        } catch (IOException e) {
            System.err.println("Problem encountered processing file.");
        }
        return null;
    }

    //Saves highscores to the text file 
    public static void saveScoresToFile() {
        try {
            File file = new File("highscores.txt");
            FileWriter writer = new FileWriter(file);
            String scores = "";

            for (int i = 0; i < 5; i++) {
                scores += highScores.get(i).name + ",";
                scores += highScores.get(i).score + ",";
            }

            writer.write(scores);
            writer.flush();
            writer.close();

        } catch (FileNotFoundException e) {
            System.err.println("Unable to find data file");
        } catch (IOException e) {
            System.err.println("Problem encountered processing file.");
        }
    }

    //takes input field of new score and adds it to the list if it is greater than lowest score. Then calls save to file function
    public static void saveScores(HighScore score) {
        if (score.getScore() > highScores.get(4).getScore()) {
            highScores.set(4, score);
            Collections.sort(highScores);
            Collections.reverse(highScores);
            newHighScore = true;
            saveScoresToFile();
        }
    }

    //Return string for listing all 5 highscores.
    public static String getScoreList() {
        return "High Scores\n\n1: " + highScores.get(0) + "\n2: " + highScores.get(1) + "\n3: " + highScores.get(2) + "\n4: " + highScores.get(3) + "\n"
                + "5: " + highScores.get(4);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public static ArrayList<HighScore> getHighScores() {
        return highScores;
    }

    public static void setHighScores(ArrayList<HighScore> highScores) {
        HighScore.highScores = highScores;
    }
}
