package components;

/**
 * This class is used for Appliance Components. It can only receive power.
 *
 * @author Kevin Becker
 */

public class Appliance extends Component
{
    /**
     * Creates an Appliance object.
     *
     * @param name The name of the Appliance.
     * @param parent The parent Component of the Appliance.
     * @param requiredCurrent The required current of the Appliance.
     */
    public Appliance(String name, Component parent, int requiredCurrent)
    {
        // Runs the superclass' constructor using the name, the parent, the type and maxCurrent of 0
        super(name, parent, Type.APPLIANCE, requiredCurrent);
    }

    /**
     *
     */
    @Override
    public void turnOn()
    {
        // Makes the current being used equal to the current the Appliance draws
        currentBeingUsed = maxCurrent;
        // Calls the superclass' turnOn() method
        super.turnOn();
    }

    /**
     *
     */
    @Override
    public void turnOff(){
        // Sets the current being used equal to 0 since the Appliance is now off
        currentBeingUsed = 0;
        // Calls the superclass' turnOff() method
        super.turnOff();
    }

    /**
     * This isn't an allowed action, so it always returns false.
     *
     * @param component The Component being added to this.
     * @return false always as components can't be added to the Appliance.
     */
    public boolean add(Component component)
    {
        return false;
    }

    /**
     * Turns itself off if it is currently on and removes itself from the parent Outlet.
     *
     * @return true or false; true if the Appliance was successfully removed from the Outlet, false otherwise.
     */
    public boolean remove()
    {
        // If the parent is null (in other words, it isn't removed from the diagram)
        if(parent != null)
        {
            // Turn the Appliance off
            turnOff();
            // Remove itself from its parents' child ArrayList
            parent.children.remove(this);
            // Set its parent to null
            parent = null;
            return true;
        }
        return false;
    }

    /**
     * Overrides the toString method to give critical information about the Appliance.
     *
     * @return a String for the Appliance Component.
     */
    @Override
    public String toString()
    {
        return "Appliance(name=" + name + ", current=" + currentBeingUsed +"/"+ maxCurrent +", on=" + turnedOn +")";
    }

}
