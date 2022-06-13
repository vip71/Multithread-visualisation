package com.example.demo1;

/**
 * Class with main method
 */
public class Application {
    /** determine number of rectangles on screen and how often they change color */
    static int m,n,k;
    /** determine probability of new random color */
    static double p;
    /** frame of my application */
    MyFrame frame;
    /** panel of my application */
    MyPanel panel;

    /** checks weather this string can be changed to int; if not sets value of d
     * @param argument argument
     * @param d default value
     * @return value that is going to be set
     */
    static int SetInteger(String argument,int d){
        try{
            int i=Integer.parseInt(argument);
            if(i>0) return i;
            else return d;
        }catch (NumberFormatException e){
            return d;
        }
    }

    /** checks weather this string can be changed to double from range 0 to 1; if not sets value of d
     * @param argument argument
     * @return value that is going to be set
     */
    static double SetProbability(String argument){
        try{
            double d=Double.parseDouble(argument);
            if(d>0&&d<1) return d;
            else return 0.5;
        }catch (NumberFormatException e){
            return 0.5;
        }
    }

    /**
     * main method
     * @param args arguments of function
     */
    public static void main(String[] args) {
        if(args.length<4) {
            System.out.println("blad, za malo argumentow");
            return;
        }

        Application app=new Application();
        m= SetInteger(args[0],5);
        n= SetInteger(args[1],5);
        k= SetInteger(args[2],1000);
        p=SetProbability(args[3]);

        app.panel = new MyPanel(n,m);
        app.frame = new MyFrame(app);
        app.frame.setVisible(true);
    }
}