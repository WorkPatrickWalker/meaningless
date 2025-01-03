package ca.m0r53k0d3.meaningless;

import static org.lwjgl.glfw.GLFW.GLFW_PRESS;
import static org.lwjgl.glfw.GLFW.GLFW_RELEASE;

public class MouseListener
{
    private static MouseListener instance;

    private double x, y, lastX, lastY, scrollX, scrollY;

    // A list of if each standard mouse button is being clicked or not
    private final boolean[] buttonsClicked = new boolean[3];

    private boolean isDragging;

    private MouseListener()
    {
        this.x = 0.0;
        this.y = 0.0;
        this.lastX = 0.0;
        this.lastY = 0.0;
        this.scrollX = 0.0;
        this.scrollY = 0.0;
    }

    public static MouseListener get()
    {
        // Create the mouse listener if it doesn't already exist
        if (instance == null)
        {
            instance = new MouseListener();
        }

        return instance;
    }

    public static void posCallback(long window, double x, double y)
    {
        // Some args are never used, but the callback integration lambda demands all possible variables so uh print this out but only once
        if (!Window.numberPrinted)
        {
            System.out.println("Here's a number!: " + window);
            Window.numberPrinted = true;
        }

        // Set the previous mouse position to the current mouse position
        get().lastX = get().x;
        get().lastY = get().y;

        // Set the current mouse position to the recorded position
        get().x = x;
        get().y = y;

        // Indicate if the mouse is being dragged
        get().isDragging = get().buttonsClicked[0] || get().buttonsClicked[1] || get().buttonsClicked[2];
    }

    public static void buttonCallback(long window, int button, int action, int mods)
    {
        // Some args are never used, but the callback integration lambda demands all possible variables so uh print this out but only once
        if (!Window.numberPrinted)
        {
            System.out.println("Here's a number!: " + (window + mods));
            Window.numberPrinted = true;
        }

        // Make sure only the standard mouse buttons are checked
        if (button < get().buttonsClicked.length)
        {
            // Update the state of if the mouse is being clicked or dragged
            if (action == GLFW_PRESS)
            {
                get().buttonsClicked[button] = true;
            }
            else if (action == GLFW_RELEASE)
            {
                get().buttonsClicked[button] = false;
                get().isDragging = false;
            }
        }
    }

    public static void scrollCallback(long window, double dx, double dy)
    {
        // Some args are never used, but the callback integration lambda demands all possible variables so uh print this out but only once
        if (!Window.numberPrinted)
        {
            System.out.println("Here's a number!: " + window);
            Window.numberPrinted = true;
        }

        get().scrollX = dx;
        get().scrollY = dy;
    }

    public static float getX() {return (float)get().x;}

    public static float getY() {return (float)get().y;}

    public static float getDx() {return (float)(get().x - get().lastX);}

    public static float getDy() {return (float)(get().y - get().lastY);}

    public static float getScrollX() {return (float)get().scrollX;}

    public static float getScrollY() {return (float)get().scrollY;}

    public static boolean isDragging() {return get().isDragging;}

    public static boolean isClicked(int button)
    {
        // Make sure only the standard button is checked
        if (button < get().buttonsClicked.length)
        {
            return get().buttonsClicked[button];
        }
        else
        {
            return false;
        }
    }

    public static void endFrame()
    {
        get().scrollX = 0.0;
        get().scrollY = 0.0;

        // Advance the previous position to the current position to reset the delta position
        get().lastX = get().x;
        get().lastY = get().y;
    }
}