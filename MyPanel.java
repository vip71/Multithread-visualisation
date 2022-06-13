package com.example.demo1;
import java.awt.*;
import java.util.Random;

/**
 * Main panel
 */
public class MyPanel extends Panel {
    /** random*/
    Random random;
    /** My Threads, every one contains one rectangle
     * @see MyThread*/
    MyThread[][] MyThreads;

    /** constructor of MyPanel */
    MyPanel(int n,int m)
    {
        setLayout(new GridLayout(n,m));
        setBackground(Color.BLACK);

        random = new Random();
        MyThreads = new MyThread[n][m];

        for(int i=0;i<n;i++)
        {
            for(int j=0;j<m;j++)
            {
                MyThreads[i][j] = new MyThread( i,j,this);
            }
        }
        for(int i=0;i<n;i++)
        {
            for(int j=0;j<m;j++)
            {
                MyThreads[i][j].start();
                add(MyThreads[i][j].rectangle);
            }
        }
    }
}
