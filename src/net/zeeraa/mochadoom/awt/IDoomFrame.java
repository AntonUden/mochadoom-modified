package net.zeeraa.mochadoom.awt;

import java.awt.Image;
import java.util.function.Supplier;

import javax.swing.JFrame;

public interface IDoomFrame extends FullscreenOptions{
	void turnOn();
	
	void renewGraphics();
	
	void update();
	
	void dispose();
	
	Supplier<? extends Image> getImageSupplier();
	
	void setUndecorated(boolean undecorated);
	
	JFrame getJFrame();
}
