package components;

import java.util.List;
import java.util.ArrayList;

public abstract class Component
{
    public enum Type
    {
        APPLIANCE,
        CIRCUIT,
        OUTLET
    }

    protected String name;
    protected Component.Type type;
    protected Component parent;
    protected List<Component> children = new ArrayList<Component>();
    protected Component highestParent;
    protected boolean turnedOn = false;
    protected int current;
    protected int currentUsed;

    public Component(String name, Component parent, Component.Type type, int currentUsed)
    {
        this.name = name;
        this.parent = parent;
        this.type = type;
        this.currentUsed = currentUsed;
        if(parent != null)
        {
            parent.children.add(this);
        }
        this.highestParent = findHighestParent();
    }

    public abstract boolean add(Component component);
    public abstract boolean remove();

    public void display()
    {
        display(0);
    }

    private void display(int depth)
    {
        for (int i = 0; i < depth; i++)
        {
            System.out.print("\t");
        }

        System.out.println(this);

        for (Component c: children)
        {
            c.display(depth + 1);
        }
    }

    public void turnOn()
    {
        System.out.println(name + " is turning on.");
        this.turnedOn = true;
        for(Component child : children)
        {
            child.turnOn();
        }

        highestParent.updateCurrent();
    }

    public void turnOff()
    {
        // Avoids recursive depth limit, since function would be called in an infinite loop if an element
        // tripped the circuit breaker, since it causes all elements below it to turn off.
        if(turnedOn)
        {
            System.out.println(name + " is turning off.");
            turnedOn = false;
            for (Component child : children)
            {
                child.turnOff();
            }
            highestParent.updateCurrent();
        }
    }

    public void updateCurrent()
    {

    }

    public String getName()
    {
        return this.name;
    }

    private Component findHighestParent()
    {
        return (parent == null) ? this : parent.findHighestParent();
    }


}