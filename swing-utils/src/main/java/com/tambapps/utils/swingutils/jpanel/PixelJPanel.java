package com.tambapps.utils.swingutils.jpanel;

import com.tambapps.utils.collectionutils.ArrayGrid;
import com.tambapps.utils.collectionutils.Grid;

import javax.swing.*;
import java.awt.*;

public class PixelJPanel extends JPanel {

  private final Grid<Color> pixels;
  private int pixelSize;

  public PixelJPanel(int pixelsWidth, int pixelsHeight, int pixelSize) {
    this.pixelSize = pixelSize;
    pixels = new ArrayGrid<>(pixelsHeight, pixelsWidth);
    pixels.fill(Color.black);
  }

  public PixelJPanel(int pixelsWidth, int pixelsHeight) {
    this(pixelsWidth, pixelsHeight, 0);
  }

    @Override
  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    drawPixels((Graphics2D) g);
  }

  private void drawPixels(Graphics2D g) {
    int pixelWidth;
    int pixelHeight;
    if (pixelSize > 0) {
      pixelWidth = this.pixelSize;
      pixelHeight = this.pixelSize;
    } else {
      pixelWidth = getWidth() / pixels.getN();
      pixelHeight = getHeight() / pixels.getM();
    }
    int startX = (getWidth() - pixels.getN() * pixelWidth) / 2;
    int startY = (getHeight() - pixels.getM() * pixelHeight) / 2;
    for (int j=0; j < pixels.getM(); j++) {
      for (int i=0; i< pixels.getN(); i++) {
        g.setColor(pixels.get(j, i));
        g.fillRect(startX + pixelWidth * i, startY + pixelHeight * j, pixelWidth, pixelHeight);
      }
    }
  }

  public void setPixelAt(int x, int y, Color color) {
    pixels.set(y, x, color);
  }

  public void fitPixels() {
    pixelSize = 0;
  }

  public void setPixelSize(int pixelSize) {
    this.pixelSize = pixelSize;
  }

  public Color getPixelAt(int x, int y) {
    return pixels.get(y, x);
  }

  public void redrawPixels() {
    repaint();
  }
}
