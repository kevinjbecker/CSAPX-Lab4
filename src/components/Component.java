package components;

public abstract class Component
{
    public enum Type
    {
        APPLIANCE,
        CIRCUIT,
        OUTLET
    }


    private String name;
    private Component.Type type;
    private Component parent;

    /**
     *
     * @param name
     * @param type
     */
    public Component(String name, Component parent, Component.Type type)
    {
        this.name = name;
        this.parent = parent;
        this.type = type;
    }

    public abstract boolean add(Component component);
    public abstract boolean remove();

    public void display()
    {
        System.out.println("temp");
    }

    public boolean updateCurrent(int newCurrentValue)
    {
        return false;
    }

    public void turnOn()
    {
        System.out.println("turning on");
    }

    public void turnOff()
    {
        System.out.println("turning off");
    }

    public String getName()
    {
        return this.name;
    }

    public Component.Type getType()
    {
        return this.type;
    }

    @Override
    public boolean equals(Object o)
    {
        return false;
    }
}