package kz.kcell.apps.fish.mobile.vaadin;

import com.vaadin.server.BootstrapFragmentResponse;
import com.vaadin.server.BootstrapListener;
import com.vaadin.server.BootstrapPageResponse;
import org.jsoup.nodes.Element;

/**
 * @author Pavel.Terechshenkov@kcell.kz
 * @since 19 12 2014
 */
public class SpmotBootstrapListener implements BootstrapListener {

    @Override
    public void modifyBootstrapPage(final BootstrapPageResponse response) {
        final Element head = response.getDocument().head();
        head.appendElement("meta")
                .attr("name", "viewport")
                .attr("content", "width=device-width, initial-scale=1");

        head.appendElement("meta")
                .attr("name", "apple-mobile-web-app-capable")
                .attr("content", "yes");

        head.appendElement("meta")
                .attr("name", "apple-mobile-web-app-status-bar-style")
                .attr("content", "black");

        head.appendElement("meta")
                .attr("name", "theme-color")
                .attr("content", "#73369e");
    }

    @Override
    public void modifyBootstrapFragment(final BootstrapFragmentResponse response) {
        // TODO Auto-generated method stub
    }

}
