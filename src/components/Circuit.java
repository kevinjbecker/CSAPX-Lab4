package components;

/**
 * This class is used for Circuit Components. Its main purpose is to distribute power to other Circuits or Outlets.
 *
 * @author Kevin Becker
 */

public class Circuit extends Component
{
    /**
     * Creates a Circuit object.
     *
     * @param name The name of the Circuit.
     * @param parent The parent Component.
     * @param maxCurrent The maximum current allowed by the Circuit.
     */
    public Circuit(String name, Component parent, int maxCurrent)
    {
        // Runs the superclass' constructor using the name, the parent, the type and maxCurrent
        super(name, parent, Type.CIRCUIT, maxCurrent);
    }

    /**
     * This method adds on to the updateCurrent() method to add on to the default and further to make sure the Circuit doesn't need to blow.
     */
    @Override
    public void updateCurrent() {
        // Runs the super updateCurrent first
        super.updateCurrent();
        // Makes sure the current being used doesn't exceed the max current
        if (currentBeingUsed > maxCurrent & turnedOn)
        {
            overloadCircuit();
        }
    }

    /**
     * Attempts to add a component to the Circuit. The only Components that can be added to a Circuit are Circuits and Outlets.
     *
     * @param component The Component being added to this.
     * @return true or false; true if the component was added, false otherwise.
     */
    public boolean add(Component component)
    {
        // If the Component isn't an Appliance it can be added to a Circuit.
        if(component.type != Type.APPLIANCE)
        {
            // Adds the Component to the parent's children ArrayList.
            parent.children.add(component);
            return true;
        }
        return false;
    }

    /**
     * This isn't an allowed action, so it always returns false.
     *
     * @return false since circuits can't be removed from a diagram.
     */
    @Override
    public boolean remove()
    {
        return false;
    }

    /**
     * If the circuit overloads, this method goes down the cascade turning off each of the subComponents.
     */
    private void overloadCircuit()
    {
        // Prints that the Circuit is breaking
        System.out.println(this + " is breaking.");
        // Runs down the turnOff cascade of this Circuit
        turnOff();
        // Sets the current being used to 0
        currentBeingUsed = 0;
        // Resets the highestParent current usage cascade
        highestParent.updateCurrent();
    }

    /**
     * Overrides the toString method to give critical information about the Circuit.
     *
     * @return a String for the Circuit Component.
     */
    @Override
    public String toString()
    {
        return "Circuit(name=" + name + ", current=" + currentBeingUsed +"/" + maxCurrent + ", on=" + turnedOn +")";
    }
}