package kz.kcell.apps.common;

import org.junit.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.junit.Assert.assertEquals;

/**
 * @author Pavel.Terechshenkov@kcell.kz
 * @since 13 04 2016
 */
public class TimeUtilTest {

    @Test
    public void less24hourse() throws Exception {
        assert TimeUtil.less24hourse(
                LocalDate.of(2014, 12, 9),
                LocalDate.of(2014, 12, 10)
        ) == false;

        assert TimeUtil.less24hourse(
                LocalDate.of(2014, 12, 10),
                LocalDate.of(2014, 12, 9)
        ) == false;

        assert TimeUtil.less24hourse(
                LocalDate.of(2014, 12, 10),
                LocalDate.of(2014, 12, 10)
        ) == true;

        assert TimeUtil.less24hourse(
                LocalDate.of(2014, 12, 10),
                LocalDate.of(2014, 12, 11)
        ) == false;


    }

    @Test
    public void less24hourse1() throws Exception {
        assert TimeUtil.less24hourse(
                LocalDateTime.of(2014, 12, 10, 6, 40, 45),
                LocalDateTime.of(2014, 12, 10, 7, 45, 55)
        ) == true;

        assert TimeUtil.less24hourse(
                LocalDateTime.of(2014, 12, 10, 7, 45, 55),
                LocalDateTime.of(2014, 12, 10, 6, 40, 45)
        ) == true;

        assert TimeUtil.less24hourse(
                LocalDateTime.of(2014, 12, 10, 6, 40, 45),
                LocalDateTime.of(2014, 12, 11, 6, 40, 44)
        ) == true;

        assert TimeUtil.less24hourse(
                LocalDateTime.of(2014, 12, 10, 6, 40, 45),
                LocalDateTime.of(2014, 12, 11, 6, 40, 45)
        ) == false;

        assert TimeUtil.less24hourse(
                LocalDateTime.of(2014, 12, 10, 6, 40, 45),
                LocalDateTime.of(2014, 12, 11, 7, 45, 55)
        ) == false;
    }

    @Test
    public void hoursElapsedTest() throws Exception {
        assertEquals( TimeUtil.hoursElapsed(
                LocalDateTime.of(2014, 12, 10, 6, 40, 45),
                LocalDateTime.of(2014, 12, 10, 7, 45, 55)
        ),1);

        assertEquals( TimeUtil.hoursElapsed(
                LocalDateTime.of(2014, 12, 10, 7, 45, 55),
                LocalDateTime.of(2014, 12, 10, 6, 40, 45)
        ),-1);

        assertEquals( TimeUtil.hoursElapsed(
                LocalDateTime.of(2014, 12, 10, 6, 40, 45),
                LocalDateTime.of(2014, 12, 11, 6, 40, 44)
        ),23);

        assertEquals( TimeUtil.hoursElapsed(
                LocalDateTime.of(2014, 12, 10, 6, 40, 45),
                LocalDateTime.of(2014, 12, 11, 6, 40, 45)
        ),24);

        assertEquals( TimeUtil.hoursElapsed(
                LocalDateTime.of(2014, 12, 10, 6, 40, 45),
                LocalDateTime.of(2014, 12, 11, 7, 45, 55)
        ),25);

    }

}