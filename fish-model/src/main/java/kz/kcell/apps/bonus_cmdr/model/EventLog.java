package kz.kcell.apps.bonus_cmdr.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * @author Pavel.Terechshenkov@kcell.kz
 * @since 08 12 2015
 */

@Builder
public class EventLog {
    @Getter @Setter private Date eventTime;
    @Getter @Setter private String msisdn;
    @Getter @Setter private String value;
    @Getter @Setter private String PARAM_ID;
    @Getter @Setter private String EVENTTYPES_ID;
}
