package kz.kcell.apps.common.msisdn;

/**
 * @author Pavel.Terechshenkov@kcell.kz
 * @since 06 10 2014
 */
public class FastMsisdn extends Msisdn {

    private PartsStruct partsStruct = new PartsStruct();

    public FastMsisdn(String value) {
        super.Msisdn(value);
    }

    public  FastMsisdn(String value, Locale locale) {
        super.Msisdn(value, locale);
    }

    @Override
    protected void sanitize() {
        super.sanitize();
        if(locale.equals(Locale.KZ) || locale.equals(Locale.RU)) {
            if (value.length() == 10) {
                value = "7"+value;
            } else if(value.length() == 11) {
                if(value.charAt(0) == '8') {
                    value = "7" + value.substring(1);
                } else if(value.charAt(0) != '7') {
                    throw new IllegalFormatMsisdnException(value,"First number must 7");
                }
            }
        }
    }

    @Override
    protected void validate() {
        super.validate();
        parseByPart();
        // todo доделать
    }

    private void  parseByPart()  {
        partsStruct.cc = value.substring(0,locale.cc);
        partsStruct.ndc = value.substring(locale.cc,locale.cc+locale.ndc);
        partsStruct.sn = value.substring(locale.cc+locale.ndc,locale.cc+locale.ndc+locale.sn);
    }

    @Override
    public String getCC() {
        return partsStruct.cc;
    }

    @Override
    public String getNDC() {
        return partsStruct.ndc;
    }

    @Override
    public String getSN() {
        return partsStruct.sn;
    }

    public static FastMsisdn of(String msisdn) {
        return new FastMsisdn(msisdn);
    }

}
