import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class SnowFrame extends JFrame implements ActionListener {
  public Pan f=new Pan();
  public JSlider slid=new JSlider(1,10);
  public static void main(String[] a) {
    SnowFrame s=new SnowFrame();
  }
  public SnowFrame() {
    super("FRACTALS");
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    slid.setMajorTickSpacing(3);
    slid.setPaintLabels(true);
    slid.setPaintTicks(true);
    JPanel con=new JPanel();
    con.setLayout(new BorderLayout());
    JPanel bottom=new JPanel();
    JButton button=new JButton("Draw the fractal");
    button.addActionListener(this);
    bottom.add(button);
    bottom.add(slid);
    con.add(bottom, BorderLayout.SOUTH);
    con.add(f,BorderLayout.CENTER);
    add(con);
    setSize(400,400);
    setVisible(true);
  }
  public void actionPerformed(ActionEvent e) {
    f.detail=slid.getValue();
    f.repaint();
  }
}
class Pan extends JPanel {
  int detail=4;
  int direction=90;
  int x=0;
  int y=0;
  public void paintComponent(Graphics g) {
    Graphics2D graf=(Graphics2D) g;
    x=getSize().width/9;
    y=getSize().height*5/6;
    int segSize=(int)((float)Math.min(x,y)*3);
    drawFractal(segSize,detail,graf);
    direction += 120;
    direction=(direction+360)%360;
    drawFractal(segSize,detail,graf);
    direction += 120;
    direction=(direction+360)%360;
    drawFractal(segSize,detail,graf);
    direction += 120;
    direction=(direction+360)%360;
  }
  public void drawFractal(int seg, int details, Graphics2D graf) {
    if (details<=1) {
      graf.drawLine(x,y,x+getChangeX(seg),y+getChangeY(seg));
      x=x+getChangeX(seg);
      y=y+getChangeY(seg);
      return;
    }
    drawFractal(seg/3,details-1,graf);
    direction -= 60;
    direction=(direction+360)%360;
    drawFractal(seg/3,details-1,graf);
    direction += 120;
    direction=(direction+360)%360;
    drawFractal(seg/3,details-1,graf);
    direction -= 60;
    direction=(direction+360)%360;
    drawFractal(seg/3,details-1,graf);
  }
  public int getChangeX(int len) {
    if (direction <90) return (int)(Math.sin(Math.toRadians(direction))*(double)len);
    if (direction ==90) return len;
    if (direction ==180 || direction==0) return 0;
    if (direction ==270) return -len;
    if (direction >90 && direction<180) return (int)(Math.cos(Math.toRadians(direction-90))*(double)len);
    if (direction >180 && direction<270) return -(int)(Math.sin(Math.toRadians(direction-180))*(double)len);
    if (direction >270 && direction!=360) return -(int)(Math.cos(Math.toRadians(direction-270))*(double)len);
    return 5;
  }
  public int getChangeY(int len) {
    if (direction <90) return (int)(Math.cos(Math.toRadians(direction))*(double)len);
    if (direction ==90 || direction==270) return 0;
    if (direction ==180) return -len;
    if (direction ==0) return len;
    if (direction >90 && direction<180) return -(int)(Math.sin(Math.toRadians(direction-90))*(double)len);
    if (direction >180 && direction<270) return -(int)(Math.cos(Math.toRadians(direction-180))*(double)len);
    if (direction >270 && direction!=360) return (int)(Math.sin(Math.toRadians(direction-270))*(double)len);
    return 5;
  }
}
