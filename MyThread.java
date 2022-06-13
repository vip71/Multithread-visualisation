package com.example.demo1;

import javax.swing.*;
import java.awt.*;

import static com.example.demo1.Application.*;
/** MyTread; every one is represented by one rectangle on screen */
public class MyThread extends Thread{
    /** rectangle that is shown on screen */
    public JPanel rectangle;
    /** main panel */
    final MyPanel panel;
    /** coordinates on screen */
    int i,j;
    /** color of JPanel */
    Color myColor;
    /** determines weather thread is working */
    public volatile boolean working;

    /**
     * constructor for MyThread
     * @param i i
     * @param j j
     * @param panel panel
     */
    public MyThread(int i,int j,MyPanel panel){
        this.i=i;
        this.j=j;
        this.panel=panel;
        myColor = new Color(panel.random.nextInt(255), panel.random.nextInt(255), panel.random.nextInt(255));
        rectangle = new Rectangle(this);
        rectangle.setSize(1, 1);
        rectangle.setBackground(myColor);
        working=true;
    }

    /**
     * Sets new value of working
     * @param b new value os working
     */
    void setWorking(boolean b){working=b;}

    /**
     * Counts how many neighbours work
     * @param colors colors
     * @return number of how many neighbours work
     */
    int CountWorkingNeighbours(Color[] colors){
        int s=0;
        for(int a=0;a<4;a++) colors[a]=new Color(0,0,0);
        if(panel.MyThreads[(n+i-1)%n][j].working) {s++; colors[0]=panel.MyThreads[(n+i-1)%n][j].myColor;}
        if(panel.MyThreads[(n+i+1)%n][j].working) {s++; colors[1]=panel.MyThreads[(n+i+1)%n][j].myColor;}
        if(panel.MyThreads[i][(m+j-1)%m].working) {s++; colors[2]=panel.MyThreads[i][(m+j-1)%m].myColor;}
        if(panel.MyThreads[i][(m+j+1)%m].working) {s++; colors[3]=panel.MyThreads[i][(m+j+1)%m].myColor;}
        return s;
    }

    /**
     * Colors rectangle
     */
     void ColorMe(){
                synchronized (panel) {
                    if(panel.random.nextDouble(0,1)<p) {
                        myColor = new Color(panel.random.nextInt(255), panel.random.nextInt(255), panel.random.nextInt(255));
                        rectangle.setBackground(myColor);
                    }
                    else{
                       int red,green,blue,workingNeighbours;
                       Color[] NeighboursColors =new Color[4];
                       workingNeighbours=CountWorkingNeighbours(NeighboursColors);
                       if(workingNeighbours>0) {
                           red = (NeighboursColors[0].getRed() + NeighboursColors[1].getRed() + NeighboursColors[2].getRed() + NeighboursColors[3].getRed()) / workingNeighbours;
                           blue = (NeighboursColors[0].getBlue() + NeighboursColors[1].getBlue() + NeighboursColors[2].getBlue() + NeighboursColors[3].getBlue()) / workingNeighbours;
                           green = (NeighboursColors[0].getGreen() + NeighboursColors[1].getGreen() + NeighboursColors[2].getGreen() + NeighboursColors[3].getGreen()) / workingNeighbours;
                           myColor = new Color(red, green, blue);
                           rectangle.setBackground(myColor);
                       }
                    }
                }
                Thread.yield();
    }

    /** threads run function */
    public void run() {
        while (true) {
            try {
                Thread.sleep(panel.random.nextInt(k/2,3*k/2));

                if (!working) {
                    synchronized(this) {
                        while (!working)
                            wait();
                    }
                }
            } catch (InterruptedException e){
            }
            ColorMe();
        }
    }

}
