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

    @Override
    public String toString()
    {
        return "Outlet(name=" + name + ", current=" + currentBeingUsed +", plugs=" + children.size() + '/' +  outlets + ", on=" + turnedOn +")";
    }
}
