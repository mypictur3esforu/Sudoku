import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class UI {
    private JFrame frame;
    private JPanel sudokuCreator;
    private Grid emptyGrid;

    UI(){
        frame = createFrame();
        sudokuCreator = createSudokuCreator();
        frame.add(sudokuCreator);
        frame.setVisible(true);
    }

    private JFrame createFrame(){
        JFrame frame = new JFrame();
        frame.setExtendedState(Frame.MAXIMIZED_BOTH);
        frame.setBackground(Color.BLACK);
        frame.setLayout(new GridLayout(1, 1));
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
        return frame;
    }

    private JPanel createSudokuCreator() {
        JPanel sudCreator = new JPanel(new GridLayout(1, 1)), grid = new Grid().ui;
        sudCreator.add(grid);
        return sudCreator;
    }


    

}
