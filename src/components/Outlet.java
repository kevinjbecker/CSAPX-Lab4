package components;

/**
 * This class is used for the Outlet Components. It can only distribute power to Appliances.
 * @author Kevin Becker
 */

public class Outlet extends Component
{
    private int outlets;

    /**
     * Creates a Outlet object.
     *
     * @param name The name of the Outlet.
     * @param parent The parent Component.
     * @param outlets The number of outlets in the Outlet.
     */
    public Outlet(String name, Component parent, int outlets)
    {
        // Runs the superclass' constructor using the name, the parent, the type and maxCurrent of 0
        super(name, parent, Type.OUTLET, 0);
        // Sets the number of outlets
        this.outlets = outlets;
    }

    /**
     * Attempts to add a component to the Outlet. The only Components that can be added to an Outlet are Appliances.
     *
     * @param component The Component being added to this.
     * @return true or false; true if the component was added, false otherwise.
     */
    public boolean add(Component component)
    {
        if(component.type == Type.APPLIANCE)
        {
            //Adds to the children component
            children.add(component);
            return true;
        }
        return false;
    }

    /**
     * This isn't an allowed action, so it always returns false.
     *
     * @return false since Outlets can't be removed from a diagram.
     */
    public boolean remove()
    {
        return false;
    }

    /**
     * Overrides the toString method to give critical information about the Outlet.
     *
     * @return a String for the Outlet Component.
     */
    @Override
    public String toString()
    {
        return "Outlet(name=" + name + ", current=" + currentBeingUsed +", plugs=" + children.size() + '/' +  outlets + ", on=" + turnedOn +")";
    }
}
