package model;

import java.io.Serializable;

public class TV extends Product implements Serializable {
    private String TV_size;
    private String TV_brand;
    private String TV_typescreen;

    public TV() {
    	
    }

 
    public TV(int product_id, String product_name, double product_price, int product_total, String TV_size,
            String TV_brand, String TV_typescreen) {
        super(product_id, product_name, product_price, product_total);
        this.TV_size = TV_size;
        this.TV_brand = TV_brand;
        this.TV_typescreen = TV_typescreen;
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

    public void setTV_size(String TV_size) {
        this.TV_size = TV_size;
    }

    public void setTV_brand(String TV_brand) {
        this.TV_brand = TV_brand;
    }

    public void setTV_typescreen(String TV_typescreen) {
        this.TV_typescreen = TV_typescreen;
    }

    @Override
    public String toString() {
        return "TV [" + super.toString() + ", size=" + TV_size + ", brand=" + TV_brand + ", typescreen=" + TV_typescreen + "]";
    }
}
