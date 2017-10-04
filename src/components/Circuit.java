package components;

public class Circuit extends Component
{

    /**
     * Creates a circuit object to add to the item.
     * @param name
     * @param parent
     * @param maxCurrent
     */
    public Circuit(String name, Component parent, int maxCurrent)
    {
        super(name, parent, Type.CIRCUIT, maxCurrent);
        this.currentBeingUsed = 0;
    }

    @Override
    public void updateCurrent() {
        super.updateCurrent();
        if (currentBeingUsed > maxCurrent && turnedOn) {
            overloadCircuit();
        }
    }

    @Override
    public boolean remove()
    {
        return false;
    }

    public boolean add(Component component)
    {
        if(component.type != Type.APPLIANCE)
        {
            parent.children.add(component);
            return true;
        }
        return false;
    }

    private void overloadCircuit()
    {
        System.out.println(this + " is breaking.");
        turnOff();
        currentBeingUsed = 0;
        highestParent.updateCurrent();
    }

    @Override
    public String toString()
    {
        return "Circuit(name=" + name + ", current=" + currentBeingUsed +"/" + maxCurrent + ", on=" + turnedOn +")";
    }
}