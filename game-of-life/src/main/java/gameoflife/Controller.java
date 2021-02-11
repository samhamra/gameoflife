package gameoflife;

import java.awt.event.ActionEvent;
import java.util.Timer;
import java.util.TimerTask;

 class Controller {
     private final static int DEFAULT_UPDATE_RATE_IN_MS = 400;
     private final int updateRate;
     private final View view;
     private Model model;
     private Timer timer;
     private boolean isRunning = false;

     Controller(Model model, View view, int updateRate) {
        this.model = model;
        this.view = view;
        this.updateRate = updateRate;
        timer = new Timer();
        addEventListeners();
    }

     Controller(Model model, View view) {
         this(model, view, DEFAULT_UPDATE_RATE_IN_MS);
     }

    private void addEventListeners() {
        view.addStopListener(this::stop);
        view.addStartListener(this::start);
        view.addRestartListener(this::restart);
        addGridButtonListeners();
    }

    private void addGridButtonListeners() {
        for (int i = 0; i < model.getRows(); i++) {
            for (int j = 0; j < model.getColumns(); j++) {
                addGridButtonListener(i,j);
            }
        }
    }

    private void addGridButtonListener(int row, int column) {
            view.addGridButtonListener(row, column, e -> {
                model.toggleValueAtPos(row, column);
                view.updateGridButton(row, column, model.getValueAtPos(row, column));
            });
    }

    private void updateView() {
        for (int i = 0; i < model.getRows(); i++) {
            for (int j = 0; j < model.getColumns(); j++) {
                view.updateGridButton(i,j, model.getValueAtPos(i,j));
            }
        }
    }

    private void restart(ActionEvent e) {
        stop(null);
        model = new Model();
        updateView();
     }

    private void stop(ActionEvent e) {
        if(isRunning) {
            timer.cancel();
            timer = new Timer();
            isRunning = false;
        }
    }

    private void start(ActionEvent e) {
        if(!isRunning) {
            isRunning = true;
            timer.scheduleAtFixedRate(new NextGenerationTask(), updateRate, updateRate);
        }
    }

    private class NextGenerationTask extends TimerTask{
        @Override
        public void run() {
            model.nextGeneration();
            updateView();
        }
    }
}