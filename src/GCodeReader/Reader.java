package GCodeReader;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Comparator;
import java.util.Scanner;

public class Reader extends JFrame {
    private static final String WINDOW_TITLE = "GCodeReader";
    public Reader() {
        super(WINDOW_TITLE);

        JTextArea outputArea = new JTextArea() {{
            setEditable(false);
            setBorder(BorderFactory.createLineBorder(Color.black, 1, true));
            setBackground(Color.decode("#f2fbff"));
            setLineWrap(true);
        }};
        JTextArea inputArea = new JTextArea() {
            private void update() {
                outputArea.setText(getText());
            }
            {
            setBorder(BorderFactory.createLineBorder(Color.black, 1, true));
            setBackground(Color.decode("#f2fbff"));
            setLineWrap(true);
            getDocument().addDocumentListener(new DocumentListener() {
                @Override
                public void insertUpdate(DocumentEvent e) {update();}
                @Override
                public void removeUpdate(DocumentEvent e) {update();}
                @Override
                public void changedUpdate(DocumentEvent e) {update();}
            });
        }};
        JButton importButton = new JButton("Import") {{
            setFocusable(false);
            setHorizontalAlignment(SwingConstants.RIGHT);
            addActionListener(e -> {
                JFileChooser chooser = new JFileChooser();
                chooser.showOpenDialog(this);
                File file = chooser.getSelectedFile();
                System.out.println(file);
            });
        }};



        setContentPane(new JPanel(new BorderLayout()) {{
            add(new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 15)) {{
                add(importButton);
            }}, BorderLayout.PAGE_START);
            add(new JPanel() {{
                add(inputArea);
                add(outputArea);
            }}, BorderLayout.CENTER);
        }});

        pack();
        //setSize(new Dimension((2 * WINDOW_GAP) + (5 * (NUM_SIZE + NUM_GAP)) + 38, 300)); // TODO: 8/2/2023
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public static void main(String[] args) {
        Reader r = new Reader();
    }

    private class SubPanel extends JPanel {
        public SubPanel() {

        }
    }
}
