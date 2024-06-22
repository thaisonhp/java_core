package tx1_java;

import java.util.*;


public class TVManagerImpl implements TVManager {
	private List<TV> ListTV ;

	public TVManagerImpl() {
		ListTV = new ArrayList<>();
	}

	public TVManagerImpl(List<TV> ListTV) {
		this.ListTV = ListTV;
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
		for (Product item : this.ListTV) {
			if (item.getProduct_id() == t.getProduct_id()) {
				this.ListTV.remove(item);
				this.ListTV.add(t);
				System.out.println("Đã sửa thành công!");
				return true;
			}
		}
		return false;
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
		return result ;
	}

	@Override
	public List<TV> searchTV(String name) {
		// TODO Auto-generated method stub
		List<TV> results = new ArrayList<>();
		for(TV i : this.ListTV) {
			if(i.getProduct_name().contains(name)) {
				results.add(i);
			}
		}
		return results ;
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

}
