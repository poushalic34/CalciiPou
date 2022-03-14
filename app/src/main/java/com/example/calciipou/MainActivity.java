package com.example.calciipou;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {
    String input="",output="";
    double finalResult=0;
    double d=0;
    ArrayList<String> operand=new ArrayList<String>();
    private EditText display;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        display=findViewById(R.id.textView);
        display.setShowSoftInputOnFocus(false);
        display.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(getString(R.string.display).equals(display.getText().toString())){
                    display.setText("0.0");
                }
            }
        });
    }
    private void updateText(String strAdd)
    {
        String oldstr=display.getText().toString();
        int cursorPos=display.getSelectionStart();
        String leftstr=oldstr.substring(0,cursorPos);
        String rightstr=oldstr.substring(cursorPos);
        if(getString(R.string.display).equals(display.getText().toString())){
            display.setText(strAdd);
            display.setSelection(cursorPos+1);
        }
        else{
            display.setText(String.format("%s%s%s",leftstr,strAdd,rightstr));
            display.setSelection(cursorPos+(strAdd.length()));
        }
    }
    public void clearSc(View view){
            display.setText("");
    }
    public void OneBTN(View view){
        updateText("1");
    }
    public void TwoBTN(View view){
        updateText("2");
    }
    public void ThreeBTN(View view){
        updateText("3");
    }
    public void ZeroBTN(View view){
        updateText("0");
    }
    public void FourBTN(View view){
        updateText("4");
    }
    public void FiveBTN(View view){
        updateText("5");
    }
    public void SixBTN(View view){
        updateText("6");
    }
    public void SevenBTN(View view){
        updateText("7");
    }
    public void EightBTN(View view){
        updateText("8");
    }
    public void NineBTN(View view){
        updateText("9");
    }
    public void ExpoBTN(View view){
        updateText(" ^ ");
    }
    public void Del(View view){
        String s,st;
        int cursorPos=display.getSelectionStart();
        int textLen=display.getText().length();
        if(cursorPos!=0 && textLen!=0){
            SpannableStringBuilder selection=(SpannableStringBuilder)display.getText();
            String oldstr=display.getText().toString();
            Character c=oldstr.charAt(oldstr.length()-1);
            if(c==' '){
                s=display.getText().toString();
                st=s.substring(0,cursorPos-3);
                display.setText(st);
                display.setSelection(cursorPos-3);
            }
            else
            {
                selection.replace(cursorPos-1,cursorPos,"");
                display.setText(selection);
                display.setSelection(cursorPos-1);
            }

        }
    }

    public void MultiBTN(View view){

        String oldstr=display.getText().toString();
        int cursorPos=display.getSelectionStart();
        if(oldstr.length()!=0) {
            if (oldstr.substring(cursorPos - 1, cursorPos) != "+" || oldstr.substring(cursorPos - 1, cursorPos) != "-" || oldstr.substring(cursorPos - 1, cursorPos) != "×" || oldstr.substring(cursorPos - 1, cursorPos) != "÷")
                updateText(" × ");
        }
        else
            updateText(" × ");

    }
    public void DiviBTN(View view){

        String oldstr=display.getText().toString();
        int cursorPos=display.getSelectionStart();
        if(oldstr.length()!=0) {
            if (oldstr.substring(cursorPos - 1, cursorPos) != "+" || oldstr.substring(cursorPos - 1, cursorPos) != "-" || oldstr.substring(cursorPos - 1, cursorPos) != "×" || oldstr.substring(cursorPos - 1, cursorPos) != "÷")
                updateText(" ÷ ");
        }
        else
            updateText(" ÷ ");
    }
    public void AddBTN(View view){
        String oldstr=display.getText().toString();
        int cursorPos=display.getSelectionStart();
        if(oldstr.length()!=0) {
            if (oldstr.substring(cursorPos - 1, cursorPos) != "+" || oldstr.substring(cursorPos - 1, cursorPos) != "-" || oldstr.substring(cursorPos - 1, cursorPos) != "×" || oldstr.substring(cursorPos - 1, cursorPos) != "÷")
                updateText(" + ");
        }
        else
            updateText(" + ");
    }
    public void SubBTN(View view){
        String oldstr=display.getText().toString();
        int cursorPos=display.getSelectionStart();
        if(oldstr.length()!=0) {
            if (oldstr.substring(cursorPos - 1, cursorPos) != "+" || oldstr.substring(cursorPos - 1, cursorPos) != "-" || oldstr.substring(cursorPos - 1, cursorPos) != "×" || oldstr.substring(cursorPos - 1, cursorPos) != "÷")
                updateText(" - ");
        }
        else
            updateText(" - ");
    }
    public void openParan(View view){
        updateText("(");
    }
    public void closeParan(View view){
        updateText(")");
    }
    public void tanBTN(View view){
        updateText(" % ");
    }
    public void DeciBTN(View view){

        String oldstr=display.getText().toString();
        int cursorPos=display.getSelectionStart();
        if(oldstr.substring(cursorPos-1,cursorPos)!="+"||oldstr.substring(cursorPos-1,cursorPos)!="-"||oldstr.substring(cursorPos-1,cursorPos)!="×"||oldstr.substring(cursorPos-1,cursorPos)!="÷"||oldstr.substring(cursorPos-1,cursorPos)!=".")
            updateText(".");
    }
    public void EqualTo(View view)
    {
        getResult();

    }

    private void getResult() {
        operand.clear();
        String oldstr=display.getText().toString();
        oldstr=oldstr+" ";
        int len=oldstr.length();
        Character ch;
        String v="";
        for(int i=0;i<len;i++)
        {
            ch=oldstr.charAt(i);
            if(ch.equals(' '))
            {
                v=v.trim();
                operand.add(v);
                v="";
            }
            v=v+ch;
        }
        getAns("^");
        getAns("%");
        getAns("÷");
        getAns("×");
        getAns("+");
        getAns("-");
        display.setText(String.valueOf(finalResult));
    }

    private void getAns(String operator) {
        int pos;
        for(int i=0;i<operand.size();i++)
        {
            if(operand.get(i).equals(operator))
            {
                if(operand.get(i).equals("%"))
                    finalResult=(Double.parseDouble(operand.get(i-1)))/(Double.parseDouble(operand.get(i+1)))*100;
                else if(operand.get(i).equals("÷"))
                    finalResult=(Double.parseDouble(operand.get(i-1)))/(Double.parseDouble(operand.get(i+1)));
                else if(operand.get(i).equals("+"))
                    finalResult=(Double.parseDouble(operand.get(i-1)))+(Double.parseDouble(operand.get(i+1)));
                else if(operand.get(i).equals("-"))
                    finalResult=(Double.parseDouble(operand.get(i-1)))-(Double.parseDouble(operand.get(i+1)));
                else if(operand.get(i).equals("×"))
                    finalResult=(Double.parseDouble(operand.get(i-1)))*(Double.parseDouble(operand.get(i+1)));
                else if(operand.get(i).equals("^"))
                {
                    double g=Double.parseDouble(operand.get(i-1));
                    double h=Double.parseDouble(operand.get(i+1));
                    int e=(int)h;
                    d=g;
                    for(int j=1;j<e;j++)
                    {
                        d=d*g;
                    }
                    finalResult=d;

                }


            }
        }

    }

}