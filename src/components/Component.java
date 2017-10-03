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

    private String name;
    private Component.Type type;
    private Component parent;
    private List<Component> children = new ArrayList<Component>();
    private Component highestParent;
    private int maxCurrent;
    private int currentUsed;

    public Component(String name, Component parent, Component.Type type)
    {
        this.name = name;
        this.parent = parent;
        this.type = type;
        if(parent != null)
        {
            parent.getChildren().add(this);
        }
        this.highestParent = findHighestParent();
    }

    public abstract boolean add(Component component);
    public abstract boolean remove();

    private void display(int depth)
    {
        for (int i = 0; i < depth; i++)
        {
            System.out.print("  ");
        }

        System.out.println(this);

        for (Component c: children)
        {
            c.display(depth + 1);
        }
    }

    public void display()
    {
        display(0);
    }

    public boolean updateCurrent(int newCurrentValue)
    {
        if(newCurrentValue <= maxCurrent)
        {
            currentUsed = newCurrentValue;
            return true;
        }

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

    public void setChildren(ArrayList<Component> children)
    {
        this.children = children;
    }

    public Component.Type getType()
    {
        return this.type;
    }

    public Component getParent()
    {
        return this.parent;
    }

    public List getChildren()
    {
        return children;
    }

    public Component findHighestParent()
    {
        return (parent == null) ? this : parent.findHighestParent();
    }

    @Override
    public boolean equals(Object o)
    {
        return false;
    }
}