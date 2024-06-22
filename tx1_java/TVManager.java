package tx1_java;

import java.util.List;

public interface TVManager {
	public boolean addTV(TV t);
	public boolean editTV(TV t);
	public boolean delTV(TV t);
	public List<TV>	searchTV(String name);
	public List<TV> sortedTV(double price);
}
