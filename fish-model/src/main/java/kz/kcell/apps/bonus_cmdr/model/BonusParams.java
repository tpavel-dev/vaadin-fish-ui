package kz.kcell.apps.bonus_cmdr.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

//import javax.persistence.Column;
//import javax.persistence.Entity;
//import javax.persistence.Id;
//import javax.persistence.Table;

/**
 *  create table COMPANY_BONUS_PARAMS
 * (
 *         BID NUMBER not null,
 *         CID NUMBER not null,
 *         EXE_ORDER NUMBER not null,
 *         ALLOWANCE_ID VARCHAR2(200) not null,
 *         ALLOWANCE_QUOTA NUMBER,
 *         ALLOWANCE_START_DATE DATE not null,
 *         ALLOWANCE_END_DATE DATE not null,
 *         ORGA_BALANCE_NAME VARCHAR2(100),
 *         ORGA_POCKET_NAME VARCHAR2(100),
 *         ORGA_AMOUNT NUMBER,
 *         ORGA_START_DATE DATE,
 *         ORGA_END_DATE DATE,
 *         ORGA_COMMENT VARCHAR2(200)
 * )
 *
 *
 *  public interface AllowanceServices {
 *      void grantAllowance(@NotBlank String msisdn,
 *                          @NotBlank String allowanceId,
 *                          Double quota,
 *                          LocalDateTime startDate,
 *                          LocalDateTime endDate) throws CodaException;
 *
 *  @Override
 *      @WebMethod
 *      @RolesAllowed({"INTERNAL_ROLE", "coda_give_bonus", "coda_update_balance"})
 *      public void updateBalance(
 *               String msisdn,
 *               String balanceName,
 *               String pocketName,
 *               String amount,
 *               String startDate,
 *               String expDate,
 *               String comment
 *     )
 *
 */
@Data
@Builder
//@Entity
//@Table(name = "companty_bonus_params")
@NoArgsConstructor
@AllArgsConstructor
public class BonusParams {

//    @Id
    private Long cid;
    private Long bid;

//    @Column
    private Short exeOrder;


    private String        allowanceId;
    private Double        allowanceQuota;
    private LocalDateTime allowanceStartDate;
    private LocalDateTime allowanceEndDate;

    private  String           orgaBalanceName;
    private  String           orgaPocketName;
    private  Double           orgaAmount;
    private  LocalDateTime    orgaStartDate;
    private  LocalDateTime    orgaExpDate;
    private  String           orgaComment;


}


