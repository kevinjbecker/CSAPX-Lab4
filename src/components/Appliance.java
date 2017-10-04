package components;

public class Appliance extends Component
{

    public Appliance(String name, Component parent, int requiredCurrent)
    {
        super(name, parent, Type.APPLIANCE, requiredCurrent);
    }

    @Override
    public void turnOn(){
        currentBeingUsed = maxCurrent;
        super.turnOn();
    }

    @Override
    public void turnOff(){
        currentBeingUsed = 0;
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

    @Override
    public String toString()
    {
        return "Appliance(name=" + name + ", current=" + currentBeingUsed +"/"+ maxCurrent +", on=" + turnedOn +")";
    }

}
