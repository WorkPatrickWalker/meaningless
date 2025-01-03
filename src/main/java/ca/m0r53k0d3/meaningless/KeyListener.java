package ca.m0r53k0d3.meaningless;

import static org.lwjgl.glfw.GLFW.GLFW_PRESS;
import static org.lwjgl.glfw.GLFW.GLFW_RELEASE;

public class KeyListener
{
    private static KeyListener instance;

    // A list of if any of the standard keys are being pressed
    private final boolean[] keyPressed = new boolean[350];

    private KeyListener() {}

    public static KeyListener get()
    {
        // Create the key listener if it doesn't exist already
        if (instance == null)
        {
            instance = new KeyListener();
        }

        return instance;
    }

    public static void keyCallback(long window, int key, int scancode, int action, int mods)
    {
        // Some args are never used, but the callback integration lambda demands all possible variables so uh print this out but only once
        if (!Window.numberPrinted)
        {
            System.out.println("Here's a number!: " + (window + scancode + mods));
            Window.numberPrinted = true;
        }

        // Update the state of if the referenced key is being pressed down or not
        if (action == GLFW_PRESS)
        {
            get().keyPressed[key] = true;
        }
        else if (action == GLFW_RELEASE)
        {
            get().keyPressed[key] = false;
        }
    }

    public static boolean isPressed(int key) {return get().keyPressed[key];}
}