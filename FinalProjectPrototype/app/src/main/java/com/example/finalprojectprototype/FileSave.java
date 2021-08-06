package com.example.finalprojectprototype;

import android.content.Context;
import android.util.Log;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class FileSave {
    private String filename;
    private Context context;
    private String dataToSave;
    private FileOutputStream outputF;
    private FileInputStream inputF;
    private InputStreamReader inputR;
    private BufferedReader buffRead;

    FileSave(Context context){
        this.setContext(context);
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public String getDataToSave() {
        return dataToSave;
    }

    public void setDataToSave(String dataToSave) {
        this.dataToSave = dataToSave;
    }

    public FileOutputStream getOutputF() {
        return outputF;
    }

    public void setOutputF(FileOutputStream outputF) {
        this.outputF = outputF;
    }

    public FileInputStream getInputF() {
        return inputF;
    }

    public void setInputF(FileInputStream inputF) {
        this.inputF = inputF;
    }

    public InputStreamReader getInputR() {
        return inputR;
    }

    public void setInputR(InputStreamReader inputR) {
        this.inputR = inputR;
    }

    public BufferedReader getBuffRead() {
        return buffRead;
    }

    public void setBuffRead(BufferedReader buffRead) {
        this.buffRead = buffRead;
    }

    //Call to save to a specified File
    void fileSave(String filename, Context context, String data, FileOutputStream outputStream, FileInputStream inputStream, InputStreamReader inputStreamReader, BufferedReader bufferedReader){
        this.filename=filename;
        this.context=context;
        this.dataToSave=data;
        //this.outputF=outputStream;
        try {
            outputStream=context.openFileOutput(filename, context.MODE_PRIVATE);
            outputStream.write(dataToSave.getBytes());
            outputStream.close();
        }
        catch (Exception e) {
            Log.v("billInfo", "Error: "+e);
        }
    }

    //Call to get data from a specified File, returns the output
    public String fileRead(String filename, FileInputStream inputStream, InputStreamReader inputStreamReader, BufferedReader bufferedReader) {
        this.filename=filename;
        //this.inputF=inputStream;
        //this.inputR=inputStreamReader;
        //this.buffRead=bufferedReader;
        String outputText="";
        StringBuilder stringBuilder=new StringBuilder();

        try {
            inputF=context.openFileInput(filename);
            inputR=new InputStreamReader(inputF);
            buffRead=new BufferedReader(inputR);

            String line;
            while((line=buffRead.readLine())!=null) {
                stringBuilder.append(line);
            }
        }
        catch (FileNotFoundException e) {
            Log.v("billInfo", "Error: "+e);
        }
        catch (IOException e) {
            Log.v("billInfo", "Error: "+e);
        }

        outputText=stringBuilder.toString();

        return outputText;
    }
}
