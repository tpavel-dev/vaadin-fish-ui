package kz.kcell.apps.bonus_cmdr.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MsisdnItem {
    private Long sid;
    private Long cid;
    private String msisdn;
    private String errorMessage;
    private String brand;
    private String rawLine;
    private String status;
    private boolean invalidate =false;

    public MsisdnItem(String msisdn, String brend) {
        this.msisdn = msisdn;
        this.brand = brend;
    }

//    public void setRawLine(String s) {
//        rawLine = StringUtils.substring(s, 0, 100);
//        invalidate = true;
//    }

    public static MsisdnItem buildError(String errorMessage, int lineNumber, String rawLine) {
        MsisdnItem msisdnItemWithError = new MsisdnItem();
        msisdnItemWithError.rawLine = StringUtils.substring(rawLine, 0, 100);
        msisdnItemWithError.invalidate = true;
        msisdnItemWithError.errorMessage = "line: "+lineNumber+" "+ errorMessage;

        return msisdnItemWithError;
    }
}
