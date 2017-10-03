package components;

public class Appliance extends Component{

    protected int requiredCurrent;

    public Appliance(String name, int requiredCurrent)
    {
        super(name, Type.values()[2]);
        this.requiredCurrent = requiredCurrent;
    }

    public boolean remove()
    {
        return true;
    }
    public boolean add(Component component)
    {
        return true;
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
