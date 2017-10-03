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

    public Component(String name, Component.Type type)
    {
        this.name = name;
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
        return name;
    }

    public Component.Type getType()
    {
        return type;
    }

    @Override
    public boolean equals(Object o)
    {
        return false;
    }
}