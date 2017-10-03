package components;

public class Circuit extends Component
{

    protected int maxCurrent;

    public Circuit(String name, int maxCurrent)
    {
        super(name, Type.values()[1]);
        this.maxCurrent = maxCurrent;
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
