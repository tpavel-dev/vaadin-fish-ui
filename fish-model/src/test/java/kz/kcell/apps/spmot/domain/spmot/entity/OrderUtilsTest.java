package kz.kcell.apps.spmot.domain.spmot.entity;

import org.junit.Test;

import java.sql.Timestamp;

import static org.junit.Assert.*;

/**
 * Created by x on 7/12/17.
 */
public class OrderUtilsTest {
    @Test
    public void isOrderExpired() throws Exception {
        Subscribe order = new Subscribe();
        order.setEndDate(new Timestamp(System.currentTimeMillis()));
        Thread.sleep(2000);
        assert OrderUtils.isOrderExpired(order) == true;
    }

    @Test
    public void isOrderExpired1() throws Exception {
        SubscribeDropNotice order = new SubscribeDropNotice();
        order.setEndDate(new Timestamp(System.currentTimeMillis()));
        Thread.sleep(2000);
        assert OrderUtils.isOrderExpired(order) == true;

    }

}