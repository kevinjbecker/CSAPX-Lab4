import components.Component;
import components.*;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.io.File;

/**
 * This class is used to construct a HomeWiring diagram (read in from a file). It then runs the program so
 * different items can be tested by the user. Uses the database structure provided by Professor Sean Strout @ RIT CS.
 *
 * @author Kevin Becker
 */


public class HomeWiring
{
    private static final String APPLIANCE = "appliance";
    private static final String CIRCUIT = "circuit";
    private static final String COMMENT = "#";
    private static final String DISPLAY = "d";
    private static final String OFF = "-";
    private static final String ON = "+";
    private static final String OUTLET = "outlet";
    private static final String PLUG = "p";
    private static final String PROMPT = "> ";
    private static final String QUIT = "q";
    private static final String UNPLUG = "u";

    /**
     * Private constructor of the HomeWiring object. I made this private because it doesn't need to be publicly available.
     *
     * @param fileName the name of the file containing the wiring diagram.
     */
    private HomeWiring(String fileName)
    {
        try
        {
            // Creates a scanner object that reads from the file
            Scanner sc = new Scanner(new File(fileName));

            // Creates the String array of commands
            ArrayList<String>cmds = new ArrayList<>();

            // Makes an array of the commands that need to be run
            while(sc.hasNextLine())
            {
                // Reads in a line of the file
                String lineInFile = sc.nextLine();

                // If a line is not empty and isn't a comment, then we can accept it as
                if (!lineInFile.isEmpty() && !lineInFile.substring(0, 1).equals(COMMENT))
                {
                    // Adds to the database each component
                    cmds.add(lineInFile);
                }
            }

            // Takes the wiring diagram and puts it into memory as component objects
            createWiringDiagram(cmds);
        }
        catch(java.io.FileNotFoundException e)
        {
            // If the file isn't found we can simply alert the user to this so it doesn't blow up
            System.out.println("The file you tried to read from cannot be found. Please try again.");
        }
    }

    /**
     * The main runner method of HomeWiring.java
     *
     * @param args the arguments array used for getting the fileName when constructing the HomeWiring from file.
     */
    public static void main(String[] args)
    {
        if(args.length < 1)
        {
            // If the startup command was used wrong, alert the user and exit.
            System.out.println("Usage: $ java HomeWiring diagram");
        }
        else
        {
            // Creates a HomeWiring object using the file specified as an argument
            HomeWiring diagram = new HomeWiring(args[0]);
            
            // Runs the program until the QUIT command is run
            diagram.run();
        }
    }

    /**
     * The run method which allows the user to interact with the diagram.
     */
    private void run()
    {
        // This boolean keep the program running until the QUIT command is passed
        boolean continueRunning = true;
        
        // Creates a new Scanner for user input
        Scanner userInput = new Scanner(System.in);
        
        while(continueRunning)
        {
            // Prints the prompt String (>)
            System.out.print(PROMPT);
            
            // Gets the next command
            String next = userInput.nextLine();
            
            // If it is the quit command, quit the program otherwise enter else loop and perform more conditionals
            if(next.toLowerCase().equals(QUIT))
            {
                // This quits the program
                System.out.println("System will now quit.");
                continueRunning = false;
            }
            else
            {
                // Takes the input String and converts each it to an Array of tokens
                String [] cmds = next.split(" ");
                // If the first token is the DISPLAY command display it.

                Component component = null;

                if(Components.has(cmds[1]))
                {
                    // Gather the start component
                    component = Components.get(cmds[1]);
                }
                else
                {
                    // Alert the user that the component doesn't exist
                    System.out.println("Unknown component: " + cmds[1]);
                }

                if(component != null)
                {
                    switch (cmds[0].toLowerCase())
                    {
                        case DISPLAY:
                            // Display the component and its cascade of children
                            component.display();
                            break;
                        case ON:
                            component.turnOn();
                            break;
                        case OFF:
                            component.turnOff();
                            break;
                        case PLUG:
                            // Try to run this block
                            try
                            {
                                // If parent component actually exists
                                if (Components.has(cmds[2]))
                                {
                                    // Get the parent item
                                    Component parent = Components.get(cmds[2]);
                                    // Attempt to plug component into parent
                                    boolean plugged = parent.add(component);
                                    // Alert the user on success or failure
                                    System.out.println(cmds[1] + " plugged in: " + plugged);
                                }
                                else
                                {
                                    // Alert the user if disallowed attempt
                                    System.out.println("Component does not exist. Cannot be plugged in.");
                                }
                            }
                            catch (ArrayIndexOutOfBoundsException e)
                            {
                                // If there are not enough arguments then tell the user this (prevents blowing up)
                                System.out.println("Usage: >p component-name parent-component-name");
                            }
                            break;
                        case UNPLUG:
                            // Attempt to remove the component
                            boolean unplugged = component.remove();
                            // Alert the user on success or failure
                            System.out.println(cmds[1]+ " unplugged: " + unplugged);
                            break;
                        default:
                            System.out.println("Unknown command.");
                            break;
                    }
                }
            }
        }
    }

    /**
     * Creates the wiring diagram in the specified manner.
     *
     * @param cmds an array of Strings which add assorted types of Components
     */
    private void createWiringDiagram(List<String> cmds)
    {
        // Loops through all of the command lines
        for(String cmd : cmds)
        {
            // Splits each command into an array of tokens
            String [] splitCmd = cmd.split(" ");
            // Gets the component type
            String componentType = splitCmd[1];
            // Gets the component name
            String name = splitCmd[2];
            // Gets the component's parent name
            String parentName = splitCmd[3];
            // Gets the interested integer value (either current or plug count)
            int value = Integer.parseInt(splitCmd[4]);
            // Gets the parent of the component as parent
            Component parent = Components.get(parentName);

            switch(componentType.toLowerCase())
            {
                case APPLIANCE:
                    // Adds a new Appliance with the requested values
                    Components.add(new Appliance(name, parent, value));
                    break;
                case CIRCUIT:
                    // Adds a new Circuit with the requested values
                    Components.add(new Circuit(name, parent, value));
                    break;
                case OUTLET:
                    // Adds a new Outlet with the requested values
                    Components.add(new Outlet(name, parent, value));
                    break;
                default:
                    // Alert the user that a bad line was found in the document
                    System.out.print("Incorrect component type specified on a non-commented line. Skipping.");
                    break;
            }
        }
    }
}
