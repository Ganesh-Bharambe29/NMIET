import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.util.ArrayList;

public class AfflineTransformDemo extends JFrame {
    private DrawPanel drawPanel;

    // Transformation parameters
    private double translateX = 0, translateY = 0;
    private double scaleX = 1.0, scaleY = 1.0;
    private double rotateTheta = 0;        // in degrees
    private double shearX = 0, shearY = 0;

    public AfflineTransformDemo() {
        setTitle("Affine Transformations Demo");
        setSize(800, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        drawPanel = new DrawPanel();
        add(drawPanel, BorderLayout.CENTER);

        // Control panel with sliders / inputs
        JPanel control = new JPanel(new GridLayout(0, 2));
        control.setPreferredSize(new Dimension(200, 0));

        // Translation inputs
        control.add(new JLabel("Translate X:"));
        JTextField txField = new JTextField("0");
        control.add(txField);
        control.add(new JLabel("Translate Y:"));
        JTextField tyField = new JTextField("0");
        control.add(tyField);

        // Scaling
        control.add(new JLabel("Scale X:"));
        JTextField sxField = new JTextField("1");
        control.add(sxField);
        control.add(new JLabel("Scale Y:"));
        JTextField syField = new JTextField("1");
        control.add(syField);

        // Rotation
        control.add(new JLabel("Rotate (deg):"));
        JTextField rotField = new JTextField("0");
        control.add(rotField);

        // Shear
        control.add(new JLabel("Shear X:"));
        JTextField shxField = new JTextField("0");
        control.add(shxField);
        control.add(new JLabel("Shear Y:"));
        JTextField shyField = new JTextField("0");
        control.add(shyField);

        JButton applyBtn = new JButton("Apply");
        JButton resetBtn = new JButton("Reset");
        control.add(applyBtn);
        control.add(resetBtn);

        add(control, BorderLayout.EAST);

        applyBtn.addActionListener(e -> {
            try {
                translateX = Double.parseDouble(txField.getText());
                translateY = Double.parseDouble(tyField.getText());
                scaleX = Double.parseDouble(sxField.getText());
                scaleY = Double.parseDouble(syField.getText());
                rotateTheta = Double.parseDouble(rotField.getText());
                shearX = Double.parseDouble(shxField.getText());
                shearY = Double.parseDouble(shyField.getText());
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Invalid number input");
            }
            drawPanel.repaint();
        });

        resetBtn.addActionListener(e -> {
            translateX = 0; translateY = 0;
            scaleX = 1; scaleY = 1;
            rotateTheta = 0;
            shearX = 0; shearY = 0;
            txField.setText("0");
            tyField.setText("0");
            sxField.setText("1");
            syField.setText("1");
            rotField.setText("0");
            shxField.setText("0");
            shyField.setText("0");
            drawPanel.repaint();
        });
    }

    class DrawPanel extends JPanel {
        // Base shape: triangle
        private Point2D[] basePts = {
            new Point2D.Double(-50, -50),
            new Point2D.Double(50, -50),
            new Point2D.Double(0, 50),
        };

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            setBackground(Color.WHITE);

            Graphics2D g2 = (Graphics2D) g;
            g2.translate(getWidth()/2, getHeight()/2);  // center origin
            g2.setStroke(new BasicStroke(2));
            g2.setColor(Color.GRAY);
            // draw axes
            g2.drawLine(-getWidth()/2, 0, getWidth()/2, 0);
            g2.drawLine(0, -getHeight()/2, 0, getHeight()/2);

            // Construct the affine transformation
            AffineTransform at = new AffineTransform();

            // Order of transforms: shear -> scale -> rotate -> translate
            at.shear(shearX, shearY);
            at.scale(scaleX, scaleY);
            at.rotate(Math.toRadians(rotateTheta));
            at.translate(translateX, translateY);

            // Apply transform and draw
            Path2D path = new Path2D.Double();
            Point2D p0 = at.transform(basePts[0], null);
            path.moveTo(p0.getX(), p0.getY());
            for (int i = 1; i < basePts.length; i++) {
                Point2D pi = at.transform(basePts[i], null);
                path.lineTo(pi.getX(), pi.getY());
            }
            path.closePath();

            g2.setColor(Color.BLUE);
            g2.draw(path);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new AfflineTransformDemo().setVisible(true);
        });
    }
}
