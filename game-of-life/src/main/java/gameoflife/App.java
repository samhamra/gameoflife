package gameoflife;

public class App {
    public static void main( String[] args ) {
        Model model = new Model();
        View view = new View(Model.DEFAULT_ROWS, Model.DEFAULT_COLUMNS);
        new Controller(model, view);
    }

}