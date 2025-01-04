package ca.m0r53k0d3.meaningless;

public class Time {
    public static float startTime = System.nanoTime();

    public static float beginTime;

    public static float endTime;

    public static float dt;

    public static float getTime() {return (float)((System.nanoTime() - startTime) * 1E-9);}
}