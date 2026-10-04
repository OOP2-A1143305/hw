import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class U1_1 extends JFrame {
    private int count = 0;
    private int sum = 0;
    private Random random;

    private JLabel infoLabel;
    private JLabel diceLabel;
    private JButton rollButton;

    public U1_1() {
        setTitle("骰子模擬器");
        setSize(400, 320);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        random = new Random();

        infoLabel = new JLabel("已擲 0 次，總和 0，平均 0.00", SwingConstants.CENTER);
        infoLabel.setFont(new Font("SansSerif", Font.PLAIN, 16));
        infoLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        add(infoLabel, BorderLayout.NORTH);

        diceLabel = new JLabel("-", SwingConstants.CENTER);
        diceLabel.setFont(new Font("Serif", Font.BOLD, 60));
        diceLabel.setForeground(Color.BLACK);
        add(diceLabel, BorderLayout.CENTER);

        rollButton = new JButton("擲骰子");
        rollButton.setFont(new Font("SansSerif", Font.PLAIN, 18));
        
        rollButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                rollDice();
            }
        });
        
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(rollButton);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    private void rollDice() {
        int diceValue = random.nextInt(6) + 1;

        count++;
        sum += diceValue;
        double average = (double) sum / count;

        diceLabel.setText(String.valueOf(diceValue));

        if (diceValue == 6) {
            diceLabel.setForeground(Color.GREEN);
        } else if (diceValue == 1) {
            diceLabel.setForeground(Color.RED);
        } else {
            diceLabel.setForeground(Color.BLACK);
        }

        infoLabel.setText(String.format("已擲 %d 次，總和 %d，平均 %.2f", count, sum, average));
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new U1_1().setVisible(true);
            }
        });
    }
}
