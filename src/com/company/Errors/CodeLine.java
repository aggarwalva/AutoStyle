package com.company.Errors;

public class CodeLine {
    public String code;
    public int offset;

    public CodeLine(String code){
        this.code = code;
    }

    public CodeLine(String code, int offset){
        this(code);
        this.offset = offset;
    }

    public String getCode(){
        return code;
    }

    public void setCode(String code){
        this.code = code;
    }

    public int getOffset(){
        return offset;
    }

    public void offset(int offset){
        this.offset += offset;
    }

    public void setOffset(int offset){
        this.offset = offset;
    }
}
