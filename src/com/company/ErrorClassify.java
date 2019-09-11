package com.company;

import com.company.Errors.*;

public class ErrorClassify {
    public static CheckStyleError generateError(String msg, CodeLine line){
        if(msg.contains("whitespace.")){
            return new WhiteSpaceError(msg, line);
        }
        else{
            return new UndefinedError(msg,line);
        }
    }
}
