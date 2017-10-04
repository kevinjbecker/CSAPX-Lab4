package components;

/**
 * This class is used for Appliance Components. It can only receive power.
 * @author Kevin Becker
 */

public class Appliance extends Component
{
    /**
     *
     * @param name
     * @param parent
     * @param requiredCurrent
     */
    public Appliance(String name, Component parent, int requiredCurrent)
    {
        super(name, parent, Type.APPLIANCE, requiredCurrent);
    }

    /**
     *
     */
    @Override
    public void turnOn(){
        currentBeingUsed = maxCurrent;
        super.turnOn();
    }

    /**
     *
     */
    @Override
    public void turnOff(){
        currentBeingUsed = 0;
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
        if(parent != null)
        {
            turnOff();
            parent.children.remove(this);
            parent = null;
            return true;
        }
        return false;
    }

    @Override
    /**
     *
     */
    public String toString()
    {
        return "Appliance(name=" + name + ", current=" + currentBeingUsed +"/"+ maxCurrent +", on=" + turnedOn +")";
    }

}
