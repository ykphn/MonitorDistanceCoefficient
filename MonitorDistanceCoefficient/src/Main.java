import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import javax.swing.*;

public class Main {
    private static double result;
    private static String fov_1s;
    private static String fov_2s;
    private static String monitorMatch_S;
    private static String sensitivity_S;
    private static String result_S;

    static class CustomWindowListener implements WindowListener {

        public void windowOpened(WindowEvent e) {}

        public void windowClosing(WindowEvent e) {
            System.exit(0);
        }

        public void windowClosed(WindowEvent e) {}

        public void windowIconified(WindowEvent e) {}

        public void windowDeiconified(WindowEvent e) {}

        public void windowActivated(WindowEvent e) {}

        public void windowDeactivated(WindowEvent e) {}
    }

    static double calc(double fov, double monitorMatch) {
        return Math.atan(monitorMatch/100 * Math.tan(fov / 2 * Math.PI/180));
    }

    public static void main(String[] args) {

        JFrame f = new JFrame();

        JLabel lBaslik1 = new JLabel("Sensitivity Converter by", SwingConstants.CENTER);
        lBaslik1.setBounds(30,30 , 300, 30);
        JLabel lBaslik2 = new JLabel("Monitor Distance Coefficient", SwingConstants.CENTER);
        lBaslik2.setBounds(30,50 , 300, 30);

        JLabel lFov1 = new JLabel("FOV 1:", SwingConstants.CENTER);
        lFov1.setBounds(80,100 , 90, 35);
        JTextField jFov1 = new JTextField();
        jFov1.setBounds(180, 100, 100, 35);

        JLabel lFov2 = new JLabel("FOV 2:", SwingConstants.CENTER);
        lFov2.setBounds(80, 150, 90 ,35);
        JTextField jFov2 = new JTextField();
        jFov2.setBounds(180, 150, 100, 35);

        JLabel lMonitorMatch = new JLabel("Monitor Match:", SwingConstants.CENTER);
        lMonitorMatch.setBounds(80,200 , 90, 35);
        JTextField jMonitorMatch = new JTextField();
        jMonitorMatch.setBounds(180, 200, 100, 35);

        JLabel lSensitivity = new JLabel("Sensitivity:", SwingConstants.CENTER);
        lSensitivity.setBounds(80, 250, 90 ,35);
        JTextField jSensitivity = new JTextField();
        jSensitivity.setBounds(180, 250, 100, 35);

        JButton bCalculate = new JButton("Calculate");
        bCalculate.setBounds(80, 300, 180, 35);
        bCalculate.setHorizontalTextPosition(SwingConstants.CENTER);

        bCalculate.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                fov_1s = jFov1.getText();
                fov_2s = jFov2.getText();
                monitorMatch_S = jMonitorMatch.getText();
                sensitivity_S = jSensitivity.getText();

                if (fov_1s.equals("") || fov_2s.equals("") || monitorMatch_S.equals("") || sensitivity_S.equals("")) {
                    JOptionPane.showMessageDialog(null, "Boxes cannot be empty");
                    return;
                }

                try {
                    result = Double.parseDouble(sensitivity_S) *
                            calc(Double.parseDouble(fov_2s), Double.parseDouble(monitorMatch_S))/
                            calc(Double.parseDouble(fov_1s), Double.parseDouble(monitorMatch_S));
                    result_S = "New Sensitivity: " + result;
                    JOptionPane.showMessageDialog(null, result_S);

                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Boxes must be equal to number");
                }

            }
        });

        f.add(lBaslik1);
        f.add(lBaslik2);
        f.add(lFov1);
        f.add(lFov2);
        f.add(jFov1);
        f.add(jFov2);
        f.add(lMonitorMatch);
        f.add(jMonitorMatch);
        f.add(lSensitivity);
        f.add(jSensitivity);
        f.add(bCalculate);
        f.addWindowListener(new CustomWindowListener());
        f.setSize(360, 400);
        f.setLayout((LayoutManager)null);
        f.setVisible(true);
    }
}
