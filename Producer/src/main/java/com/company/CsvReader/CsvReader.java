package com.company.CsvReader;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class CsvReader {

    private final String filePath =  "C:\\Users\\Alex\\Desktop\\DragosProject\\Producer\\src\\main\\java\\com\\company\\resources\\sensor.csv";
    private ArrayList<Double> data = new ArrayList<Double>();

    public ArrayList<Double> getData() {
        return data;
    }

    public void readSensor(){
        File file = new File(this.filePath);
        Scanner inputStream;
        try{
            inputStream = new Scanner(file);
            while(inputStream.hasNext()){
                String line = inputStream.next();
                data.add(Double.parseDouble(line));
            }
        }catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
