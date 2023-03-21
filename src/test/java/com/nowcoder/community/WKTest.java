package com.nowcoder.community;

import java.io.IOException;

public class WKTest {
    public static void main(String[] args){
        String cmd = "d:/localFactory/wkhtmltopdf/bin/wkhtmltoimage --quality 75 https://www.nowcoder.com d:/work/data/wk-images/2.png";

        try {
            Runtime.getRuntime().exec(cmd);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("ok");
    }
}
