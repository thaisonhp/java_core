package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import model.TV;
import view.TVManagerView;

public class TVManagerController implements ActionListener {
	JFileChooser fc = new JFileChooser();
	private TVManagerView view;

	public TVManagerController(TVManagerView view) {
		this.view = view;
	}

	// Lấy thông tin từ bảng thông tin sản phẩm
	private TV createTVFromInput() {
		int id = Integer.parseInt(this.view.textField_id.getText());
		String name = this.view.textField_Name_2.getText();
		double price = Double.parseDouble(this.view.textField_price.getText());
		int total = Integer.parseInt(this.view.textField_total.getText());
		String size = this.view.textField_size.getText();
		String brand = this.view.textField_brand.getText();
		String typescreen = this.view.textField_typescreen.getText();
		return new TV(id, name, price, total, size, brand, typescreen);
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		String cm = e.getActionCommand();
		JOptionPane.showMessageDialog(view, "Bạn vừa nhấn vào " + cm);
		DefaultTableModel model = (DefaultTableModel) this.view.table.getModel();

		switch (cm) {
		case "Thêm":
			TV t = createTVFromInput();
			if (this.view.manager.addTV(t)) {
				Object[] rowData = { t.getProduct_id(), t.getProduct_name(), t.getProduct_price(), t.getProduct_total(),
						t.getTV_size(), t.getTV_brand(), t.getTV_typescreen() };
				model.addRow(rowData);
				model.fireTableDataChanged();
			}
			break;

		case "Sửa":
			TV t_sua = createTVFromInput();
			if (this.view.manager.editTV(t_sua)) {
				// Thêm lại mục đã sửa
				model.fireTableDataChanged();
				refreshTable(model); // Cập nhật lại bảng sau khi sửa
				JOptionPane.showMessageDialog(view, "Sửa thành công!");
			} else {
				JOptionPane.showMessageDialog(view, "Sửa không thành công!");
			}

			break;

		case "Xoá":
			int selectedRow = this.view.table.getSelectedRow();
			if (selectedRow >= 0) {
				int id = (int) model.getValueAt(selectedRow, 0);
				TV targetTV = this.view.manager.getListTV().stream().filter(tv -> tv.getProduct_id() == id).findFirst()
						.orElse(null);

				if (targetTV != null) {
					this.view.manager.delTV(targetTV);
					model.removeRow(selectedRow);
					model.fireTableDataChanged();
					JOptionPane.showMessageDialog(view, "Xoá thành công!");
				} else {
					JOptionPane.showMessageDialog(view, "Không tìm thấy TV để xóa.");
				}
			} else {
				JOptionPane.showMessageDialog(view, "Vui lòng chọn một hàng để xóa.");
			}
			break;

		case "Tìm kiếm":
			String[] collectionWayToSearch = { "Name", "Price", "Size", "Type screen", "Brand" };
			int index = this.view.comboBox.getSelectedIndex();
			String searchValue = JOptionPane.showInputDialog(view,
					"Nhập giá trị để tìm kiếm theo " + collectionWayToSearch[index] + ":");

			if (searchValue != null && !searchValue.trim().isEmpty()) {
				List<TV> result = this.view.manager.searchTV(searchValue.strip());
				model.setRowCount(0); // Xóa tất cả các hàng hiện có
				for (TV tv : result) {
					Object[] rowData = { tv.getProduct_id(), tv.getProduct_name(), tv.getProduct_price(),
							tv.getProduct_total(), tv.getTV_size(), tv.getTV_brand(), tv.getTV_typescreen() };
					model.addRow(rowData);
				}
			}
//                System.out.println(collectionWayToSearch[index]);
			// tìm thấy các tv đúng yêu cầu

			// xoá các thành phần hiện có
			// thêm mới
			break;

		case "Lưu":
			try {
				FileOutputStream of = new FileOutputStream("TV.bin");
				ObjectOutputStream out = new ObjectOutputStream(of);
				out.writeInt(2); // số lượng bản ghi trợ giúp đọc
				// Lặp qua từng hàng trong bảng
				for (int i = 0; i < model.getRowCount(); i++) {
					// Lấy giá trị từng ô trong hàng
					int id = (int) model.getValueAt(i, 0);
					String name = (String) model.getValueAt(i, 1);
					double price = (double) model.getValueAt(i, 2);
					int total = (int) model.getValueAt(i, 3);
					String size = (String) model.getValueAt(i, 4);
					String brand = (String) model.getValueAt(i, 5);
					String typescreen = (String) model.getValueAt(i, 6);
					// Tạo đối tượng TV từ các giá trị lấy được
					TV tv = new TV(id, name, price, total, size, brand, typescreen);
					// Ghi đối tượng TV vào file
					out.writeObject(tv);
				}
				out.close();
				JOptionPane.showMessageDialog(view, "Lưu File thành công!");
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			break;

		case "Hiển thị":
			if (this.view.manager.getListTV().isEmpty()) {
				this.view.manager.generateList();
				for (TV tv : this.view.manager.getListTV()) {
					int id = tv.getProduct_id();
					String name = tv.getProduct_name();
					double price = tv.getProduct_price();
					int total = tv.getProduct_total();
					String size = tv.getTV_size();
					String brand = tv.getTV_brand();
					String typescreen = tv.getTV_typescreen();

					// Thêm hàng mới
					Object[] rowData = { id, name, price, total, size, brand, typescreen };
					model.addRow(rowData);
				}
				// Thông báo cho JTable rằng dữ liệu đã được cập nhật
				model.fireTableDataChanged();
			} else {
				refreshTable(model);
			}
			break;
		case "Huỷ":
			this.view.xoaForm();
			this.view.manager.setLuachon("Huỷ");
			break;
		case "Sắp Xếp":
//            	System.out.println("Da chon sap xep");
//            	this.view.manager.setListTV();
			List<TV> result = this.view.manager.sortedTV(0);
//            	this.view.manager.printList(result);
			refreshTable(model);
		}
	}

	private int findRowIndexById(int id, DefaultTableModel model) {
		for (int i = 0; i < model.getRowCount(); i++) {
			if ((int) model.getValueAt(i, 0) == id) {
				return i;
			}
		}
		return -1;
	}

	private void refreshTable(DefaultTableModel model) {
		model.setRowCount(0); // Xóa tất cả các hàng hiện có
		for (TV tv : this.view.manager.getListTV()) {
			Object[] rowData = { tv.getProduct_id(), tv.getProduct_name(), tv.getProduct_price(), tv.getProduct_total(),
					tv.getTV_size(), tv.getTV_brand(), tv.getTV_typescreen() };
			model.addRow(rowData);
		}
		model.fireTableDataChanged();
	}
}
