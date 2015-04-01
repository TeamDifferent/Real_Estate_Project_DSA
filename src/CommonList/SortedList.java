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
public class SortedList extends List{
  public SortedList(int maxItems)

{
super(maxItems);
}  
  public SortedList()

{
super(100);
}
public boolean isThere (Listable item)

{
int compareResult;
int midPoint;
int first = 0;
int last = numItems - 1;
boolean moreToSearch = (first <= last);
boolean found = false;
while (moreToSearch && !found)
{
midPoint = (first + last) / 2;
compareResult = item.compareTo(list[midPoint]);
if (compareResult == 0)
found = true;
else if (compareResult < 0) 
{
last = midPoint - 1;
moreToSearch = (first <= last);
}
else
{
first = midPoint + 1;
moreToSearch = (first <= last);
}
}
return found;
}
public Listable retrieve (Listable item)

{
int compareResult;
int first = 0;
int last = numItems - 1;
int midPoint = (first + last) / 2;
boolean found = false;
while (!found)
{
midPoint = (first + last) / 2;
compareResult = item.compareTo(list[midPoint]);
if (compareResult == 0)
found = true;
else if (compareResult < 0) 
last = midPoint - 1;
else 
first = midPoint + 1;
}
return list[midPoint].copy();
}
public void insert (Listable item)

{
int location = 0;
boolean moreToSearch = (location < numItems);
while (moreToSearch)
{
if (item.compareTo(list[location]) < 0) 
moreToSearch = false;
else 
{
location++;
moreToSearch = (location < numItems);
}
}
for (int index = numItems; index > location; index--)
list[index] = list[index - 1];
list[location] = item.copy();
numItems++;
}
public void delete (Listable item)

{
int location = 0;
while (item.compareTo(list[location]) != 0)
location++;
for (int index = location + 1; index < numItems; index++)
list[index - 1] = list[index];
numItems--;
}
}
