import javax.swing.*;

public class U1_0 extends JFrame {
    public U1_0() {
        setTitle("登入");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        JLabel l1 = new JLabel("帳號:");
        l1.setBounds(30, 30, 50, 20);
        JTextField t1 = new JTextField();
        t1.setBounds(90, 30, 120, 20);

        JLabel l2 = new JLabel("密碼:");
        l2.setBounds(30, 70, 50, 20);
        JPasswordField t2 = new JPasswordField();
        t2.setBounds(90, 70, 120, 20);

        JButton btn = new JButton("登入");
        btn.setBounds(90, 110, 80, 30);

        add(l1);
        add(t1);
        add(l2);
        add(t2);
        add(btn);

        btn.addActionListener(e -> {
            String username = t1.getText();
            String password = new String(t2.getPassword());
            if (username.equals("admin") && password.equals("")) {
                System.out.println("登入成功");
            } else {
                System.out.println("登入失敗");
            }
        });

        setVisible(true);
    }

    public static void main(String[] args) {
        new U1_0();
    }
}