import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

// Class representing a GPS point
class GPSPoint 
{
    int x, y;
    GPSPoint(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

// Main drawing panel
class MapPanel extends JPanel {
    private List<GPSPoint[]> roads;    // List of road segments (lines)
    private List<GPSPoint> circles;    // List of circle centers
    private List<Integer> radii;       // Corresponding circle radii

    public MapPanel() {
        roads = new ArrayList<>();
        circles = new ArrayList<>();
        radii = new ArrayList<>();

        // Example roads (as line segments between GPS points)
        roads.add(new GPSPoint[]{new GPSPoint(50, 50), new GPSPoint(200, 50)});
        roads.add(new GPSPoint[]{new GPSPoint(200, 50), new GPSPoint(200, 200)});
        roads.add(new GPSPoint[]{new GPSPoint(50, 200), new GPSPoint(200, 200)});
        roads.add(new GPSPoint[]{new GPSPoint(50, 50), new GPSPoint(50, 200)});

        // Example roundabouts / landmarks (as circles)
        circles.add(new GPSPoint(125, 125));
        radii.add(40);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        setBackground(Color.WHITE);

        // Draw all roads using Bresenham’s Algorithm
        g.setColor(Color.BLUE);
        for (GPSPoint[] road : roads) {
            drawLineBresenham(g, road[0].x, road[0].y, road[1].x, road[1].y);
        }

        // Draw all circles using Midpoint Circle Algorithm
        g.setColor(Color.RED);
        for (int i = 0; i < circles.size(); i++) {
            GPSPoint center = circles.get(i);
            int r = radii.get(i);
            drawCircleMidpoint(g, center.x, center.y, r);
        }
    }

    // Bresenham’s Line Drawing Algorithm
    private void drawLineBresenham(Graphics g, int x1, int y1, int x2, int y2) {
        int dx = Math.abs(x2 - x1);
        int dy = Math.abs(y2 - y1);
        int sx = x1 < x2 ? 1 : -1;
        int sy = y1 < y2 ? 1 : -1;
        int err = dx - dy;

        while (true) {
            g.fillRect(x1, y1, 1, 1); // draw pixel
            if (x1 == x2 && y1 == y2) break;
            int e2 = 2 * err;
            if (e2 > -dy) { err -= dy; x1 += sx; }
            if (e2 < dx) { err += dx; y1 += sy; }
        }
    }

    // Midpoint Circle Algorithm
    private void drawCircleMidpoint(Graphics g, int xc, int yc, int r) {
        int x = 0, y = r;
        int d = 1 - r;

        while (x <= y) {
            // Plotting symmetric points in all 8 octants
            plotCirclePoints(g, xc, yc, x, y);
            if (d < 0) {
                d += 2 * x + 3;
            } else {
                d += 2 * (x - y) + 5;
                y--;
            }
            x++;
        }
    }

    private void plotCirclePoints(Graphics g, int xc, int yc, int x, int y) {
        g.fillRect(xc + x, yc + y, 1, 1);
        g.fillRect(xc - x, yc + y, 1, 1);
        g.fillRect(xc + x, yc - y, 1, 1);
        g.fillRect(xc - x, yc - y, 1, 1);
        g.fillRect(xc + y, yc + x, 1, 1);
        g.fillRect(xc - y, yc + x, 1, 1);
        g.fillRect(xc + y, yc - x, 1, 1);
        g.fillRect(xc - y, yc - x, 1, 1);
    }
}

// Main GPS Map Window
public class GPSMapRendering extends JFrame {
    public GPSMapRendering() {
        setTitle("GPS Map Rendering - Line & Circle Algorithms");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        add(new MapPanel());
        setLocationRelativeTo(null); // Center window
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(GPSMapRendering::new);
    }
}
