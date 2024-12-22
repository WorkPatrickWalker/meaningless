package ca.m0r53k0d3.meaningless;

import org.lwjgl.Version;
import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.opengl.GL;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.glClear;
import static org.lwjgl.opengl.GL11.glClearColor;
import static org.lwjgl.opengl.GL11.GL_COLOR_BUFFER_BIT;
import static org.lwjgl.system.MemoryUtil.NULL;

public class Window
{
    private final int width, height;

    private final String title;

    private float r, g, b;
    private final float a;

    // The window's singleton instance
    private static Window instance = null;

    // The window's location in memory
    private static long window;

    private Window()
    {
        // Make the window 1120x630 when un-maximized (which is both 16:9 and fits on 1366x768 monitors!)
        this.width = 1120;
        this.height = 630;

        this.title = "meaningless";

        // Make the window white when initialized
        this.r = 1.0f;
        this.g = 1.0f;
        this.b = 1.0f;
        this.a = 1.0f;
    }

    public static Window get()
    {
        // Create the window if it doesn't already exist
        if (Window.instance == null)
        {
            Window.instance = new Window();
        }

        return Window.instance;
    }

    public void init()
    {
        // Say hello to LWJGL! :)
        System.out.println("Hello LWJGL " + Version.getVersion() + "!");

        // Tell GLFW to direct errors to the console
        GLFWErrorCallback.createPrint(System.err).set();

        // Complain if GLFW fails to initialize
        if (!glfwInit())
        {
            throw new IllegalStateException("Failed to initialize GLFW :(");
        }

        // Change what happens to the window when it gets created
        glfwDefaultWindowHints();
        glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE);
        glfwWindowHint(GLFW_MAXIMIZED, GLFW_TRUE);
        glfwWindowHint(GLFW_RESIZABLE, GLFW_TRUE);

        // Create the window, name it, and tell it the dimensions that it will shrink to once un-maximized
        window = glfwCreateWindow(this.width, this.height, this.title, NULL, NULL);

        // Complain if the window fails to be created
        if (window == NULL)
        {
            throw new IllegalStateException("Failed to create the window :(");
        }

        // Make the window have focus on the desktop
        glfwMakeContextCurrent(window);

        // Enable v-sync
        glfwSwapInterval(1);

        // Make the window visible
        glfwShowWindow(window);

        // Sync GLFW and LWJGL together
        GL.createCapabilities();
    }

    public void loop()
    {
        while (!glfwWindowShouldClose(window))
        {
            // Look for events that occurred since the last loop
            glfwPollEvents();

            // Set the background color
            glClearColor(this.r, this.g, this.b, this.a);
            glClear(GL_COLOR_BUFFER_BIT);

            // Swap the buffers (?)
            glfwSwapBuffers(window);
        }
    }
}