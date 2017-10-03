package components;

public class Appliance extends Component{

    protected int requiredCurrent;

    public Appliance(String name, Component parent, int requiredCurrent)
    {
        super(name, parent, Type.values()[2]);
        this.requiredCurrent = requiredCurrent;
    }

    public boolean remove()
    {
        return true;
    }
    public boolean add(Component component)
    {
        return false;
    }

    public void turnOn()
    {

    }

    @Override
    public String toString()
    {
        return "";
    }
}
