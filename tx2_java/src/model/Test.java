package model;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.*;

public class Test {
	private static List<TV> list;

	public void generateList(int n) {
		TV m ;
		HashMap<Integer, TV> ids = new HashMap<>();
		
		Test.list = new ArrayList<>();
		
		String[] name = { "QN90A Neo", "OLED C1", "XR A80J", "TCL 6-Series Roku TV (R635)", "P-Series Quantum X",
				"Hisense U8G", "Philips 806" };
		String[] typescreen = { "4k,'HD", "LCD", "OLED" };
		
		String[] manufacturer = { "Samsung", "LG", "xiaomi", "Sony", "Bravia", "Vizio" };
		
		int cnt;
		while (list.size() < n) {
			
			cnt = (int) (Math.random() * 100);
			if (!ids.containsKey(cnt)) {
				m = new TV();
				m.setProduct_id(cnt);
				ids.put(cnt, m);
				
				cnt = (int) (Math.random() * (name.length));
				m.setProduct_name(name[cnt]);
				
				m.setProduct_price((int) (Math.random() * 100000));
				
				m.setProduct_total((int) (Math.random() * 5) * 10);
				
				cnt = (int) (Math.random() * manufacturer.length);
				m.setTV_brand(manufacturer[cnt]);
				
				m.setTV_size((int) (Math.random() * 60) + "inch");
				
				m.setTV_typescreen(typescreen[(int) (Math.random() * typescreen.length)]);
				Test.list.add(m);

			}
		}

	}

	public void printList() {
		Test.list.forEach(go -> {
			System.out.println(go);
		});
	}

	public void printList(List<TV> list) {
		list.forEach(go -> {
			System.out.println(go);
		});
	}

	public static void main(String[] args) {
		Test o1 = new Test();
		System.out.println("Danh sách TV là :");
		o1.generateList(5);
		o1.printList();
		System.out.println("--------------------------------------");
		try {
			FileOutputStream of = new FileOutputStream("TV.bin");
			ObjectOutputStream out = new ObjectOutputStream(of);
			out.writeInt(2); // số lượng bản ghi trợ giúp đọc
			out.writeUTF("Danh sách TV là :");
			for(TV t : list) {
				out.writeObject(t);
			}
			
			
			// khai báo 1 TV mẫu để test các chức năng 
			TV x = new TV(6, "SamSung mac51", 5000000, 10, "50 inch", "samsung", "4k") ;
			
			TVManagerImpl manager = new TVManagerImpl(list);
			
			if(!manager.sortedTV(0).isEmpty()) {
				// in ra manf hinh
				System.out.println("Danh sách TV sau khi sắp xếp là :");
				o1.printList(list);
				System.out.println("--------------------------------------");
				
				// viet va file 
				
				out.writeUTF("Danh sách TV sau khi sắp xếp là :");
				for(TV t : list) {
					out.writeObject(t);
				}
				out.writeUTF("--------------------------------------");
			}else {
				System.out.println("Sắp xếp bị lỗi!");
			}
			
			if(manager.addTV(x)) {
				System.out.println("Danh sách TV sau khi thêm theo giá là :");
				o1.printList(list);
				System.out.println("--------------------------------------");
				out.writeUTF("Danh sách TV sau khi thêm là :");
				for(TV t : list) {
					out.writeObject(t);
				}
				out.writeUTF("--------------------------------------");
			}else {
				System.out.println("Không thêm được!");
			}

			// search 
			System.out.println("Ket qua tim kiem:");
			List<TV> results = manager.searchTV("SamSung mac51");
			o1.printList(results);
			System.out.println("--------------------------------------");
			out.writeUTF("Danh sách TV sau khi tìm kiếm là :");
			for(TV t : results) {
				out.writeObject(t);
			}
			out.writeUTF("--------------------------------------");
			// edit TV 
			// khai bao tv can sua 
			TV k = new TV(6, "SamSung mac51", 3000000, 10, "50 inch", "samsung", "4k");
			
			if(manager.editTV(k)) {
				System.out.println("Danh sách sau khi sửa là:");
				o1.printList(list);
				System.out.println("--------------------------------------");
				out.writeUTF("Danh sách TV sau khi sửa là :");
				for(TV t : list) {
					out.writeObject(t);
				}
				out.writeUTF("--------------------------------------");
			}else {
				System.out.println("Sửa bị lỗi !");
			}
			
			// xoá TV 
			TV t = new TV(6, "SamSung mac51", 3000000, 10, "50 inch", "samsung", "4k");

			if(manager.delTV(t)) {
				System.out.println("Danh sách sau khi xoá là:");
				o1.printList(list);
				System.out.println("--------------------------------------");
				out.writeUTF("Danh sách TV sau khi xoá là :");
				for(TV tv : list) {
					out.writeObject(t);
				}
				out.writeUTF("--------------------------------------");
			}else {
				System.out.println("Xoá bị lỗi !");
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

}
