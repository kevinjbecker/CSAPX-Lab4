import components.Component;

import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.io.File;


public class HomeWiring {

    private HomeWiring diagram;
    public static final String APPLIANCE = "appliance";
    public static final String CIRCUIT = "circuit";
    public static final String COMMENT = "#";
    public static final String DISPLAY = "d";
    public static final String OFF = "-";
    public static final String ON = "+";
    public static final String OUTLET = "outlet";
    public static final String PLUG = "p";
    public static final String PROMPT = ">";
    public static final String QUIT = "q";
    public static final String UNPLUG = "u";

    public HomeWiring(String fileName)
    {
        try
        {
            // Creates a scanner object that reads from the file
            Scanner sc = new Scanner(new File(fileName));

            // Creates the String array of commands
            List<String>cmds = new ArrayList();

            // Makes an array of the commands that need to be run
            while(sc.hasNextLine())
            {
                String line_in_file = sc.nextLine();

                // If a line is not empty and isn't a comment, then we can accept it as
                if (!line_in_file.isEmpty() && !line_in_file.substring(0, 1).equals(COMMENT))
                {
                    cmds.add(line_in_file);
                }
            }
            createWiringDiagram(cmds);
        }
        catch(java.io.FileNotFoundException e)
        {
            System.out.println("The file you tried to read from cannot be found. Please try again.");
        }
    }


    public static void main(String[] args)
    {
        if(args.length < 1)
        {
            System.out.println("Usage: $ java HomeWiring diagram");
        }
        else
        {
            HomeWiring diagram = new HomeWiring(args[0]);
            diagram.run();
        }
    }

    private void run()
    {
        boolean continueRunning = true;
        Scanner in = new Scanner(System.in);
        while(continueRunning)
        {
            System.out.print(PROMPT);
            String next = in.nextLine();
            if(next.toLowerCase().equals(QUIT))
            {
                System.out.println("System will now quit.");
                break;
            }
            else
            {
                String [] cmds = next.split(" ");
                if(cmds[0].equals(DISPLAY))
                {
                    if(Components.has(cmds[1]))
                    {
                        components.Component toDisplay = Components.get(cmds[1]);
                        toDisplay.display();
                    }
                    else
                    {
                        System.out.println("Unknown component: " + cmds[1]);
                    }
                }
                else if(cmds[0].equals(ON))
                {
                    if (Components.has(cmds[1]))
                    {
                        Component startPowerCascade = Components.get(cmds[1]);
                        startPowerCascade.turnOn();
                    }
                }
                else if(cmds[0].equals(OFF))
                {
                    //power off
                }
                else if(cmds[0].equals(PLUG))
                {
                    try
                    {
                        //plug component
                        if (Components.has(cmds[1]))
                        {
                            Component toPlug = Components.get(cmds[1]);
                            Component newParent = Components.get(cmds[2]);
                            boolean plugged = newParent.add(toPlug);
                            System.out.println(cmds[1] + " plugged in: " + plugged);
                        }
                        else
                        {
                            System.out.println("Component does not exist. Cannot be plugged in.");
                        }
                    }
                    catch (ArrayIndexOutOfBoundsException e)
                    {
                        System.out.println("Usage: >p component-name parent-component-name");
                    }
                }
                else if(cmds[0].equals(UNPLUG))
                {
                    //unplug component
                    //plug component
                    if(Components.has(cmds[1]))
                    {
                        Component toUnPlug = Components.get(cmds[1]);
                        boolean unplugged = toUnPlug.remove();
                        System.out.println(cmds[1]+ " unplugged: " + unplugged);
                    }
                }
                else
                {
                    System.out.println("Unknown command. Please try again.");
                }
            }
            //implement all of the commands
        }
    }

    private void createWiringDiagram(List<String> cmds)
    {
        // add component-type new-component parent-component #
        for(String cmd : cmds)
        {
            String [] splitCmd = cmd.split(" ");
            String componentType = splitCmd[1];
            String name = splitCmd[2];
            String parentName = splitCmd[3];
            int value = Integer.parseInt(splitCmd[4]);

            if(componentType.equals(CIRCUIT))
            {
                // add component-type new-component parent-component #
                components.Component parent = Components.get(parentName);
                components.Component temp = new components.Circuit(name, parent, value);
                Components.add(temp);
            }
            else if (componentType.equals(OUTLET))
            {
                components.Component parent = Components.get(parentName);
                components.Component temp = new components.Outlet(name, parent, value);
                Components.add(temp);
            }
            else if (componentType.equals(APPLIANCE))
            {
                components.Component parent = Components.get(parentName);
                components.Component temp = new components.Appliance(name, parent, value);
                Components.add(temp);
            }
            else
            {
                System.out.print("Incorrect component type specified on a non-commented line. Skipping.");
            }

            // Because it is the initial setup, we can assume we need to add all of them to the Components database
        }
    }

}
