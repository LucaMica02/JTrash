package model;

import view.Observer;

/**
 * @author Luca Micarelli
 * this interface makes an object observable
 */
public interface Observable 
{
	public void addObserver(Observer observer);
	public void removeObserver(Observer observer);
	public void notifyObserver();
}
