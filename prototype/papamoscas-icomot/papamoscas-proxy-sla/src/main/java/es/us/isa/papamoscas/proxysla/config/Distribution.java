/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.us.isa.papamoscas.proxysla.config;

/**
 *
 * @author quique
 */
public class Distribution {

    protected String distribution;
    protected Double min;
    protected Double max;

    public String getType() {
        return distribution;
    }

    public void setType(String distribution) {
        this.distribution = distribution;
    }

    public void setMin(Double min) {
        this.min = min;
    }

    public void setMax(Double max) {
        this.max = max;
    }

    public Double getMin() {
        return min;
    }

    public Double getMax() {
        return max;
    }
    
    public Double[] getDistribution(Integer levels){
    	
    	Double[] ret = new Double[levels];
    	
    	Double diff = this.max - this.min;
    	Double factor = 1.0 / levels;
    	
    	for(int l = 0; l < levels; l++ ){
    		ret[l] = Math.floor(this.min + ( diff * factor * ( l +1 ) ));
    	}    	
    	
    	return ret;
    	
    }
}
