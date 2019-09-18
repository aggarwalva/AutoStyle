package com.company;

import com.company.Errors.*;
import javafx.application.Application;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.*;
import java.util.ArrayList;

public class AutoStyle extends Application {
    public static void main(String[] args){
        FileReader in = null, inLog = null;
        FileWriter out = null;
        BufferedReader read = null, readLog = null;
        BufferedWriter write = null;

        try{
            File f = new File("C:\\Users\\Varun\\Desktop\\Test\\Test.java");
            File log = new File("C:\\Users\\Varun\\Desktop\\Test\\outlog.txt");
            File temp = new File(f.getAbsolutePath().replace(".java", "TEMP.java"));

            /*
            CheckStyleError c = ErrorClassify.generateError("C:\\Users\\Varun\\Desktop\\Test\\Test.java:2:18: ',' is not followed by whitespace.", "    static int x,y,k;");
            System.out.println(c.fix());
            WhiteSpaceError w = new WhiteSpaceError("C:\\Users\\Varun\\Desktop\\Test\\Test.java:2:18: ',' is not followed by whitespace.", "    static int x,y,k;");
            System.out.println("Line: " + w.getLineNum() + " Index: " + w.getIndex());
            System.out.println("Line: " + w.getLineNum()+ " : " + w.fix());
               */

            System.out.println(f.canRead() && log.canRead());
            in = new FileReader(f);
            inLog = new FileReader(log);
            out = new FileWriter(temp);
            read = new BufferedReader(in);
            readLog = new BufferedReader(inLog);
            write = new BufferedWriter(out);

            ArrayList<CodeLine> code = new ArrayList<>();
            while (read.ready()) {
                code.add(new CodeLine(read.readLine()));
            }

            String currentMessage;
            int lineNum;
            while(readLog.ready()){
                currentMessage = readLog.readLine();
                lineNum = CheckStyleError.findLineNumber(currentMessage);
                System.out.println("Line number: " + lineNum);
                if(lineNum > 0){
                    CheckStyleError c = CheckStyleError.generateError(currentMessage, code.get(lineNum-1));
                    code.set(lineNum - 1, c.fix());
                }
            }

            for(CodeLine c : code){
                System.out.println(c.getCode());
                write.write(c.getCode());
                write.newLine();
            }

            write.close();
            out.close();
            read.close();
            in.close();

            System.out.println("Deleted: " + f.delete());
            temp.renameTo(f);
        } catch(Exception e){
            System.out.println("Exception in main");
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void start(Stage primaryStage){
        primaryStage.setTitle("AutoStyle");
        TextField inPath = new TextField("Enter input file here");
        TextField outPath = new TextField("Enter output file here");
    }
}
