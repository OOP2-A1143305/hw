import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class U2_2 extends JFrame {

    private JComboBox<String> typeComboBox;
    private JTextField inputTextField;
    private JTextField outputTextField;
    private JComboBox<String> sourceUnitComboBox;
    private JComboBox<String> targetUnitComboBox;
    private JButton convertButton;

    private String[] lengthUnits = {"公尺", "公分", "英吋", "英尺"};
    private String[] weightUnits = {"公斤", "公克", "磅", "盎司"};
    private String[] tempUnits = {"攝氏", "華氏", "克氏"};

    public U2_2() {
        setTitle("單位換算器");
        setSize(480, 280);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        String[] types = {"長度", "重量", "溫度"};
        typeComboBox = new JComboBox<>(types);
        add(typeComboBox, BorderLayout.NORTH);

        JPanel centerPanel = new JPanel(new GridLayout(2, 1, 5, 5));

        JPanel row1 = new JPanel(new FlowLayout());
        inputTextField = new JTextField(15);
        sourceUnitComboBox = new JComboBox<>(lengthUnits);
        row1.add(inputTextField);
        row1.add(sourceUnitComboBox);

        JPanel row2 = new JPanel(new FlowLayout());
        outputTextField = new JTextField(15);
        outputTextField.setEditable(false);
        targetUnitComboBox = new JComboBox<>(lengthUnits);
        row2.add(outputTextField);
        row2.add(targetUnitComboBox);

        centerPanel.add(row1);
        centerPanel.add(row2);
        add(centerPanel, BorderLayout.CENTER);

        convertButton = new JButton("換算");
        add(convertButton, BorderLayout.SOUTH);

        typeComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateUnitComboBoxes();
            }
        });

        convertButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                performConversion();
            }
        });

        setLocationRelativeTo(null);
    }

    private void updateUnitComboBoxes() {
        String selectedType = (String) typeComboBox.getSelectedItem();
        sourceUnitComboBox.removeAllItems();
        targetUnitComboBox.removeAllItems();

        String[] currentUnits;
        if ("長度".equals(selectedType)) {
            currentUnits = lengthUnits;
        } else if ("重量".equals(selectedType)) {
            currentUnits = weightUnits;
        } else {
            currentUnits = tempUnits;
        }

        for (String unit : currentUnits) {
            sourceUnitComboBox.addItem(unit);
            targetUnitComboBox.addItem(unit);
        }
    }

    private void performConversion() {
        try {
            double inputValue = Double.parseDouble(inputTextField.getText().trim());
            String type = (String) typeComboBox.getSelectedItem();
            String fromUnit = (String) sourceUnitComboBox.getSelectedItem();
            String toUnit = (String) targetUnitComboBox.getSelectedItem();

            double result = 0;

            if ("長度".equals(type)) {
                result = convertLength(inputValue, fromUnit, toUnit);
            } else if ("重量".equals(type)) {
                result = convertWeight(inputValue, fromUnit, toUnit);
            } else if ("溫度".equals(type)) {
                result = convertTemperature(inputValue, fromUnit, toUnit);
            }

            outputTextField.setText(String.format("%.4f", result));
        } catch (NumberFormatException ex) {
            outputTextField.setText("錯誤輸入");
        }
    }

    private double convertLength(double val, String from, String to) {
        double meters = 0;
        switch (from) {
            case "公尺": meters = val; break;
            case "公分": meters = val / 100.0; break;
            case "英吋": meters = val * 0.0254; break;
            case "英尺": meters = val * 0.3048; break;
        }

        switch (to) {
            case "公尺": return meters;
            case "公分": return meters * 100.0;
            case "英吋": return meters / 0.0254;
            case "英尺": return meters / 0.3048;
            default: return 0;
        }
    }

    private double convertWeight(double val, String from, String to) {
        double kg = 0;
        switch (from) {
            case "公斤": kg = val; break;
            case "公克": kg = val / 1000.0; break;
            case "磅": kg = val * 0.45359237; break;
            case "盎司": kg = val * 0.0283495231; break;
        }

        switch (to) {
            case "公斤": return kg;
            case "公克": return kg * 1000.0;
            case "磅": return kg / 0.45359237;
            case "盎司": return kg / 0.0283495231;
            default: return 0;
        }
    }

    private double convertTemperature(double val, String from, String to) {
        double celsius = 0;
        switch (from) {
            case "攝氏": celsius = val; break;
            case "華氏": celsius = (val - 32) * 5 / 9; break;
            case "克氏": celsius = val - 273.15; break;
        }

        switch (to) {
            case "攝氏": return celsius;
            case "華氏": return celsius * 9 / 5 + 32;
            case "克氏": return celsius + 273.15;
            default: return 0;
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new U2_2().setVisible(true);
            }
        });
    }
}
