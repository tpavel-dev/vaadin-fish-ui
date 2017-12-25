package kz.kcell.apps.bonus_cmdr.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 *   create table COMPANY_MSISDN_SET_STATUS
 *   (
 *   	ID
 *   	CID NUMBER(19) not null,
 *   	BID NUMBER(19) not null,
 *   	EXE_ORDER NUMBER(3) not null,
 *   	MSISDN VARCHAR2(11),
 *   	BRAND CHAR,
 *   	STATUS VARCHAR2(20)
 *   )
 *
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MsisdnSetItem {
    private Long sid;
    private Long cid;
    private Long bid;
    private Short exeOrder;
    private String msisdn;
    private String brand;
}
