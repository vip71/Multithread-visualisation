package com.example.demo1;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Jpanel extension used ass rectangle on screen
 */
public class Rectangle extends JPanel {
    /**
     * Constructor for Rectangle
     * @param thread thread that owns "this" rectangle
     */
    Rectangle(MyThread thread){
        super();
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                synchronized (thread) {
                    e.consume();
                    thread.setWorking(!thread.working);
                    if (thread.working) {
                        thread.notify();
                    }
                }
            }
        });
    }
}
