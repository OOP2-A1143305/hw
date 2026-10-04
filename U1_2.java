import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class U1_2 extends JFrame {
    private JComboBox<String> typeComboBox;
    private JTextField inputField;
    private JTextField outputField;
    private JComboBox<String> sourceUnitComboBox;
    private JComboBox<String> targetUnitComboBox;
    private JButton convertButton;

    private final String[] lengthUnits = {"公尺", "公分", "英寸", "英尺"};
    private final String[] weightUnits = {"公斤", "公克", "磅", "盎司"};
    private final String[] tempUnits = {"攝氏", "華氏", "克氏"};

    public U1_2() {
        setTitle("單位換算器");
        setSize(480, 280);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        typeComboBox = new JComboBox<>(new String[]{"長度", "重量", "溫度"});
        typeComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateUnitOptions();
            }
        });
        add(typeComboBox, BorderLayout.NORTH);

        JPanel centerPanel = new JPanel(new GridLayout(2, 2, 10, 10));
        centerPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        inputField = new JTextField();
        outputField = new JTextField();
        outputField.setEditable(false);

        sourceUnitComboBox = new JComboBox<>();
        targetUnitComboBox = new JComboBox<>();

        centerPanel.add(inputField);
        centerPanel.add(sourceUnitComboBox);
        centerPanel.add(outputField);
        centerPanel.add(targetUnitComboBox);

        add(centerPanel, BorderLayout.CENTER);

        convertButton = new JButton("換算");
        convertButton.setFont(new Font("SansSerif", Font.BOLD, 16));
        convertButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                performConversion();
            }
        });

        JPanel southPanel = new JPanel();
        southPanel.add(convertButton);
        add(southPanel, BorderLayout.SOUTH);

        updateUnitOptions();
    }

    private void updateUnitOptions() {
        String selectedType = (String) typeComboBox.getSelectedItem();
        sourceUnitComboBox.removeAllItems();
        targetUnitComboBox.removeAllItems();

        String[] units;
        if ("長度".equals(selectedType)) {
            units = lengthUnits;
        } else if ("重量".equals(selectedType)) {
            units = weightUnits;
        } else {
            units = tempUnits;
        }

        for (String unit : units) {
            sourceUnitComboBox.addItem(unit);
            targetUnitComboBox.addItem(unit);
        }
    }

    private void performConversion() {
        String inputText = inputField.getText().trim();
        if (inputText.isEmpty()) {
            outputField.setText("");
            return;
        }

        double val;
        try {
            val = Double.parseDouble(inputText);
        } catch (NumberFormatException ex) {
            outputField.setText("輸入錯誤");
            return;
        }

        String type = (String) typeComboBox.getSelectedItem();
        String src = (String) sourceUnitComboBox.getSelectedItem();
        String tgt = (String) targetUnitComboBox.getSelectedItem();

        if (src == null || tgt == null) return;

        double result = 0.0;

        if ("長度".equals(type)) {
            double meters = toMeters(val, src);
            result = fromMeters(meters, tgt);
        } else if ("重量".equals(type)) {
            double grams = toGrams(val, src);
            result = fromGrams(grams, tgt);
        } else if ("溫度".equals(type)) {
            result = convertTemperature(val, src, tgt);
        }

        outputField.setText(String.format("%.4f", result));
    }

    private double toMeters(double val, String unit) {
        switch (unit) {
            case "公尺": return val;
            case "公分": return val / 100.0;
            case "英寸": return val * 0.0254;
            case "英尺": return val * 0.3048;
            default: return val;
        }
    }

    private double fromMeters(double meters, String unit) {
        switch (unit) {
            case "公尺": return meters;
            case "公分": return meters * 100.0;
            case "英寸": return meters / 0.0254;
            case "英尺": return meters / 0.3048;
            default: return meters;
        }
    }

    private double toGrams(double val, String unit) {
        switch (unit) {
            case "公斤": return val * 1000.0;
            case "公克": return val;
            case "磅": return val * 453.59237;
            case "盎司": return val * 28.349523125;
            default: return val;
        }
    }

    private double fromGrams(double grams, String unit) {
        switch (unit) {
            case "公斤": return grams / 1000.0;
            case "公克": return grams;
            case "磅": return grams / 453.59237;
            case "盎司": return grams / 28.349523125;
            default: return grams;
        }
    }

    private double convertTemperature(double val, String src, String tgt) {
        double celsius;
        switch (src) {
            case "攝氏": celsius = val; break;
            case "華氏": celsius = (val - 32) * 5.0 / 9.0; break;
            case "克氏": celsius = val - 273.15; break;
            default: celsius = val; break;
        }

        switch (tgt) {
            case "攝氏": return celsius;
            case "華氏": return celsius * 9.0 / 5.0 + 32;
            case "克氏": return celsius + 273.15;
            default: return celsius;
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new U1_2().setVisible(true);
            }
        });
    }
}
