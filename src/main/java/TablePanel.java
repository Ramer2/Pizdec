import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.Arrays;

public class TablePanel extends JPanel {
    private static TablePanel instance;

    private JTable eulerTable;
    private JTable midpointTable;

    private DefaultTableModel eulerTableModel;
    private DefaultTableModel midpointTableModel;

    private TablePanel() {
        setBackground(Color.WHITE);
        setBorder(BorderFactory.createMatteBorder(3, 3, 3, 0, Color.BLACK));
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1;
        gbc.weighty = 1;

        // Euler's Method Table
        String[] columnNames = {"t", "Sx", "Sy", "Vx", "Vy", "dSx", "dSy", "dVx", "dVy"};
        eulerTableModel = new DefaultTableModel(columnNames, 0);
        eulerTable = new JTable(eulerTableModel);
//        eulerTableModel.addRow(new Object[]{0, 0, 0, 0, 0, 0, 0, 0});
        JScrollPane eulerScrollPane = new JScrollPane(eulerTable);

        JPanel eulerPanel = new JPanel(new BorderLayout());
        eulerPanel.setBorder(BorderFactory.createTitledBorder("Euler's Method"));
        eulerPanel.add(eulerScrollPane, BorderLayout.CENTER);
//        eulerPanel.add(eulerTable, BorderLayout.CENTER);
        eulerPanel.setBackground(Color.WHITE);

        gbc.gridx = 0;
        gbc.gridy = 0;
        add(eulerPanel, gbc);

        // Midpoint Method Table
        midpointTableModel = new DefaultTableModel(columnNames, 0);
        midpointTable = new JTable(midpointTableModel);
        JScrollPane midpointScrollPane = new JScrollPane(midpointTable);

        JPanel midpointPanel = new JPanel(new BorderLayout());
        midpointPanel.setBorder(BorderFactory.createTitledBorder("Midpoint Method"));
        midpointPanel.add(midpointScrollPane, BorderLayout.CENTER);
//        midpointPanel.add(midpointTable, BorderLayout.CENTER);
        midpointPanel.setBackground(Color.WHITE);

        gbc.gridy = 1;
        add(midpointPanel, gbc);
    }

    public void addEulerRow(Object[] row) {
//        System.out.println(Arrays.toString(row));
        eulerTableModel.addRow(row);
        System.out.println(eulerTableModel.getDataVector());



//        System.out.println(eulerTableModel.getRowCount());
//        System.out.println(eulerTableModel.equals(eulerTable.getModel()));

//        eulerTable.setModel(eulerTableModel);
        eulerTable = new JTable(eulerTableModel);

        eulerTable.repaint();
        eulerTable.revalidate();
    }

    public void addMidpointRow(Object[] row) {
        SwingUtilities.invokeLater(() -> {
            midpointTableModel.addRow(row);
            midpointTable.repaint();
            midpointTable.revalidate();
        });
    }

    public void reset() {
        eulerTableModel.setRowCount(0);
        midpointTableModel.setRowCount(0);
    }

    public static TablePanel getInstance() {
        if (instance == null)
            instance = new TablePanel();

        return instance;
    }
}
