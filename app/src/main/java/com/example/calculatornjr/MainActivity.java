package com.example.calculatornjr;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Toast;

import com.example.calculatornjr.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    Calculator calculator=new Calculator();
    int roundBracketOpenCount=0;
    double result=0;
    @SuppressLint("ResourceAsColor")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        //focus editext expression
        binding.edtExpression.requestFocus();
        binding.edtExpression.setCursorVisible(true);
        binding.edtExpression.setShowSoftInputOnFocus(false);
        //click number button
        clickNumberButton();
        //delete
        binding.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //expression=expression.substring(0,expression.length()-1);
                delete();
            }
        });
        //result
        binding.btnResult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String expression=binding.edtExpression.getText().toString();
                if (expression.length()!=0){
                    result=calculator.balan(expression);
                    binding.edtExpression.setText(String.valueOf(result));
                    binding.edtExpression.setSelection(String.valueOf(result).length());
                }
            }
        });

        //Listen review result
        binding.edtExpression.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                try {
                    if (s.length()==0){
                        binding.tvResult.setText("");
                    }else{
                        result=calculator.balan(String.valueOf(s));
                        binding.tvResult.setText(String.valueOf(result));
                    }
                }catch (Exception e){

                }
            }
        });
    }
    private void clickNumberButton(){
        binding.btnC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.edtExpression.setText("");
            }
        });
        binding.btn0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.edtExpression.append("0");
            }
        });
        binding.btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.edtExpression.append("1");
            }
        });
        binding.btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.edtExpression.append("2");
            }
        });
        binding.btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.edtExpression.append("3");
            }
        });
        binding.btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.edtExpression.append("4");
            }
        });
        binding.btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.edtExpression.append("5");
            }
        });
        binding.btn6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.edtExpression.append("6");
            }
        });
        binding.btn7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.edtExpression.append("7");
            }
        });
        binding.btn8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.edtExpression.append("8");
            }
        });
        binding.btn9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.edtExpression.append("9");
            }
        });
        binding.btnDot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.edtExpression.append(".");
            }
        });
        binding.btnPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.edtExpression.append("+");
            }
        });
        binding.btnSubtract.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.edtExpression.append("-");
            }
        });
        binding.btnMultiply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.edtExpression.append("*");
            }
        });
        binding.btnDevide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.edtExpression.append("/");
            }
        });
        binding.btnPercent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.edtExpression.append("%");
            }
        });
        binding.btnRoundBrackets.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (binding.edtExpression.getText().length()>0){
                    char c=binding.edtExpression.getText().charAt(binding.edtExpression.getText().length()-1);
                    if (c=='+' || c=='-'||c=='*' || c=='/'){
                        binding.edtExpression.append("(");
                        roundBracketOpenCount++;
                    }else{
                        if (roundBracketOpenCount>0){
                            binding.edtExpression.append(")");
                            roundBracketOpenCount--;
                        }else{
                            binding.edtExpression.append("*(");
                            roundBracketOpenCount++;
                        }
                    }
                }else{
                    binding.edtExpression.append("(");
                }
            }
        });
    }
    private void delete(){
        int position = binding.edtExpression.getSelectionStart();
        if (position > 0) {
            Editable text = binding.edtExpression.getText();
            text.delete(position - 1, position);
        }
    }
}