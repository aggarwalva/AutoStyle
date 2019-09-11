package com.company.Errors;

public class UndefinedError extends CheckStyleError {

    public UndefinedError(String msg, CodeLine line){
        super("Undefined", msg, line);
    }

    @Override
    public CodeLine fix() {
        return line;
    }
}
