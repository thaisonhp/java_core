package test;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import view.TVManagerView;

public class Test_view {
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			new TVManagerView() ;
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
