package nl.captcha.gimpy;

import java.awt.image.BufferedImage;

import com.jhlabs.image.RippleFilter;
import com.jhlabs.image.TransformFilter;

import static nl.captcha.util.ImageUtil.applyFilter;

/**
 * @author <a href="mailto:james.childers@gmail.com">James Childers</a>
 */
public class RippleGimpyRenderer implements GimpyRenderer {

    /**
     * Apply a RippleFilter to the image.
     * 
     * @param image The image to be distorted
     */
	@Override
    public void gimp(BufferedImage image) {
        RippleFilter filter = new RippleFilter();
//        filter.setWaveType(RippleFilter.SINGLEFRAME);
        filter.setXAmplitude(2.6f);
        filter.setYAmplitude(1.7f);
        filter.setXWavelength(15);
        filter.setYWavelength(5);
        
//        filter.setEdgeAction(TransformFilter.RANDOMPIXELORDER);

//        applyFilter(image, filter);
    }
}