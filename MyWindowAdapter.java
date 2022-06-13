package com.example.demo1;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * Adapter class
 * @see MyFrame
 */
public class MyWindowAdapter extends WindowAdapter {
    /** makes window closeable */
    public void windowClosing(WindowEvent e) { System.exit(0);}
    }
