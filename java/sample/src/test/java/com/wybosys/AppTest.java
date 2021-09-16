package com.wybosys;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.logging.Logger;

import org.apache.commons.lang3.RandomStringUtils;
import org.joda.time.DateTime;
import org.joda.time.LocalDateTime;
import org.joda.time.chrono.ISOChronology;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class AppTest extends TestCase {

    public AppTest(String testName) {
        super(testName);

        AES.Init();
    }

    public static Test suite() {
        return new TestSuite(AppTest.class);
    }

    private Logger _log = Logger.getLogger("test");

    public void testApp() throws Exception {
        AES.KEY = RandomStringUtils.randomAscii(16);
        AES.IV = RandomStringUtils.randomAscii(16);

        // AES.METHOD = "AES/CBC/PKCS5Padding";
        AES.METHOD = "AES/CBC/PKCS7Padding";

        _log.info(AES.KEY);
        _log.info(AES.IV);

        String plain = RandomStringUtils.random(1024);

        String cyp = AES.Encrypt(plain);
        String tgt = AES.Decrypt(cyp);
        assertEquals(tgt, plain);

        Pipeline pipe = new Pipeline();
        pipe.local("xxx", "yyy").localInc("xxx");
        pipe.next("A").global("abc", 123).globalInc("abc");
        pipe.next("B").local("abc", 100).localInc("abc");
        assertEquals(pipe.global("abc"), 124);
        assertEquals(pipe.local("abc"), 101);
        pipe.next("C");

        _log.info(pipe.toString());
    }

    public void testTime() {
        DateTime jdt = DateTime.now();
        _log.info(jdt.toString());

        DateTime jdtutc = DateTime.now(ISOChronology.getInstanceUTC());
        _log.info(jdtutc.toString());

        java.time.LocalDateTime dt = java.time.LocalDateTime.parse(jdt.toString(),
                DateTimeFormatter.ISO_OFFSET_DATE_TIME);
        _log.info(dt.toString());

        dt = java.time.LocalDateTime.parse(jdtutc.toString(), DateTimeFormatter.ISO_DATE_TIME);
        _log.info(dt.toString());

        LocalDateTime jldt = LocalDateTime.now();
        _log.info(jldt.toString());

        org.joda.time.LocalDate jld = org.joda.time.LocalDate.now();
        _log.info(jld.toDateTimeAtStartOfDay().toDateTime(ISOChronology.getInstanceUTC()).toString());
        _log.info(jld.toInterval().toString());

        LocalDate d = LocalDate.now();
        _log.info(d.toString());
    }
}
