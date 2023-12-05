package dev.parking;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.util.List;
import java.util.*;

public class ParkingGui extends JFrame implements ParkingStatusListener {

    private JLabel capacityLabel;
    private JLabel freePlacesLabel;
    private CarTableModel carTableModel;
    private final ParkingManager parkingManager;

    public ParkingGui(ParkingManager parkingManager) {
        this.parkingManager = parkingManager;
        initializeComponents();
        updateDisplay();
    }

    private void initializeComponents() {
        this.setTitle("Parking Application");
        capacityLabel = new JLabel();
        freePlacesLabel = new JLabel();
        carTableModel = new CarTableModel(parkingManager.getCars());
        JTable carsTable = new JTable(carTableModel);
        carsTable.setDefaultRenderer(Object.class, new CarStatusCellRenderer());

        setLayout(new BorderLayout());

        JPanel labelsPanel = new JPanel(new GridLayout(1, 2));
        labelsPanel.add(capacityLabel);
        labelsPanel.add(freePlacesLabel);

        setLayout(new BorderLayout());
        add(labelsPanel, BorderLayout.NORTH);
        add(new JScrollPane(carsTable), BorderLayout.CENTER);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setVisible(true);
    }

    @Override
    public void onStatusChange() {
        updateDisplay();
    }

    public void updateDisplay() {
        SwingUtilities.invokeLater(() -> {
            capacityLabel.setText("Capacity: " + parkingManager.getParking().getCapacity());
            freePlacesLabel.setText("Free Places: " + parkingManager.getParking().getFreePlaces());
            Set<Car> carSet = new HashSet<>();
            carSet.addAll(parkingManager.getCars().get(CarStatus.AWAITS_ENTRY));
            carSet.addAll(parkingManager.getCars().get(CarStatus.ON_PARKING_SLOT));
            carSet.addAll(parkingManager.getCars().get(CarStatus.EXITING));
            carTableModel.cars = new ArrayList<>(carSet);
            carTableModel.fireTableDataChanged();
        });
    }

    private static class CarTableModel extends AbstractTableModel {
        private final String[] columnNames = {"Car Name", "Status"};
        private List<Car> cars;

        public CarTableModel(Map<CarStatus, List<Car>> carMap) {
            List<Car> cars = carMap.get(CarStatus.AWAITS_ENTRY);
            cars.addAll(carMap.get(CarStatus.ON_PARKING_SLOT));
            cars.addAll(carMap.get(CarStatus.EXITING));
            cars.stream().forEach(System.out::println);
            this.cars = cars;
        }

        @Override
        public int getRowCount() {
            return cars.size();
        }

        @Override
        public int getColumnCount() {
            return columnNames.length;
        }

        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
            Car car = cars.get(rowIndex);
            return switch (columnIndex) {
                case 0 -> car.getCarName();
                case 1 -> car.getStatus().toString();
                default -> "";
            };
        }

        @Override
        public String getColumnName(int column) {
            return columnNames[column];
        }
    }

    private static class CarStatusCellRenderer extends DefaultTableCellRenderer {
        @Override
        public Component getTableCellRendererComponent(JTable table, Object value,
                                                       boolean isSelected, boolean hasFocus,
                                                       int row, int column) {
            Component component = super.getTableCellRendererComponent(
                    table, value, isSelected, hasFocus, row, column
            );

            CarTableModel model = (CarTableModel) table.getModel();
            Car car = model.cars.get(row);

            switch (car.getStatus()) {
                case AWAITS_ENTRY -> component.setBackground(Color.YELLOW);
                case ON_PARKING_SLOT -> component.setBackground(Color.GREEN);
                case EXITING -> component.setBackground(Color.GRAY);
                default -> component.setBackground(table.getBackground());
            }

            return component;
        }
    }
}
