import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.io.File;


public class HomeWiring {

    private HomeWiring diagram;
    public static final String ADD = "add";
    public static final String APPLIANCE = "appliance";
    public static final String CIRCUIT = "circuit";
    public static final String COMMENT = "#";
    public static final String DISPLAY = "d";
    public static final String MAIN = "main";
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
            run();
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
            if(next.substring(0,1).equals("q"))
            {
                System.out.println("System will now quit.");
                continueRunning = false;
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
                if(name != "null")
                {
                    // add component-type new-component parent-component #
                    components.Component parent = Components.get(parentName);
                    components.Component temp = new components.Circuit(name, parent, value);
                    Components.add(temp);
                }
                else
                {
                    components.Component temp = new components.Circuit(name, null, value);
                    Components.add(temp);
                }
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
