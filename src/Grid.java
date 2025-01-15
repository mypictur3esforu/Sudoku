import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class Grid {
    private JButton[][] gridButs = new JButton[9][9];
    private int[][][] gridValues = new int[9][9][];
    public JPanel ui;
    private int[] sB = new int[]{-1,-1};

    Grid() {
        ui = gridUI();
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                //erste 0 für Wert, zweite für ausgewählt oder nicht
                gridValues[j][i] = new int[]{0, 0};
            }
        }
    }

    private JPanel gridUI() {
        JPanel emptGrid = new JPanel(new GridLayout(1, 2)), grid = new JPanel(new GridLayout(9, 1)),
                numbers = new JPanel(new GridLayout(3, 3));
        for (int i = 0; i < 9; i++) {
            int finalI = i;
            JPanel gridPart = new JPanel(new GridLayout(1, 9));
            for (int j = 0; j < 9; j++) {
                int finalJ = j;
                JButton b = new JButton();
                b.setBackground(null);
                b.setBackground(Color.PINK);
                gridPart.add(b);
                b.addActionListener(e -> {
                    gridPressed(finalJ, finalI);
                });
                b.setFont(new Font("Aria", Font.PLAIN, 20));
                this.gridButs[j][i] = b;
            }
            grid.add(gridPart);
            JButton numberB = new JButton(i + 1 + "");
            numberB.setBackground(new Color(0x838383));
            numberB.setFont(new Font("Arial", Font.PLAIN, 50));
            numberB.setBorder(new LineBorder(Color.black, 2));
            numberB.addActionListener(e -> {
                placeNumber(finalI + 1);
            });
            numbers.add(numberB);
        }
        emptGrid.setBackground(Color.gray);
        emptGrid.add(grid);
        emptGrid.add(numbers);
        return emptGrid;
    }

    private void gridPressed(int x, int y) {
        boolean deselect = gridValues[x][y][1] == 1;
        deselectGrid();
        if (deselect) return;
        for (int i = 0; i < gridButs.length; i++) {
            for (int j = 0; j < gridButs[i].length; j++) {
                if (i == x || j == y) gridButs[i][j].setBackground(new Color(0xFF6E3A9C, true));
            }
        }
        gridButs[x][y].setBackground(new Color(0x20FDC5));
        gridValues[x][y][1] = 1;
        sB = new int[]{x,y};
    }

    private void deselectGrid(){
        sB = new int[]{-1};
        for (int i = 0; i < gridButs.length; i++) {
            for (int j = 0; j < gridButs[i].length; j++) {
                gridButs[i][j].setBackground(Color.PINK);
                gridValues[i][j][1] = 0;
            }
        }
    }

    private void placeNumber(int number) {
        if (sB[0] == -1) return;
        gridValues[sB[0]][sB[1]][0] = number;
        gridButs[sB[0]][sB[1]].setText(number + "");
        deselectGrid();
    }
}
