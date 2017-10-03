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
        return true;
    }
    public boolean add(Component component)
    {
        return true;
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
