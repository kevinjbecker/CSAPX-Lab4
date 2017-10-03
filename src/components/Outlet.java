package components;

public class Outlet extends Component
{

    protected int outlets;

    public Outlet(String name, Component parent, int outlets)
    {
        super(name, parent, Type.values()[0]);
        this.outlets = outlets;
    }

    public boolean remove()
    {
        return false;
    }
    public boolean add(Component component)
    {
        if(component.getType() == Type.APPLIANCE)
        {
            this.getChildren().add(component);
            component.turnOn();
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
