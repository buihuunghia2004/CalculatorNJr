package com.example.calculatornjr;

import java.util.ArrayList;

public class Calculator {
    public double balan(String s){
        char[] arrCharExpression=s.toCharArray();

        //create queue + stax + number stax
        ArrayList queue=new ArrayList<>();
        ArrayList<Character> stax=new ArrayList<>();
        ArrayList<Character> numberStax=new ArrayList<>();

        //Handle...
        for (char c:arrCharExpression){
            //1: c is number or '.'
            if (Character.isDigit(c) || c=='.'){
                numberStax.add(c);

                //2: c is sign
            }else{
                //convert numberstax to number and add to queue
                if (!numberStax.isEmpty()){
                    queue.add(getNumber(numberStax));
                    numberStax.clear();
                }

                //if stax empty
                if (stax.isEmpty()){
                    stax.add(c);

                    //if stax not empty
                }else{
                    //if c is '('
                    if (c=='('){
                        stax.add(c);
                        //if c is ')'
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
                        // if c is '+,-,*,/'
                    }else{
                        for (int i=stax.size()-1;i>=0;i--){
                            //match '('
                            if (stax.get(i)=='('){
                                break;
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
        System.out.println("c");
        for (Object o:queue){
            System.out.println(o);
        }
        System.out.println("c");
        //cù
        if (!numberStax.isEmpty()){
            queue.add(getNumber(numberStax));
        }
        if (!stax.isEmpty()){
            for (int i=stax.size()-1;i>=0;i--){
                queue.add(stax.get(i));
            }
        }

        System.out.println("f");
        for (Object o:queue){
            System.out.println(o);
        }
        System.out.println("f");

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
