package com.delbiaggio.haagahelia.swingmath.tools;

import java.util.ArrayList;
import java.util.StringTokenizer;

public class CreateTablesFromString {

    public static ArrayList<Integer> convertStrToArray(String source) {
        ArrayList<Integer> confTables = new ArrayList();
        String lstTable = source;
        StringTokenizer str = new StringTokenizer(lstTable, ";");
        while (str.hasMoreTokens()) {
            if (!manageTableAToN(str.nextToken(), confTables)) {
                return null;
            }
            
        }
        return confTables;
    }

    private static boolean manageTableAToN(String tables, ArrayList<Integer> confTables) {
        StringTokenizer str = new StringTokenizer(tables, "-");
        try {
            if (str.countTokens() == 1) {
                confTables.add(Integer.parseInt(str.nextToken()));
            } else {
                int deb = Integer.parseInt(str.nextToken());
                int fin = Integer.parseInt(str.nextToken());
                if (deb>fin) {
                    return false;
                }
                for (int i = deb; i <= fin; i++) {
                    confTables.add(i);
                }
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }

}
