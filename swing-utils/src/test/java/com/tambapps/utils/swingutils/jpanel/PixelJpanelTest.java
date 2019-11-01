package com.tambapps.utils.swingutils.jpanel;

import com.tambapps.utils.swingutils.JFrameTest;

import java.awt.Color;
import javax.swing.JComponent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Collections;
import java.util.List;

public class PixelJpanelTest extends JFrameTest {

  private static final int PIXEL_SIZE = 10;
  private static final int WIDTH = 30;
  private static final int HEIGHT = 50;
  private static final int WINDOW_WIDTH = 800;
  private static final int WINDOW_HEIGHT = 1000;
  private static final PixelJPanel PIXEL_PANEL;

  public PixelJpanelTest() {
    super(WINDOW_WIDTH, WINDOW_HEIGHT);
  }
  static {
    PIXEL_PANEL = new PixelJPanel(WIDTH, HEIGHT, PIXEL_SIZE);
    PIXEL_PANEL.setPixelAt(0, 0, Color.white);
    PIXEL_PANEL.setBackground(Color.red);
    PIXEL_PANEL.addMouseListener(new MouseAdapter() {
      @Override
      public void mouseClicked(MouseEvent e) {
        int x = (int) (Math.random() * WIDTH);
        int y = (int) (Math.random() * HEIGHT);
        PIXEL_PANEL.setPixelAt(x, y, Color.blue);
        PIXEL_PANEL.repaint();
      }
    });
    //PIXEL_PANEL.fitPixels();
  }

  @Override
  protected List<JComponent> getComponent() {
    return Collections.singletonList(PIXEL_PANEL);
  }

  public static void main(String[] args) {
    new PixelJpanelTest().start();
  }
}
