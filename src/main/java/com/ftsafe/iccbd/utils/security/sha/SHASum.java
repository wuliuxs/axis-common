package com.ftsafe.iccbd.utils.security.sha;

import com.ftsafe.iccbd.utils.security.util.ByteUtil;
import org.bouncycastle.crypto.Digest;
import org.bouncycastle.crypto.digests.SHA1Digest;

/**
 * Created by qingyuan on 17-3-25.
 */
public class SHASum {

    public static String sha1(String message) {

        Digest bcSha1 = new SHA1Digest();

        bcSha1.update(message.getBytes(), 0, message.getBytes().length);

        byte[] bytefinal = new byte[bcSha1.getDigestSize()];

        bcSha1.doFinal(bytefinal, 0);

        return ByteUtil.toHexString(bytefinal);
    }

    public static String sha1HexString(String message) {

        Digest bcSha1 = new SHA1Digest();

        byte[] msg = ByteUtil.toBytes(message);

        bcSha1.update(msg, 0, msg.length);

        byte[] bytefinal = new byte[bcSha1.getDigestSize()];

        bcSha1.doFinal(bytefinal, 0);

        return ByteUtil.toHexString(bytefinal);
    }

}
