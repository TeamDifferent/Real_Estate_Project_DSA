/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CommonList;

/**
 *
 * @author DELL
 */
public abstract class List {
protected Listable[] list; 
protected int numItems;
protected int currentPos; 
public List(int maxItems)

{
numItems = 0;
list = new Listable[maxItems];
}
public boolean isFull()
// Returns whether this list is full
{
return (list.length == numItems);
}
public int lengthIs()
// Returns the number of elements on this list
{
return numItems;
}
public abstract boolean isThere (Listable item);

public abstract Listable retrieve(Listable item);

public abstract void insert (Listable item);

public abstract void delete (Listable item);

public void reset()

        {
currentPos = 0;
}
public Listable getNextItem ()
// Returns copy of the next element on this list
{
Listable next = list[currentPos];
if (currentPos == numItems-1)
currentPos = 0;
else
currentPos++;
return next.copy();
}
    
}
