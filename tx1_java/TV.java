package tx1_java;

public class TV extends Product {
	private String TV_size ; 
	private String TV_brand ;
	private String TV_typescreen ;
	
	
	public TV() {
		super();
	}


	public TV(int product_id, String product_name, double product_price, int product_total, String tV_size,
			String tV_brand, String tV_typescreen) {
		super(product_id, product_name, product_price, product_total);
		TV_size = tV_size;
		TV_brand = tV_brand;
		TV_typescreen = tV_typescreen;
	}


	public String getTV_size() {
		return TV_size;
	}


	public String getTV_brand() {
		return TV_brand;
	}


	public String getTV_typescreen() {
		return TV_typescreen;
	}


	public void setTV_size(String tV_size) {
		TV_size = tV_size;
	}


	public void setTV_brand(String tV_brand) {
		TV_brand = tV_brand;
	}


	public void setTV_typescreen(String tV_typescreen) {
		TV_typescreen = tV_typescreen;
	}


	@Override
	public String toString() {
		return "TV [" + super.toString() + "size =" + TV_size + ", brand = " + TV_brand + ", typescreen=" + TV_typescreen + "]";
	}
	
	
	
	
}
