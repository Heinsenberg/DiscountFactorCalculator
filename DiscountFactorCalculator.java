import java.util.TreeMap;
import java.util.HashMap;
import java.util.Set; 

public class DiscountFactorCalculator {
	
	private static String DAYCOUNT_CONVENTION = "DAYCOUNT_CONVENTION";
	private static String COMPOUNDING_CONVENTION = "CONTINOUS";
	
	
	public DiscountFactorCalculator(TreeMap<Integer,Double> _depoRates) {
		m_depoRates = _depoRates;
		m_discountFactors = new TreeMap<Integer,Double>();
	}
		
	public void setParams(HashMap<String,String> _params) {
		
		m_params = _params.isEmpty() ? useDefaults() : _params;
		
	}
	
	public void calculateDiscountFactors() {
		Set<Integer> keys = m_depoRates.keySet();
		Double discountFactor = 0.0 ;
		
		for(Integer key : keys) {
			discountFactor = calculateDiscountFactor(key,m_depoRates.get(key));
			m_discountFactors.put(key,discountFactor);
		}
	}

	
	public TreeMap<Integer,Double> getDiscountFactors(){
		return m_discountFactors;
	}
	
	private Double calculateAnnualTime(Integer _timeInDays) {
		String daycountConvention = m_params.containsKey(DAYCOUNT_CONVENTION) ? m_params.get(DAYCOUNT_CONVENTION) : useDefaults().get(DAYCOUNT_CONVENTION) ;
		
		String[] parts = daycountConvention.split("/");
		Double yearDayCount = Double.parseDouble(parts[1]);
		Double tau = _timeInDays / yearDayCount;
		
		return tau;
		
	}
	
	private Double calculateDiscountFactor(Integer _time, Double _depoRate) {
		Double df = Math.exp(-_depoRate*calculateAnnualTime(_time));
		return df;
	}
	
	private HashMap<String,String> useDefaults() {
		HashMap<String,String> defaultParams = new HashMap<String,String>();
		defaultParams.put(DAYCOUNT_CONVENTION, "ACT/365");
		return defaultParams;
	}
	
	private TreeMap<Integer,Double> m_depoRates;
	private TreeMap<Integer,Double> m_discountFactors;
	private HashMap<String,String> m_params;
	
}
