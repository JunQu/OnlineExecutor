package org.olexec.execute;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;

@SpringBootTest
public class ByteUtilsTest {

    @Test
    public void test1() {
        byte[] b = ByteUtils.string2Byte("a");
        System.out.println(Arrays.toString(b));
        int num = ByteUtils.byte2Int(b, 0, b.length);
        System.out.println(num);
        b = ByteUtils.int2Byte(num, b.length);
        System.out.println(Arrays.toString(b));
    }

}