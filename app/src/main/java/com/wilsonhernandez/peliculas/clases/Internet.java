package com.wilsonhernandez.peliculas.clases;

public class Internet {
    public boolean validarInternet(){
        try {
            Process p = Runtime.getRuntime().exec("ping -c 1 www.google.com");

            int val           = p.waitFor();
            boolean reachable = (val == 0);
            return reachable;

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return false;
    }
}
