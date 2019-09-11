package com.company.Errors;

public class WhiteSpaceError extends CheckStyleError {

    public WhiteSpaceError(String msg, CodeLine line){
        super("WhiteSpace", msg, line);
    }

    @Override
    public CodeLine fix() {
        line = new CodeLine(line.getCode().substring(0,getIndex()-1 + line.getOffset()) + " " + line.getCode().substring(getIndex()-1+line.getOffset(), line.getCode().length()));
        line.offset(1);
        return line;
    }
}
