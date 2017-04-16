/*
 * Title: CustomerDialog source code
 * Author: Oracle
 * Date: 16/04/17
 * Code Version: 1.4
 * Availability: https://docs.oracle.com/javase/tutorial/uiswing/examples/components/DialogDemoProject/src/components/CustomDialog.java
 * Use of source code must be referenced with below copyright and Oracle is not used to promote product
 * Copyright (c) 1995, 2008, Oracle and/or its affiliates. All rights reserved.
 */
package nz.ac.aut.ense701.gui;

import java.awt.*;
import java.awt.event.*;
import java.beans.*;
import java.util.regex.Pattern;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 * This class establishes the dialog boxes to enter name and checks input to ensure it is correct
 * @author Harindu Tillekeratna
 * @author David Balzer
 * @author George Xu
 * @author Thong Teav
 **/

class GetNameDialog extends JDialog implements ActionListener, PropertyChangeListener {

    private String typedText = null;
    private JTextField textField;
    private JOptionPane optionPane;
    private String btnString1 = "Enter";
    private String btnString2 = "Cancel";

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
    public GetNameDialog(Frame aFrame) {
        super(aFrame, true);

        setTitle("Player Name");

        textField = new JTextField(10);

        //Create an array of the text and components to be displayed. Messages can be added later
        String msgString1 = "Please enter your player name:";
    
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
            if (btnString1.equals(value)) {
                Pattern pattern = Pattern.compile("^[A-Za-z_ ]++$");
                typedText = textField.getText();
                if (pattern.matcher(typedText).matches()) {
                    exit();
                } else {
                    //text was invalid
                    textField.selectAll();
                    JOptionPane.showMessageDialog(this,
                            "Sorry, \"" + typedText + "\" "
                            + "isn't a valid name.\n"
                            + "Only alphanumeric characters, underscores and spaces accepted.",
                            "Try again",
                            JOptionPane.ERROR_MESSAGE);
                    typedText = null;
                    textField.requestFocusInWindow();
                }
            } else { //user closed dialog or clicked cancel
                JOptionPane.showMessageDialog(this, "It's OK.  "
                        + "We won't force you to play.");
                typedText = null;
                exit();
            }
        }
    }

    /**
     * This method clears the dialog and hides it.
     */
    public void exit() {
        dispose();
    }
}
