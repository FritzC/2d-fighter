import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;

public class Main {

	private final Object lock = new Object();
	private boolean isRunning = false;

	private int frameWidth = 800;
	private int frameHeight = 600;

	private Thread glThread;

	public static void main(String[] args) {
		new Main().runTester();
	}

	private void runTester() {
		final JFrame frame = new JFrame("Title");
		frame.setSize(frameWidth, frameHeight);
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent we) {
				int result = JOptionPane.showConfirmDialog(frame,
						"Do you want to quit?");
				if (result == JOptionPane.OK_OPTION) {
					frame.setVisible(false);
					frame.dispose();
				}
			}
		});

		Canvas canvas = new Canvas() {
			@Override
			public void addNotify() {
				super.addNotify();
				startGL();
			}

			@Override
			public void removeNotify() {
				stopGL();
				super.removeNotify();
			}
		};
		canvas.setPreferredSize(new Dimension(frameWidth, frameHeight));
		canvas.setIgnoreRepaint(true);

		try {
			Display.setParent(canvas);
		} catch (LWJGLException e) {
			e.printStackTrace();
		}
		JPanel canvasPanel = new JPanel();
		canvasPanel.add(canvas);

		// frame.pack();
		frame.setVisible(true);
	}

	private void startGL() {
		glThread = new Thread(new Runnable() {
			@Override
			public void run() {
				setIsRunning(true);
				DisplayManager.createDisplay(frameWidth, frameHeight);
				while (isRunning()) {
					DisplayManager.updateDisplay();
				}
				Display.destroy();
			}
		}, "LWJGL Thread");
		glThread.start();
	}

	private void stopGL() {
		setIsRunning(false);
		try {
			glThread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	private void setIsRunning(boolean isRunning) {
		synchronized (lock) {
			this.isRunning = isRunning;
		}
	}

	private boolean isRunning() {
		synchronized (lock) {
			return isRunning;
		}
	}

}