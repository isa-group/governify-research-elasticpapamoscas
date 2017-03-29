package es.us.isa.papamoscas.proxysla.config;

public class UpRiseSpeed extends Distribution {

    public UpRiseSpeed() {
    	super();
    }
    
    public Double[] getDistribution(Integer levels){
    	
    	Double[] ret = new Double[levels];
    	
    	Double diff = this.max - this.min;
    	Double factor = 1.0 / levels;
    	
    	ret[0] = this.min;
    	for(int l = 1; l < levels; l++ ){
    		ret[l] = this.min  + ( diff * factor * ( l + 1 ) );
    	}     	
    	
    	return ret;
    	
    }

    @Override
    public String toString() {
        return "downRiseSpeed [max=" + max + ", min=" + min
                + ", distribution=" + distribution + "]";
    }
}
