package components;

public class Circuit extends Component
{

    protected int maxCurrent;

    /**
     * Creates a circuit object to add to the item.
     * @param name
     * @param parent
     * @param maxCurrent
     */
    public Circuit(String name, Component parent, int maxCurrent)
    {
        super(name, parent, Type.values()[1], 0);
        this.maxCurrent = maxCurrent;
    }

    public void updateCurrent() {
        if(children.size() > 0)
        {
            int currentTotal = 0;
            for (Component child : children) {
                child.updateCurrent();
                currentTotal += child.currentUsed;
            }
            currentUsed = currentTotal;
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
        System.out.println(this + " is breaking!");
        turnOff();
    }
}