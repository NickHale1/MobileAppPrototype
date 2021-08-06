package com.example.finalprojectprototype;

/**
 * Helper class to mnage the save file content
 */
public class SaveManager {

    static String[] names;
    static float[] amounts;

    static void processData(String fileContent) {
        if (!fileContent.equals("")) {
            String[] splitByPerson = fileContent.split(",");
            String[] tempNames = new String[splitByPerson.length];
            float[] tempAmounts = new float[splitByPerson.length];
            for (int i = 0; i < splitByPerson.length; i++) {
                String[] temp = splitByPerson[i].split("\t");
                tempNames[i] = temp[0];
                tempAmounts[i] = (float) Float.valueOf(temp[1]);
            }
            names = tempNames;
            amounts = tempAmounts;

        }
    }

    static String[] getNames(){
        return names;
    }

    static float[] getAmounts(){
        return amounts;
    }

    static String convertToFileFormat(String[]names, float[]amounts){
        StringBuilder sb = new StringBuilder();
        for(int i =0; i<names.length;i++){
            sb.append(names[i] + "\t" +amounts[i]);
            if(i< names.length-1){
                sb.append(",");
            }
        }
        return sb.toString();
    }

}