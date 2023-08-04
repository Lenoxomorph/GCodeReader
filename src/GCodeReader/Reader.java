package GCodeReader;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Reader extends JFrame {
    private static final String WINDOW_TITLE = "GCodeReader";
    public Reader() {
        super(WINDOW_TITLE);

        TextDisplay outputArea = new TextDisplay() {{textArea.setEditable(false);}};
        TextDisplay inputArea = new TextDisplay() {
            private void update() {outputArea.setText(textArea.getText());}
            {textArea.getDocument().addDocumentListener(new DocumentListener() {
                @Override
                public void insertUpdate(DocumentEvent e) {update();}
                @Override
                public void removeUpdate(DocumentEvent e) {update();}
                @Override
                public void changedUpdate(DocumentEvent e) {update();}
                });}
        };
        JButton importButton = new JButton("Import") {{
            setFocusable(false);
            setHorizontalAlignment(SwingConstants.RIGHT);
            addActionListener(e -> {
                JFileChooser chooser = new JFileChooser();
                chooser.showOpenDialog(this);
                File file = chooser.getSelectedFile();
                Scanner scanner = null;
                try {
                    scanner = new Scanner(file);
                } catch (FileNotFoundException ex) {
                    ex.printStackTrace();
                }
                assert scanner != null;
                String content = scanner.useDelimiter("\\A").next();
                scanner.close();
                inputArea.setText(content);
            });
        }};



        setContentPane(new JPanel(new BorderLayout()) {{
            add(new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 15)) {{
                add(importButton);
            }}, BorderLayout.PAGE_START);
            add(new JPanel(new BorderLayout()) {{
                add(inputArea, BorderLayout.WEST);
                add(outputArea, BorderLayout.CENTER);
            }}, BorderLayout.CENTER);
        }});

        pack();
        //setSize(new Dimension((2 * WINDOW_GAP) + (5 * (NUM_SIZE + NUM_GAP)) + 38, 300)); // TODO: 8/2/2023
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public static void main(String[] args) {
        new Reader();
    }

    private static class TextDisplay extends JScrollPane {
        public JTextArea textArea;

        public TextDisplay() {
            super(new JTextArea("test"), JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
            textArea = new JTextArea() {{
                setBorder(BorderFactory.createLineBorder(Color.black, 1, true));
                setBackground(Color.decode("#F2FBFF"));
                setLineWrap(false);
            }};
            setViewportView(textArea);
            setPreferredSize(new Dimension(400, 400));
        }

        public void setText(String content) {
            textArea.setText(content);
        }
    }
}
