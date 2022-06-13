package com.example.demo1;
import java.awt.*;

/** frame class*/
public class MyFrame extends Frame{
    /** frame constructor*/
    MyFrame(Application app) {

        setBounds(0,0,1280,960);
        setLayout(new BorderLayout());
        add(app.panel,BorderLayout.CENTER);

        addWindowListener(new MyWindowAdapter());
    }

}
