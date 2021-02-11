package gameoflife;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class View {
    private final static int CELL_SIZE = 16;
    private final int rows;
    private final int columns;
    private JButton startButton;
    private JButton stopButton;
    private JButton restartButton;
    private final JButton[][] gridButtons;

    public View(int rows, int columns) {
         this.rows = rows;
         this.columns = columns;
         gridButtons = new JButton[rows][columns];
         init();
    }

    public void updateGridButton(int row, int column, boolean alive) {
        JButton button = gridButtons[row][column];
        if(alive) {
            button.setBackground(Color.BLACK);
        } else {
            button.setBackground(Color.WHITE);
        }
    }

    public void addStartListener(ActionListener startListener) {
        startButton.addActionListener(startListener);
    }

    public void addStopListener(ActionListener stopListener) {
        stopButton.addActionListener(stopListener);
    }

    public void addRestartListener(ActionListener restartListener) {
        restartButton.addActionListener(restartListener);
    }


    public void addGridButtonListener(int row, int column, ActionListener gridListener) {
        gridButtons[row][column].addActionListener(gridListener);
    }


    private void init() {
        try {
            UIManager.setLookAndFeel( UIManager.getCrossPlatformLookAndFeelClassName() );
        } catch (Exception e) {
            e.printStackTrace();
        }
        JFrame frame = new JFrame("Game of Life by Sam Hamra");
        frame.setPreferredSize(new Dimension(((columns+2)*CELL_SIZE), (rows*CELL_SIZE)+135));
        frame.setResizable(false);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel main = new JPanel();
        main.setBackground(new Color(192,192,192));
        JPanel buttonPanel = createButtonPanel();
        JPanel grid = createGrid();
        setupGridButtons(grid);
        JSplitPane sp = new JSplitPane(JSplitPane.VERTICAL_SPLIT, grid, buttonPanel);
        sp.setDividerSize(1);
        main.add(sp);
        frame.setContentPane(main);
        frame.setVisible(true);
    }

    private void setupGridButtons(JPanel grid) {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                JButton button = new JButton();
                button.setBackground(Color.WHITE);
                gridButtons[i][j] = button;
                grid.add(button);
            }
        }
    }

    private JPanel createGrid() {
        JPanel grid = new JPanel();
        grid.setLayout(new GridLayout(rows, columns));
        grid.setPreferredSize(new Dimension (columns*CELL_SIZE, rows*CELL_SIZE));
        return grid;
    }

    private JPanel createButtonPanel() {
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(Color.LIGHT_GRAY);
        startButton = new JButton("Start");
        startButton.setPreferredSize(new Dimension(80, 30));
        buttonPanel.add(startButton);
        stopButton = new JButton("Stop");
        stopButton.setPreferredSize(new Dimension(80, 30));
        buttonPanel.add(stopButton);
        restartButton = new JButton("Restart");
        restartButton.setPreferredSize(new Dimension(80, 30));
        buttonPanel.add(restartButton);
        return buttonPanel;
    }
}


