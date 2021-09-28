package frc.robot;

public class Tools{
    public Tools() {}
    public static double range(double Value, double Max, double Min){
        return Value > Max ? Max : Value < Min ? Min : Value;
    }
}