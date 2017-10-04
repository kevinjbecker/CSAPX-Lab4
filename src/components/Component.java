package components;

import java.util.List;
import java.util.ArrayList;

/**
 * This class is used to construct a HomeWiring diagram (read in from a file). It then runs the program so
 * different items can be tested by the user. Uses the database structure provided by Professor Sean Strout @ RIT CS.
 *
 * @author Kevin Becker
 */

public abstract class Component
{
    /**
     * This enum has the sole purpose of determining the types of Components (the subclasses of Component).
     */
    public enum Type
    {
        APPLIANCE,
        CIRCUIT,
        OUTLET
    }

    // Without and modifier these variables are package private; the desired design.
    String name;
    Component.Type type;
    Component parent;
    List<Component> children = new ArrayList<>();
    Component highestParent;
    boolean turnedOn = false;
    int currentBeingUsed = 0;
    int maxCurrent;


    /**
     * Constructs a new Component object. No modifier is provided since the only classes that use this constructor are package based.
     *
     * @param name The name of the Component.
     * @param parent The parent Component of this new Component.
     * @param type The type of Component being used (utilizes Component.Types enum).
     * @param maxCurrent The maximum current allowed by the Component.
     */
    Component(String name, Component parent, Component.Type type, int maxCurrent)
    {
        this.name = name;
        this.parent = parent;
        this.type = type;
        this.maxCurrent = maxCurrent;
        if(parent != null)
        {
            parent.children.add(this);
        }
        this.highestParent = findHighestParent();
    }

    /**
     * An abstract class for the add method, used to add a Component to itself.
     *
     * @param component The Component being added to this.
     * @return true or false; true if the Component was successfully added, false otherwise.
     */
    public abstract boolean add(Component component);

    /**
     * An abstract class for the remove method, used to remove a Component from its parent.
     *
     * @return true or false; true if the Component was successfully removed, false otherwise.
     */
    public abstract boolean remove();

    /**
     * Wrapper class for private display(int).
     */
    public void display()
    {
        // Calls display(int) with a value of 0
        display(0);
    }

    /**
     * Begins the display cascade.
     *
     * @param depth The depth of display currently at to have the correct number of indentations.
     */
    private void display(int depth)
    {
        // Indents to the proper amount
        for (int i = 0; i < depth; i++)
        {
            System.out.print("\t");
        }

        // Prints the toString to this object
        System.out.println(this);

        // Calls display for each of its children with a new int value of depth + 1
        for (Component c: children)
        {
            c.display(depth + 1);
        }
    }

    /**
     * Attempts to turn on the Component. Only works if its parent is turned on. Powers any children connected to it.
     */
    public void turnOn()
    {
        /*
        * If it isn't the top circuit or its parent is turned on
        * We can do this in the same conditional because if its parent is null (which means it can't run
        * parent.turnedOn) Java uses short-circuit analysis and skips over the second part
        */
        if(parent == null || parent.turnedOn)
        {
            // Attempt to turn Component on
            System.out.println(name + " is turning on.");
            // Set turnedOn to true
            this.turnedOn = true;
            // Turn each of its children on because they now have power
            for (Component child : children) {
                child.turnOn();
            }
            // Update the current cascade
            highestParent.updateCurrent();
        }
        else
        {
            // If a Component can't be turned on, alert the user.
            System.out.println( name + " cannot be turned on. Its parent is currently turned off.");
        }
    }

    /**
     * Attempts to turn on the Component. Only works if its turned on. Removes power to any children connected to it.
     */
    public void turnOff() {
        // Uses this for saving computational time
        if(turnedOn)
        {
            // Alert user which Component is turning off
            System.out.println(name + " is turning off.");
            // Sets the status so that the Component is turned off
            this.turnedOn = false;
            // Turns each of its children off because they now do not have power
            for (Component child : children)
            {
                child.turnOff();
                child.currentBeingUsed = 0;
            }
            // Update the current cascade
            highestParent.updateCurrent();
        }
    }

    /**
     * Updates the current of each Component.
     */
    public void updateCurrent()
    {
        // Checks to see if the Component has children (so it doesn't run if its an Appliance or doesn't have children)
        if(children.size() > 0)
        {
            // Sets the current count to 0
            int totalCurrent = 0;
            // Goes through each of its children to get the total amount of current being used
            for (Component child : children)
            {
                child.updateCurrent();
                totalCurrent += child.currentBeingUsed;
            }
            // Sets the current being used to totalCurrent
            this.currentBeingUsed = totalCurrent;
        }
    }

    /**
     * Gets the name of the Component.
     *
     * @return the name of the Component.
     */
    public String getName()
    {
        return this.name;
    }

    /**
     * Finds the highest parent in the diagram (the Component with parent of null).
     *
     * @return a Component of the highest parent
     */
    private Component findHighestParent()
    {
        return (parent == null) ? this : parent.findHighestParent();
    }


}