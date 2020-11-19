package com.utils;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class AjaxUtils {
    public static void printString(HttpServletResponse response, String str) {
        response.setCharacterEncoding("utf-8");
        try {
            PrintWriter out=response.getWriter();
            out.print(str);
            out.flush();
            out.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
