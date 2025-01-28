import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class Grid {
    private JButton[][] gridButs = new JButton[9][9];
    private int[][][] gridValues = new int[9][9][];
    private JPanel ui;
    private int[] sB = new int[]{-1,-1};
    private Color gridColor = new Color(0xFFFFFF), borderColor = new Color(0x000000), numbersColor = new Color(0x98D1CB),
            selectedFieldColor = new Color(0x20FDC5), selectedCRs = new Color(0xFF80D2FF, true);

    public JPanel getUI(){return ui;}

    Grid() {
        ui = gridUI();
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                //erste 0 für Wert, zweite für ausgewählt oder nicht
                gridValues[j][i] = new int[]{0, 0};
            }
        }
        deselectGrid();
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
                b = buttonBorder(b, i, j);
                b.setBackground(null);
                gridPart.add(b);
                b.addActionListener(e -> {
                    gridPressed(finalJ, finalI);
                });
                b.setFont(new Font("Arial", Font.PLAIN, 40));
                this.gridButs[j][i] = b;
            }
            grid.add(gridPart);
            JButton numberB = new JButton(i + 1 + "");
            numberB.setBackground(numbersColor);
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

    private JButton buttonBorder(JButton b, int i, int j){
        int top = 1, bottom = 1, right = 1, left = 1;
        if ((j + 1) % 3 == 0) right = 4;
        if (j == 0) left = 6;
        if (j == 8) right = 6;
        if ((i+1) % 3 == 0) bottom = 4;
        if (i == 0) top = 6;
        if (i == 8) bottom = 6;
        b.setBorder(BorderFactory.createMatteBorder(top, left, bottom, right, borderColor));
        return b;
    }

    private void gridPressed(int x, int y) {
        boolean deselect = gridValues[x][y][1] == 1;
        deselectGrid();
        if (deselect) return;
        for (int i = 0; i < gridButs.length; i++) {
            for (int j = 0; j < gridButs[i].length; j++) {
                if (i == x || j == y) gridButs[i][j].setBackground(selectedFieldColor);
            }
        }
        gridButs[x][y].setBackground(selectedCRs);
        gridValues[x][y][1] = 1;
        sB = new int[]{x,y};
    }

    private void deselectGrid(){
        sB = new int[]{-1};
        for (int i = 0; i < gridButs.length; i++) {
            for (int j = 0; j < gridButs[i].length; j++) {
                gridButs[i][j].setBackground(gridColor);
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
