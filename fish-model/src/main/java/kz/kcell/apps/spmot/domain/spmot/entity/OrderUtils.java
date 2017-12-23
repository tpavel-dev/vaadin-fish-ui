package kz.kcell.apps.spmot.domain.spmot.entity;

import java.sql.Timestamp;
import java.util.Date;

/**
 * Created by x on 7/12/17.
 */
public class OrderUtils {

    public static boolean isOrderActivated(SubscribeDropNotice order) {
        return order.getStatus() != null && order.getStatus() == SubscribeStatus.ACTIVATED;
    }

    public static boolean isOrderActivated(Subscribe order) {
        return order.getStatus() != null && order.getStatus() == SubscribeStatus.ACTIVATED;
    }

    public static boolean isOrderExpired(SubscribeDropNotice order) {

        return order.getStatus() != SubscribeStatus.ACTIVATED && order.getEndDate().before(new Timestamp(System.currentTimeMillis()));
    }

    public static boolean isOrderExpired(Subscribe order) {
        return order.getStatus() != SubscribeStatus.ACTIVATED && order.getEndDate().before(new Timestamp(System.currentTimeMillis()));
    }


}
