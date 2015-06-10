import javax.swing.*;
import java.awt.*;
public class SnowFrame extends JFrame implements ActionListener {
  public Pan f=new Pan();
  public JSlider slid=new JSlider(1,10);
  public static void main(String[] a) {
    SnowFrame s=new SnowFrame();
  }
  public SwonFrame() {
    super("FRACTALS");
    JPanel con=new JPanel();
    con.setLayout(new BorderLayout());
    JPanel bottom=new JPanel();
    JButton button=new JButton("Draw the fractal");
    button.addActionListener(this);
    bottom.add(button);
    bottom.add(slid);
    con.add(bottom, BordrLayout.SOUTH);
    con.add(f,BorderLayout.CENTER);
  }
  public void actionPerformed(ActionEvent e) {
    f.detail=slid.getValue();
    f.repaint();
  }
}
class Pan extens JPanel {
  int detail=1;
  public void paintComponent(Graphics g) {
    Graphics2D graf=(Graphics2D) g;
  }
  public void drawFractal(int startX, int startY, int endX int endY, int details, Graphics2D graf) {
    if (details==1) {
      graf.drawLine(startX,startY,endX,endY);
    } else {
      drawFractal(stratX,startY,(2*startX+endX)/3,(2*startY+endY)/3,details-1,graf);
      drawFractal(endX,endY,(2*endX+startX)/3,(2*endY+startY)/3,details-1,graf);
    }
  }
}
