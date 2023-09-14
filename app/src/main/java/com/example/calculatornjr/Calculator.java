package com.example.calculatornjr;

import java.util.ArrayList;

public class Calculator {
    public double balan(String s){
        char[] arrCharExpression=s.toCharArray();

        //create queue + stax + number stax
        ArrayList queue=new ArrayList<>();
        ArrayList<Character> stax=new ArrayList<>();
        ArrayList<Character> numberStax=new ArrayList<>();

        for (char c:arrCharExpression){
            if (Character.isDigit(c) || c=='.'){      //ký tự là số
                numberStax.add(c);          //thêm c vào number stax
            }else{                          //ký tự là dấu
                //chuyển number stax thành số và thêm vào queue
                if (!numberStax.isEmpty()){
                    queue.add(getNumber(numberStax));
                    numberStax.clear();
                }

                if (stax.isEmpty()){        //nếu stax rỗng
                    stax.add(c);            //thêm c vào stack
                }else{                      //nếu stax ko rỗng
                    //nếu là dấu (
                    if (c=='('){
                        stax.add(c);
                    }else if (c==')'){
                        for (int i=stax.size()-1;i>=0;i--){
                            if (stax.get(i)=='('){
                                stax.remove(i);
                                break;
                            }else{
                                queue.add(stax.get(i));
                                stax.remove(i);
                            }
                        }
                    }else{
                        for (int i=stax.size()-1;i>=0;i--){         //duyệt stax
                            if (stax.get(i)=='('){
                                stax.remove(i);
                            }else{
                                if (getLevelSign(c) >= getLevelSign(stax.get(i))){//nếu dấu có level cao hơn hoặc bằng
                                    //chuyển dấu từ stax vào queue
                                    queue.add(stax.get(i));
                                    stax.remove(i);
                                }else{                                  //nếu dấu có level thấp hơn
                                    break;
                                }
                            }
                            //
                        }
                        //thêm c vào stax
                        stax.add(c);
                    }
                }
            }
        }
        //cù
        if (!numberStax.isEmpty()){
            queue.add(getNumber(numberStax));
        }
        if (!stax.isEmpty()){
            for (int i=stax.size()-1;i>=0;i--){
                queue.add(stax.get(i));
            }
        }

        ArrayList arr=new ArrayList<>();
        for (Object o:queue){
            if (String.valueOf(o).equals("+") || String.valueOf(o).equals("*") || String.valueOf(o).equals("-") || String.valueOf(o).equals("/")){
                //handle
                switch ((char) o){
                    case '+':arr.set(arr.size()-2,Double.parseDouble(String.valueOf(arr.get(arr.size()-2)))+Double.parseDouble(String.valueOf(arr.get(arr.size()-1))));break;
                    case '*':arr.set(arr.size()-2,Double.parseDouble(String.valueOf(arr.get(arr.size()-2)))*Double.parseDouble(String.valueOf(arr.get(arr.size()-1))));break;
                    case '-':arr.set(arr.size()-2,Double.parseDouble(String.valueOf(arr.get(arr.size()-2)))-Double.parseDouble(String.valueOf(arr.get(arr.size()-1))));break;
                    case '/':arr.set(arr.size()-2,Double.parseDouble(String.valueOf(arr.get(arr.size()-2)))/Double.parseDouble(String.valueOf(arr.get(arr.size()-1))));break;
                }
                arr.remove(arr.size()-1);
            }else{
                arr.add(Double.parseDouble(String.valueOf(o)));
            }
        }

        return Double.parseDouble(String.valueOf(arr.get(0)));
    }
    private int getLevelSign(char c){
        if (c=='+' || c=='-'){
            return 2;
        }else if (c=='*' || c=='/'){
            return 1;
        }
        return 0;
    }
    private double getNumber(ArrayList<Character> arr){
        String numberS="";
        Double number=0.0;
        for (char c:arr){
            numberS+=String.valueOf(c);
        }
        try {
            number=Double.parseDouble(numberS);
        }catch (Exception e){

        }
        return number;
    }
}
