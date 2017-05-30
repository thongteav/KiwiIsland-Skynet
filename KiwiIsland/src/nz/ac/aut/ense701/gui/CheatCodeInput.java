/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nz.ac.aut.ense701.gui;

import java.awt.*;
import java.awt.event.*;
import java.beans.*;
import java.util.regex.Pattern;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;
import nz.ac.aut.ense701.gameModel.Cheat;
import nz.ac.aut.ense701.gameModel.Game;
import nz.ac.aut.ense701.gameModel.GameState;
import nz.ac.aut.ense701.gameModel.Player;

/**
 * @author George Xu
 **/

class CheatCodeInput extends JDialog implements ActionListener, PropertyChangeListener {

    private String typedText = null;
    private JTextField textField;
    private JOptionPane optionPane;
    private String btnString1 = "Enter";
    private String btnString2 = "Cancel";
    private Cheat cheat;
 
    /**
     * Returns null if the typed string was invalid; otherwise, returns the
     * string as the user entered it.
     */
    public String getValidatedText() {
        return typedText;
    }

   
    
    /**
     * Creates the dialog that can be reused.
     */
    public CheatCodeInput(Frame aFrame) {
        super(aFrame, true);
        setTitle("Input cheat code");
        textField = new JTextField(10);
      
        //Create an array of the text and components to be displayed. Messages can be added later
        String msgString1 = "Please enter the cheat code:";
    
        Object[] array = {msgString1, textField};

        //Create an array specifying the number of dialog button and their text.
        Object[] options = {btnString1, btnString2};

        //Create the JOptionPane.
        optionPane = new JOptionPane(array,
                JOptionPane.QUESTION_MESSAGE,
                JOptionPane.YES_NO_OPTION,
                null,
                options,
                options[0]);

        //Make this dialog display it.
        setContentPane(optionPane);

        //Sets the handling of closing.
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        //Ensure the text field always gets the first focus.
        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentShown(ComponentEvent ce) {
                textField.requestFocusInWindow();
            }
        });

        //Set event listeners and actions
        textField.addActionListener(this);
        optionPane.addPropertyChangeListener(this);
        pack();
    }

    /**
     * This method handles events for the text field.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        optionPane.setValue(btnString1);
      
    }

    /**
     * This method reacts to state changes in the option pane.
     */
    @Override
    public void propertyChange(PropertyChangeEvent e) {
     
        String prop = e.getPropertyName();
        if (isVisible()
                && (e.getSource() == optionPane)
                && (JOptionPane.VALUE_PROPERTY.equals(prop)
                || JOptionPane.INPUT_VALUE_PROPERTY.equals(prop))) {
            Object value = optionPane.getValue();
            if (btnString1.equals(value)) {
                typedText = textField.getText();
                if( "winNow".equals(typedText)){ 
                  cheat=Cheat.WINNOW;
                }
                else if( "staminaNow".equals(typedText)){ 
                  cheat=Cheat.MAX_STNAMIA;
                }
                else{
                    cheat=null;
                }
            }
            
            
            if (value == JOptionPane.UNINITIALIZED_VALUE) {
                //ignore reset
                return;
            }

            //Reset the JOptionPane's value.
            //If you don't do this, then if the user
            //presses the same button next time, no
            //property change event will be fired.
            optionPane.setValue(
                    JOptionPane.UNINITIALIZED_VALUE);
            
            //Checks the input against regex to match suitable strings
         
               
       
                exit();
            
        }
    }
    
    public Cheat getcheat(){
    
        return cheat;
    }

    /**
     * This method clears the dialog and hides it.
     */
    public void exit() {
        dispose();
    }
}
