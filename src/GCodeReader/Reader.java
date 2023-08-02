package GCodeReader;

import javax.swing.*;

public class Reader extends JFrame {
    private static final String WINDOW_TITLE = "GCodeReader";
    public Reader() {
        super(WINDOW_TITLE);
        setContentPane(new JPanel());
        pack();
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
