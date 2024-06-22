package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import model.TVManagerImpl;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JSeparator;
import javax.swing.Box;
import javax.swing.border.BevelBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Component;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.Action;

import controller.TVManagerController;
import javax.swing.JComboBox;

public class TVManagerView<Action> extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	public TVManagerImpl manager;
	public JTable table;
	public JTextField textField_id;
	public JTextField textField_Name_2;
	public JTextField textField_price;
	public JTextField textField_total;
	public JTextField textField_size;
	public JTextField textField_brand;
	public JTextField textField_typescreen;
	public JComboBox comboBox;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TVManagerView frame = new TVManagerView();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public TVManagerView() {
		setTitle("TV Manager");
		
		
		this.manager = new TVManagerImpl(); // tạo ra 1 List các TV 
		Action action = (Action) new TVManagerController(this);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 840, 617);

		

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu MenuFile = new JMenu("File");
		menuBar.add(MenuFile);

		JMenuItem menuOpen = new JMenuItem("Open");
		MenuFile.add(menuOpen);

		JSeparator separator_1 = new JSeparator();
		MenuFile.add(separator_1);

		JMenuItem menuClose = new JMenuItem("Close");
		MenuFile.add(menuClose);

		JSeparator separator = new JSeparator();
		MenuFile.add(separator);

		JMenuItem menuExit = new JMenuItem("Exit");
		MenuFile.add(menuExit);

		JMenu menuAbout = new JMenu("About");
		menuBar.add(menuAbout);

		JMenuItem menuItemAbout = new JMenuItem("About Me");
		menuAbout.add(menuItemAbout);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JSeparator separator_2 = new JSeparator();
		separator_2.setBounds(18, 43, 816, 5);
		contentPane.add(separator_2);

		JSeparator separator_4 = new JSeparator();
		separator_4.setBounds(6, 6, 828, 12);
		contentPane.add(separator_4);

		JLabel lblDanhSchTv = new JLabel("Danh sách TV:");
		lblDanhSchTv.setBounds(6, 19, 144, 29);
		contentPane.add(lblDanhSchTv);

		String[] columnNames = { "ID", "Name", "Price", "Total", "Size", "Brand", "Typescreen" };
		DefaultTableModel model = new DefaultTableModel();
		model.setColumnIdentifiers(columnNames);
		table = new JTable(model);

		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(6, 51, 828, 249);
		contentPane.add(scrollPane);

		JSeparator separator_5 = new JSeparator();
		separator_5.setBounds(6, 396, 828, 12);
		contentPane.add(separator_5);

		JLabel lblNewLabel_2 = new JLabel("ID sản phẩm:");
		lblNewLabel_2.setBounds(18, 441, 92, 16);
		contentPane.add(lblNewLabel_2);

		textField_id = new JTextField();
		textField_id.setBounds(105, 436, 130, 26);
		contentPane.add(textField_id);
		textField_id.setColumns(10);

		JLabel lblNewLabel_3 = new JLabel("Thông tin sản phẩm");
		lblNewLabel_3.setBounds(18, 413, 153, 16);
		contentPane.add(lblNewLabel_3);

		JLabel lblNewLabel_4 = new JLabel("Name");
		lblNewLabel_4.setBounds(18, 472, 61, 16);
		contentPane.add(lblNewLabel_4);

		JLabel lblNewLabel_5 = new JLabel("Size");
		lblNewLabel_5.setBounds(481, 441, 61, 16);
		contentPane.add(lblNewLabel_5);

		JLabel lblNewLabel_6 = new JLabel("Price");
		lblNewLabel_6.setBounds(280, 441, 61, 16);
		contentPane.add(lblNewLabel_6);

		JLabel lblNewLabel_7 = new JLabel("Total");
		lblNewLabel_7.setBounds(280, 472, 61, 16);
		contentPane.add(lblNewLabel_7);

		textField_Name_2 = new JTextField();
		textField_Name_2.setBounds(105, 469, 130, 26);
		contentPane.add(textField_Name_2);
		textField_Name_2.setColumns(10);

		textField_price = new JTextField();
		textField_price.setBounds(311, 436, 130, 26);
		contentPane.add(textField_price);
		textField_price.setColumns(10);

		textField_total = new JTextField();
		textField_total.setBounds(311, 467, 130, 26);
		contentPane.add(textField_total);
		textField_total.setColumns(10);

		textField_size = new JTextField();
		textField_size.setBounds(523, 436, 126, 26);
		contentPane.add(textField_size);
		textField_size.setColumns(10);

		JLabel lblNewLabel_8 = new JLabel("Brand ");
		lblNewLabel_8.setBounds(481, 472, 61, 16);
		contentPane.add(lblNewLabel_8);

		JLabel lblNewLabel_9 = new JLabel("Type screen");
		lblNewLabel_9.setBounds(661, 441, 90, 16);
		contentPane.add(lblNewLabel_9);

		textField_brand = new JTextField();
		textField_brand.setBounds(523, 467, 130, 26);
		contentPane.add(textField_brand);
		textField_brand.setColumns(10);

		textField_typescreen = new JTextField();
		textField_typescreen.setBounds(742, 436, 92, 26);
		contentPane.add(textField_typescreen);
		textField_typescreen.setColumns(10);

		JButton btnThem = new JButton("Thêm");
		btnThem.addActionListener((ActionListener) action);
		btnThem.setBounds(6, 518, 117, 29);
		contentPane.add(btnThem);

		JButton btnSua = new JButton("Sửa");
		btnSua.addActionListener((ActionListener) action);
		btnSua.setBounds(173, 518, 117, 29);
		contentPane.add(btnSua);

		JButton btnXoa = new JButton("Xoá");
		btnXoa.addActionListener((ActionListener) action);
		btnXoa.setBounds(334, 518, 117, 29);
		contentPane.add(btnXoa);

		JButton btnSapxep = new JButton("Sắp Xếp");
		btnSapxep.addActionListener((ActionListener) action);
		btnSapxep.setBounds(717, 312, 117, 29);
		contentPane.add(btnSapxep);

		JButton btnHuy = new JButton("Huỷ");
		btnHuy.addActionListener((ActionListener) action);
		btnHuy.setBounds(693, 518, 117, 29);
		contentPane.add(btnHuy);

		JButton btnLuu = new JButton("Lưu");
		btnLuu.addActionListener((ActionListener) action);
		btnLuu.setBounds(509, 518, 117, 29);
		contentPane.add(btnLuu);

		JButton btnshow = new JButton("Hiển thị");
		btnshow.addActionListener((ActionListener) action);
		btnshow.setBounds(106, 19, 117, 29);
		contentPane.add(btnshow);

		String waytoSearch[] = { "Name", "Price", "Size", "Type screeen", "Brand" };
		comboBox = new JComboBox(waytoSearch);
		comboBox.setBounds(130, 313, 93, 27);
		contentPane.add(comboBox);

		JButton btnTimkiem_1 = new JButton("Tìm kiếm");
		btnTimkiem_1.addActionListener((ActionListener) action);
		btnTimkiem_1.setBounds(6, 312, 117, 29);
		contentPane.add(btnTimkiem_1);

		this.setVisible(true);
	}

	public void xoaForm() {
		// TODO Auto-generated method stub
		textField_id.setText("");
		textField_Name_2.setText("");
		textField_price.setText("");
		textField_size.setText("");
		textField_total.setText("");
		textField_brand.setText("");
		textField_typescreen.setText("");
	}
}
