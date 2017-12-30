import java.util.TreeMap;
import java.util.HashMap;
import java.util.Set; 

public class test {

	public static void main(String[] args) {
		
		TreeMap<Integer,Double> depoRates = new TreeMap<Integer,Double>();
		HashMap<String,String> params = new HashMap<String,String>();
		
		depoRates.put(10, 0.00);		
		depoRates.put(5, 0.00);
		depoRates.put(300, 0.1);
		
		params.put("DAYCOUNT_CONVENTION", "ACT/365");
		
		DiscountFactorCalculator dfCalc = new DiscountFactorCalculator(depoRates);
		dfCalc.setParams(params);
		dfCalc.calculateDiscountFactors();
		
		TreeMap<Integer, Double> dfs = dfCalc.getDiscountFactors();
		Set<Integer> keys = dfs.keySet();
		
		for(Integer key : keys) {
			System.out.println(dfs.get(key));
		}
	}

}
