package gameoflife;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class View implements ActionListener {
    private JFrame frame;
    private Model model;
    public View(Model model) {
        this.model = model;
        setup();
    }

    private void setup() {
        frame = new JFrame("Game of Life by Sam Hamra");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(((model.getColumns()+2)*16), (model.getRows()*16)+135)); // the pictures are 16*16
        frame.setResizable(false);
        JPanel contentPane = new JPanel();
        contentPane.setBackground(new Color(195, 195, 195));
        JPanel top = new JPanel();
        top.setBackground(new Color(195, 195, 195));
        JPanel gridPanel = new JPanel();
        GridLayout grid = new GridLayout(model.getRows(), model.getColumns());
        gridPanel.setLayout(grid);
        gridPanel.setPreferredSize(new Dimension (model.getColumns()*16, model.getRows()*16)); //tile size 16*16 imported from picture
        JButton startButton = new JButton("Start");
        startButton.setPreferredSize(new Dimension(52, 52));
        top.add(startButton);
        startButton.addActionListener(this);
        JSplitPane sp = new JSplitPane(JSplitPane.VERTICAL_SPLIT, top, gridPanel); // lazy
        sp.setDividerSize(1);
        contentPane.add(sp);

        frame.setContentPane(contentPane);
        frame.pack();
        frame.setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println(e.getSource());
    }
}
