package com.ftsafe.iccbd.utils.pboc;

/**
 * Created by qingyuan on 17-3-29.
 */
public class PBOCUtil {

    public static String BerL(String BerV) {

        if (BerV != null && BerV.length() % 2 == 0) {
            int len = BerV.length() / 2;
            String tmp = String.format("%02X", len);
            if (len < 128) { // 小于0x80
                return tmp;
            } else if (len < 256) { // 小于0x100
                return "81" + tmp;
            } else {
                return "82" + tmp;
            }
        }

        return "00";
    }
}
