import java.util.Scanner;
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
            String [] cmds = {};

            // Makes an array of the commands that need to be run
            for(int i = 0; sc.hasNextLine(); i++)
            {
                String line_in_file = sc.nextLine();

                // If a line is not empty and isn't a comment, then we can accept it as
                if (!line_in_file.isEmpty() && !line_in_file.substring(0, 1).equals(COMMENT))
                {
                    cmds[i] = line_in_file;
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
        }
    }

    private void createWiringDiagram(String[] cmds)
    {
        // add component-type new-component parent-component #
        for(String cmd : cmds)
        {
            String [] splitCmd = cmd.split(" ");
            String name = splitCmd[2];
            String componentType = splitCmd[1];
            int numberPart = Integer.parseInt(splitCmd[4]);

            if(componentType.equals(CIRCUIT))
            {
                if(name != "null")
                {
                    components.Component temp = new components.Circuit(name, Components.get(name),numberPart);
                    Components.add(temp);
                }
                else
                {
                    components.Component temp = new components.Circuit(name, null, numberPart);
                    Components.add(temp);
                }
            }
            else if (componentType.equals(OUTLET))
            {
                components.Component temp = new components.Outlet(name, Components.get(name), numberPart);
                Components.add(temp);
            }
            else if (componentType.equals(APPLIANCE))
            {
                components.Component temp = new components.Appliance(name, Components.get(name), numberPart);
                Components.add(temp);
            }
            else
            {
                System.out.print("Non-correct component type specified on a non-commented line. Skipping.");
            }
            // Because it is the initial setup, we can assume we need to add all of them to the Components database

        }
    }

}
