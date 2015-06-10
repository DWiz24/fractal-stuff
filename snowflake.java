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
  public void paintComponent(Graphics g) {
    Graphics2D graf=(Graphics2D) g;
    int x=getSize().width;
    int y=getSize().height;
    drawFractal(x/4,y/4,3*x/4,y/4,detail,graf);
    drawFractal(x/4,y/4,x/2,3*y/4,detail,graf);
    drawFractal(3*x/4,y/4,x/2,3*y/4,detail,graf);
  }
  public void drawFractal(int startX, int startY, int endX, int endY, int details, Graphics2D graf) {
    if (details==1) {
      graf.drawLine(startX,startY,endX,endY);
    } else {
      drawFractal(startX,startY,(2*startX+endX)/3,(2*startY+endY)/3,details-1,graf);
      drawFractal(endX,endY,(2*endX+startX)/3,(2*endY+startY)/3,details-1,graf);
      double len=Math.sqrt(Math.pow((startX-endX),2)+Math.pow((endY-startY),2));
      double hyp=1.73205080757*len/6;
      double slope;
      if (startY==endY) {
        slope=0;
      } else {
      slope=-(startX-endX)/(startY-endY);
      }
      double a=Math.sqrt(Math.pow(hyp,2)/(1+Math.pow(slope,2)));
      int y=(int)(slope*a);
      int x=(int) a;
      drawFractal((startX+endX)/2+x,(startY+endY)/2+y,(2*startX+endX)/3,(2*startY+endY)/3,details-1,graf);
      drawFractal((startX+endX)/2+x,(startY+endY)/2+y,(2*endX+startX)/3,(2*endY+startY)/3,details-1,graf);
    }
  }
}
