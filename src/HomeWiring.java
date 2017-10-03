import java.util.Scanner;
import java.io.File;

public class HomeWiring {

    public static void main(String[] args)
    {
        if(args.length < 1)
        {
            System.out.println("Usage: $ java HomeWiring diagram");
        }
        else
        {

            String [] cmds = readWiringDiagramFile(args[0]);

            if(cmds == null)
            {
                System.out.println("Your file was empty. Please try again.");
            }
            else
            {
                createWiringDiagram(cmds);

                // This is used to break the program when it is time to turn it off.
                boolean continueRunning = true;

                // Keeps the program alive until it is quit.
                while (continueRunning) {
                    continueRunning = false;
                }
            }
        }
    }

    private static void createWiringDiagram(String[] cmds)
    {
        for(String cmd : cmds)
        {
            System.out.println("YAY");
        }
    }


    private static String [] readWiringDiagramFile(String filePath)
    {
        try
        {
            // Creates a scanner object that reads from the file
            Scanner sc = new Scanner(new File(filePath));

            // Creates the String array of commands
            String [] cmds = {};

            // Makes an array of the commands that need to be run
            for(int i = 0; sc.hasNextLine(); i++)
            {
                String line_in_file = sc.nextLine();

                // If a line is not empty and isn't a comment, then we can accept it as
                if (!line_in_file.isEmpty() && !line_in_file.substring(0, 1).equals("#")) {
                    cmds[i] = line_in_file;
                }
            }
            return cmds;
        }
        catch(java.io.FileNotFoundException e)
        {
            System.out.println("The file you tried to read from cannot be found. Please try again.");
        }

        // This should absolutely never run, but it is there to make IntelliJ happy.
        return null;
    }

}
