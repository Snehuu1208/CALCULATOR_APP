package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;

import com.google.android.material.button.MaterialButton;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    TextView resultTv,solutionTv;
    MaterialButton buttonC,buttonBracOpen,buttonBracClose;
    MaterialButton buttonDivide,buttonMultiply,buttonPlus,buttonMinus,buttonEquals;
    MaterialButton button0,button1,button2,button3,button4,button5,button6,button7,button8,button9;
    MaterialButton buttonAC,buttonDot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

        resultTv=findViewById(R.id.textView);
        solutionTv=findViewById(R.id.textView2);

        assignId(buttonC,R.id.button_C);
        assignId(buttonBracOpen,R.id.open_bracket);
        assignId(buttonBracClose,R.id.close_bracket);
        assignId(buttonDivide,R.id.button_slash);
        assignId(buttonMultiply,R.id.button_mul);
        assignId(buttonPlus,R.id.button_add);
        assignId(buttonMinus,R.id.button_sub);
        assignId(buttonEquals,R.id.button_equal);
        assignId(button0,R.id.button_0);
        assignId(button1,R.id.button_1);
        assignId(button2,R.id.button_2);
        assignId(button3,R.id.button_3);
        assignId(button4,R.id.button_4);
        assignId(button5,R.id.button_5);
        assignId(button6,R.id.button_6);
        assignId(button7,R.id.button_7);
        assignId(button8,R.id.button_8);
        assignId(button9,R.id.button_9);
        assignId(buttonAC,R.id.button_AC);
        assignId(buttonDot,R.id.cbutton_dot);
    }

    void assignId(MaterialButton btn,int id){
        btn=findViewById(id);
        btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
    MaterialButton button=(MaterialButton) v;
    String buttonText=button.getText().toString();
    String dataToCalculate=solutionTv.getText().toString();

    if(buttonText.equals("AC")){
        solutionTv.setText((""));
        resultTv.setText("0");
        return;
    }
    if(buttonText.equals("=")){
        solutionTv.setText(resultTv.getText());
        return;
    }
    if(buttonText.equals("C")){
            dataToCalculate = dataToCalculate.substring(0, dataToCalculate.length() - 1);
    }
    else{
        dataToCalculate=dataToCalculate+buttonText;
    }
    solutionTv.setText(dataToCalculate);
    String finalResult=getResult(dataToCalculate);

    if(!finalResult.equals("Error")){
        resultTv.setText(finalResult );
    }
    }


    String getResult(String data){
        try {
            Context context=Context.enter();
            context.setOptimizationLevel(-1);
            Scriptable scriptable=context.initStandardObjects();
            String finalResult=context.evaluateString(scriptable,data,"Javascript",1,null)
                    .toString();
            if(finalResult.endsWith(".0")){
                finalResult=finalResult.replace(".0","");
            }
            return finalResult;
        }catch (Exception e){
            return "Error";
        }
    }
}