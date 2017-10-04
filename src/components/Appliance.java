package components;

public class Appliance extends Component
{

    public Appliance(String name, Component parent, int requiredCurrent)
    {
        super(name, parent, Type.values()[2], requiredCurrent);
    }

    @Override
    public void turnOn()
    {
        current = currentUsed;
        super.turnOn();
    }

    @Override
    public void turnOff()
    {
        current = 0;
        super.turnOff();
    }

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

    public boolean add(Component component)
    {
        return false;
    }

}
