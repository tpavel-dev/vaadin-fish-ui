package kz.kcell.apps.common.msisdn;

import kz.kcell.apps.common.Strings;

/**
 * MSISDN (Mobile Subscriber Integrated Services Digital Number)  — номер мобильного абонента цифровой сети
 * с интеграцией служб[1] для связи в стандартах GSM, UMTS и пр. Данный номер абонента не содержится на SIM-карте,
 * а сопоставлен с IMSI SIM-карты в HLR, и предназначается для передачи номера телефона назначенному абоненту
 * и для получения звонков на телефон[2]. Главный MSISDN номер используется для идентификации абонента
 * при предоставлении большинства услуг и может быть изменен без замены SIM-карты.
 * Возможно также сопоставить SIM-карте несколько дополнительных MSISDN для работы с факсимильной связью
 * и передачи данных. MSISDN входит в состав долговременных данных, хранящихся в HLR и VLR[3].
 *
 * MSISDN, как и IMSI, может достигать 15 цифр и в соответствии с E.164 состоит из трех частей:
 * кода страны (CC — Country Code),
 * национального кода направления (NDC — National Destination Code)
 * и номера абонента (SN — subscriber number).
 * При этом конкретные длины составляющих частей регулируются международным и локальным законодательствами, например:
 *
 * Россия и Казахстан:
 *  CC=1 цифра (7), NDC=3 цифры (например, 903), SN=7 цифр (1234567)
 *  , итого — 11 цифр (итоговый пример: 7-903-1234567).
 * Украина:
 *  CC=3 цифры (380), NDC=2 цифры (например, 50), SN=7 цифр (1234567)
 *  , итого — 12 цифр (итоговый пример: 380-50-1234567).
 * Беларусь:
 *  CC=3 цифры (375), NDC=2 цифры (например, 29), SN=7 цифр (1234567)
 *  , итого — 12 цифр (итоговый пример: 375-29-1234567).
 *
 * @author Pavel.Terechshenkov@kcell.kz
 * @since 06 10 2014
 */
public abstract class Msisdn {
    public static final String LOCALE_ENV_NAME = "MSISDN_LOCALE";

    public class PartsStruct {
        public String cc;
        public String ndc;
        public String sn;
    }

    enum Locale {
          KZ(1, 3, 7) // kazakstand
        , RU(1, 3, 7) // RF
        , UA(3, 2, 7) // ukraina
        , BY(3, 2, 7) // belarus

        ;
        public final int cc;
        public final int ndc;
        public final int sn;
        public final int length;

        Locale(int cc, int ndc, int sn) {
            this.cc = cc;
            this.ndc = ndc;
            this.sn = sn;
            this.length = cc+ndc+sn;
        }

        /**
         * Return MSISDN_LOCALE from system enviroment
         * @return
         */
        static public Locale getLocale() {
            return valueOf(System.getenv(LOCALE_ENV_NAME));
        }
    }

    static public enum Part {
         CC //Country Code
        ,NDC //National Destination Code
        ,SN //Subscriber Number
    }

    /**
     *  Enum avilable formats
     *
     *  +? ?7?7012110207
     *  +? ?7? (?701)? 2110207
     *  +? ?7? (?701)? 211 02 07
     *
     * First byte  define format for cc and ndc parts
     *  1 bit - display plus
     *  2 bit - separate white space
     *  3 bit - display cc
     *  4 bit - separate white space
     *  5 bit - display round brackets
     *  6 bit - display ndc
     *  7 bit - display round brackets
     *  8 bit - separate white space
     *
     *  Second byte define format for sn part. Every bit defind when position need add wihte space
     *  First bit  define display or not SN
     *    bit      12345678
     *    sn part   2110207
     *
     *
     *               2110207
     *    examples   10000000 - 2110207
     *               10010000 - 211 0207
     *               10010100 - 211 02 07
     *               11111111 - 2 1 1 0 2 0 7
     */
    static public enum Format {
        //                          n
        //                       c  d
        //                       c  c  2110207
        //                        + 7 (7) 2110207
        //                        1234567812345678
         SOLID                 (0b0010010010000000) //  77012110207
        ,SOLID_PLUS            (0b1010010010000000) // +77012110207
        ,CANONICAL             (0b0011111110010100) //  7 (701) 211 02 07
        ,CANONICAL_PLUS        (0b1011111110010100) // +7 (701) 211 02 07
        ,NORMAL                (0b0011111110010000) //  7 (701) 211 0207
        ,NORMAL_PLUS           (0b1011111110010000) // +7 (701) 211 0207
        ,COMPACT               (0b0011010110000000) //  7 701 2110207
        ,COMPACT_PLUS          (0b1011010110000000) // +7 701 2110207
        ,NDC_SN                (0b0000010110000000) //    701 2110207
        ,NDC_SN_3              (0b0000010110010000) //    701 211 0207
        ,NDC_SN_35             (0b0000010110010100) //    701 211 02 07
        ,CC                    (0b0010000000000000) //  7
        ,NDC                   (0b0000010000000000) //    701
        ,SN                    (0b0000000010000000) //        2110207
        ,SN_3                  (0b0000000010010000) //        211 0207
        ,SN_35                 (0b0000000010010100) //        211 02 07


        ;
        final int bitmask;

        Format(int bitmask) {
            this.bitmask = bitmask;
        }
    }

    protected Locale locale;
    protected String value;

    public void Msisdn(String value) {
        this.locale = Locale.KZ;
        this.value = value;
        sanitize();
        validate();
    }

    public void Msisdn(String value, Locale locale) {
        this.locale = locale;
        this.value = value;
        sanitize();
        validate();
    }

    protected void sanitize() {
        value = Strings.onlyNumbers(value);
    }

    protected void validate() {
        if(value.length() != locale.length) {
            throw new IllegalFormatMsisdnException(value, "Locale: "+locale.name()+" length must equal "+locale.length);
        }
    }

    public String get() {
        return value;
    }

    public Long getAsLong() {
        return Long.parseLong(value);
    }

    public Integer getAsInteger() {
        return Integer.parseInt(value);
    }

    public String format(Format format) {
        StringBuilder buffer = new StringBuilder(20);
        // first byte
        for(int i = 0b1000000000000000; i > 0b0000000010000000; i = i >> 1) {
            switch (format.bitmask & i) {
                          //1234567812345678
            /*1*/    case 0b1000000000000000 : buffer.append("+"); break;
            /*2*/    case 0b0100000000000000 : buffer.append(" "); break;
            /*3*/    case 0b0010000000000000 : buffer.append(getCC()); break;
            /*4*/    case 0b0001000000000000 : buffer.append(" "); break;
            /*5*/    case 0b0000100000000000 : buffer.append("("); break;
            /*6*/    case 0b0000010000000000 : buffer.append(getNDC()); break;
            /*7*/    case 0b0000001000000000 : buffer.append(")"); break;
            /*8*/    case 0b0000000100000000 : buffer.append(" "); break;
            }
        }

        // second byte
        if((format.bitmask & 0b0000000010000000) > 0) {
            int n = 0;
            int lenghtSN = getSN().length();
            for(int i = 0b0000000001000000; i > 0; i = i >> 1) {
                if ( n < lenghtSN) {
                    buffer.append(getSN().charAt(n));
                    if ((format.bitmask & i) > 0) buffer.append(" ");
                    n++;
                }
            }
        }


        return buffer.toString();
    }

    public String getPart(Part part) {
        throw new UnsupportedOperationException("Not implementated");
    }

    public abstract String getCC();
    public abstract String getNDC();
    public abstract String getSN();

    @Override
    public String toString() {
        return value;
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Msisdn msisdn = (Msisdn) o;

        if (value != null ? !value.equals(msisdn.value) : msisdn.value != null) return false;

        return true;
    }
}
