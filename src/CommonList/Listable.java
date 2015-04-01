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
public interface Listable {
    
    public abstract int compareTo(Listable other);

    public abstract Listable copy();

}
    
