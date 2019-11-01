package com.tambapps.utils.swingutils;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public abstract class JFrameTest extends JFrame {

  public JFrameTest(int width, int height) {
    setPreferredSize(new Dimension(width, height));
    setSize(width, height);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setLocationRelativeTo(null);
    getComponent().forEach(this::add);
  }

  protected abstract List<JComponent> getComponent();

  public void start() {
    EventQueue.invokeLater(() -> {
      setVisible(true);
    });
  }
}
