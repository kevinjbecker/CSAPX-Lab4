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
        super(name, parent, Type.values()[1]);
        this.maxCurrent = maxCurrent;
    }

    public boolean remove()
    {
        return false;
    }

    public boolean add(Component component)
    {
        if(component.getType() != Type.APPLIANCE)
        {
            parent.getChildren().add(component);
            return true;
        }
        return false;
    }

    public boolean updateCurrent(int current){return true;}

    public void turnOn()
    {

    }

    @Override
    public String toString()
    {
        return "";
    }
}
