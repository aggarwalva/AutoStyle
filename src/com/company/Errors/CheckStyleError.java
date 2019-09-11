package com.company.Errors;

public abstract class CheckStyleError{
    String type, msg;
    CodeLine line;
    int lineNum, index, lineIndexEnd;

    protected CheckStyleError(String type, String msg, CodeLine line){
        this.type = type;
        this.line = line;
        this.msg = msg;
        this.lineNum = findLineNum();
        this.index = findIndex();
    }

    protected String getType(){
        return type;
    }

    protected CodeLine getLine(){
        return line;
    }

    protected String getErrorMesssage(){
        return msg;
    }

    private int findLineNum(){
        lineIndexEnd = msg.indexOf(".java") + 6;
        int index = 0;
        while(true){
            try{
                index = index * 10 + Integer.parseInt(String.valueOf(msg.charAt(lineIndexEnd)));
                lineIndexEnd++;
            } catch (NumberFormatException e){
                break;
            }
        }
        return index;
    }

    private int findIndex(){
        int start = lineIndexEnd + 1;
        int index = 0;
        while(true){
            try{
                index = index * 10 + Integer.parseInt(String.valueOf(msg.charAt(start)));
                start++;
            } catch (NumberFormatException e){
                break;
            }
        }
        return index;
    }

    public int getIndex(){
        return index;
    }

    public int getLineNum() {
        return lineNum;
    }

    public abstract CodeLine fix();

    public static CheckStyleError generateError(String msg, CodeLine line){
        if(msg.contains(".java")){
            if(msg.contains("whitespace.")){
                return new WhiteSpaceError(msg, line);
            }
            else{
                return new UndefinedError(msg,line);
            }
        } else{
            return null;
        }
    }

    public static int findLineNumber(String msg){
        if(!msg.contains(".java")) return -1;
        int lineIndexEnd = msg.indexOf(".java") + 6;
        int index = 0;
        while(true){
            try{
                index = index * 10 + Integer.parseInt(String.valueOf(msg.charAt(lineIndexEnd)));
                lineIndexEnd++;
            } catch (NumberFormatException e){
                break;
            }
        }
        return index;
    }
}