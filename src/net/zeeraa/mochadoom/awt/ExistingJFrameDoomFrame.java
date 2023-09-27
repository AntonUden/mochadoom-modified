package net.zeeraa.mochadoom.awt;

import java.awt.Component;
import java.awt.Container;
import java.awt.Graphics2D;
import java.awt.HeadlessException;
import java.awt.Image;
import static java.awt.RenderingHints.*;
import java.util.function.Supplier;
import java.util.logging.Level;
import javax.swing.JFrame;

import net.zeeraa.mochadoom.Engine;
import net.zeeraa.mochadoom.Loggers;
import net.zeeraa.mochadoom.doom.CommandVariable;

/**
 * Common code for Doom's video frames
 */
public class ExistingJFrameDoomFrame<Window extends Component & DoomWindow<Window>> implements FullscreenOptions, IDoomFrame {
	private final Window content;
	private volatile Graphics2D g2d;
	private final JFrame jframe;
	final Supplier<? extends Image> imageSupplier;
	final Dimension dim;

	ExistingJFrameDoomFrame(JFrame jframe, Dimension dimension, Window content, Supplier<? extends Image> imageSupplier) throws HeadlessException {
		this.jframe = jframe;
		this.content = content;
		this.imageSupplier = imageSupplier;
		this.dim = dimension;

		init();
	}

	@Override
	public JFrame getJFrame() {
		return jframe;
	}

	/**
	 * Initialize properties
	 */
	private void init() {
		/**
		 * This should fix Tab key - Good Sign 2017/04/21
		 */
		jframe.setFocusTraversalKeysEnabled(false);
		jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//jframe.setTitle(Engine.getEngine().getWindowTitle(0));
	}

	public void turnOn() {
		jframe.add(content);
		content.setFocusTraversalKeysEnabled(false);
		System.out.println(content.getClass().getName());
		if (content instanceof Container) {
			System.out.println("content is container");
			jframe.setContentPane((Container) content);
		} else {
			System.out.println("content is not container");
			jframe.getContentPane().setPreferredSize(content.getPreferredSize());
		}

		jframe.setResizable(false);

		/**
		 * Set it to be later then setResizable to avoid extra space on right and bottom
		 * - Good Sign 2017/04/09
		 * 
		 * JFrame's size is auto-set here.
		 */
		jframe.pack();
		jframe.setVisible(true);

		// Gently tell the eventhandler to wake up and set itself.
		jframe.requestFocus();
		content.requestFocusInWindow();
	}

	/**
	 * Uninitialize graphics, so it can be reset on the next repaint
	 */
	public void renewGraphics() {
		final Graphics2D localG2d = g2d;
		g2d = null;
		if (localG2d != null) {
			localG2d.dispose();
		}
	}

	/**
	 * Modified update method: no context needs to passed. Will render only internal
	 * screens.
	 */
	public void update() {
		if (!content.isDisplayable()) {
			return;
		}

		/**
		 * Work on a local copy of the stack - global one can become null at any moment
		 */
		final Graphics2D localG2d = getGraphics2D();

		/**
		 * If the game starts too fast, it is possible to raise an exception there We
		 * don't want to bother player with "something bad happened" but we wouldn't
		 * just be quiet either in case of "something really bad happened" - Good Sign
		 * 2017/04/09
		 */
		if (localG2d == null) {
			Loggers.getLogger(ExistingJFrameDoomFrame.class.getName())
					.log(Level.INFO, "Starting or switching fullscreen, have no Graphics2d yet, skipping paint");
		} else {
			draw(g2d, imageSupplier.get(), dim, jframe);
		}
	}

	@Override
	public void setUndecorated(boolean undecorated) {
		jframe.setUndecorated(undecorated);
	}

	@Override
	public void dispose() {
		jframe.dispose();
	}

	/**
	 * Techdemo v1.3: Mac OSX fix, compatible with Windows and Linux. Should
	 * probably run just once. Overhead is minimal compared to actually DRAWING the
	 * stuff.
	 */
	private Graphics2D getGraphics2D() {
		Graphics2D localG2d;
		if ((localG2d = g2d) == null) {
			// add double-checked locking
			synchronized (ExistingJFrameDoomFrame.class) {
				if ((localG2d = g2d) == null) {
					g2d = localG2d = (Graphics2D) content.getGraphics();
					localG2d.setRenderingHint(KEY_ALPHA_INTERPOLATION, VALUE_ALPHA_INTERPOLATION_SPEED);
					localG2d.setRenderingHint(KEY_ANTIALIASING, VALUE_ANTIALIAS_OFF);
					localG2d.setRenderingHint(KEY_RENDERING, VALUE_RENDER_SPEED);
				}
			}
		}

		return localG2d;
	}

	@Override
	public Supplier<? extends Image> getImageSupplier() {
		return imageSupplier;
	}

	private final boolean showFPS = Engine.getCVM().bool(CommandVariable.SHOWFPS);
	private long lastTime = System.currentTimeMillis();
	private int frames = 0;
}
