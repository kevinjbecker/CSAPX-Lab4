package components;

public class Outlet extends Component
{

    protected int outlets;

    public Outlet(String name, Component parent, int outlets)
    {
        super(name, parent, Type.OUTLET, 0);
        this.outlets = outlets;
    }

    @Override
    public boolean add(Component component)
    {
        if(component.type == Type.APPLIANCE)
        {
            children.add(component);
            //component.turnOn();
            return true;
        }
        return false;
    }

    public boolean remove()
    {
        return false;
    }

    public void updateCurrent()
    {
        int currentTotal = 0;

        for(Component child : children)
        {
            currentTotal += child.currentUsed;
        }

        this.currentUsed = currentTotal;
    }

    @Override
    public String toString()
    {
        return "Outlet(name=" + name + ", current=" + current +", plugs=" + children.size() + '/' +  outlets + ", on=" + turnedOn +")";
    }
}
