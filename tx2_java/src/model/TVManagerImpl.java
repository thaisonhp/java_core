package model;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.*;

public class TVManagerImpl implements TVManager {
	private List<TV> ListTV;
	private String luachon;

	public TVManagerImpl() {
		ListTV = new ArrayList<>();
		this.luachon = "";
	}

	public TVManagerImpl(List<TV> ListTV) {
		this.ListTV = ListTV;
		this.luachon = "";
	}

	public void generateList() {
		TV m;
		HashMap<Integer, TV> ids = new HashMap<>();

		String[] name = { "QN90A Neo", "OLED C1", "XR A80J", "TCL 6-Series Roku TV (R635)", "P-Series Quantum X",
				"Hisense U8G", "Philips 806" };
		String[] typescreen = { "4k,'HD", "LCD", "OLED" };

		String[] manufacturer = { "Samsung", "LG", "xiaomi", "Sony", "Bravia", "Vizio" };

		int cnt;
		while (this.ListTV.size() < 5) {

			cnt = (int) (Math.random() * 5);
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
				ListTV.add(m);

			}
		}

	}

	public List<TV> getListTV() {
		return ListTV;
	}

	public void setListTV(List<TV> listTV) {
		ListTV = listTV;
	}

	@Override
	public boolean addTV(TV t) {
		// TODO Auto-generated method stub
		boolean flag = false;
		// kiem tra xem id ton tai chua
		for (Product item : this.ListTV) {
			if (item.getProduct_id() == t.getProduct_id()) {
				flag = true;
				break;
			}
		}
		// sau khi kiểm tra thấy id chưa tồn tại thì thêm
		if (!flag) {
			this.ListTV.add(t);
			return true;
		} else
			return false;
	}

	@Override
	public boolean editTV(TV t) {
		// TODO Auto-generated method stub
		for (TV item : this.ListTV) {
			if (item.getProduct_id() == t.getProduct_id()) {
				item.setProduct_id(t.product_id);
				item.setProduct_name(t.getProduct_name());
				item.setProduct_price(t.getProduct_price());
				item.setProduct_total(t.getProduct_total());
				item.setTV_brand(t.getTV_brand());
				item.setTV_size(t.getTV_size());
				item.setTV_typescreen(t.getTV_typescreen());

				return true;
			}
		}
		return false;
	}

	public String getLuachon() {
		return luachon;
	}

	public void setLuachon(String luachon) {
		this.luachon = luachon;
	}

	@Override
	public boolean delTV(TV t) {
		// TODO Auto-generated method stub
		boolean result = false;
		// kiem tra xem id ton tai chua
		for (Product item : this.ListTV) {
			if (item.getProduct_id() == t.getProduct_id()) {
				this.ListTV.remove(item);
				result = true;
				break;
			}
		}
		return result;
	}

	@Override
	public List<TV> searchTV(String name) {
		// TODO Auto-generated method stub
		List<TV> results = new ArrayList<>();
		for (TV tv : this.ListTV) {
			if (tv.getProduct_name().contains(name) || String.valueOf(tv.getProduct_price()).contains(name)
					|| tv.getTV_size().contains(name) || tv.getTV_brand().contains(name)
					|| tv.getTV_typescreen().contains(name)) {
				results.add(tv);
			}
		}
		return results;
	}

	@Override
	public List<TV> sortedTV(double price) {
		// TODO Auto-generated method stub
		Collections.sort(ListTV, new Comparator<TV>() {

			@Override
			public int compare(TV o1, TV o2) {
				// TODO Auto-generated method stub
				double result = o1.product_price - o2.product_price;
				if (result > 0) {
					return 1;
				} else if (result < 0) {
					return -1;
				} else
					return 0;
			}
		});
		return this.ListTV;
	}

	public void printList() {
		this.ListTV.forEach(go -> {
			System.out.println(go);
		});
	}

	public void printList(List<TV> list) {
		list.forEach(go -> {
			System.out.println(go);
		});
	}

}
