package com.yogift.calculator;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    TextView workingsTextView;
    TextView resultsTextView;

    String workings;
    String formula;
    String tempFormula;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        initTextViews();
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
    private void initTextViews(){
        workingsTextView=(TextView) findViewById((R.id.workingsTextView));
        resultsTextView=(TextView) findViewById((R.id.resultsTextView));
    }

    public void equalsOnClick(View view){
        Double result = null;
        ScriptEngine engine = new ScriptEngineManager().getEngineByName("rhino");
        checkForPowerOf();

        try{
            result=(Double) engine.eval(formula);
        }
        catch (ScriptException e){
            Toast.makeText(this,"Input Invalid",Toast.LENGTH_SHORT).show();
        }

        if(result!=null){
            resultsTextView.setText(String.valueOf(result.doubleValue()));
        }
    }

    private void checkForPowerOf(){
        ArrayList<Integer> indexOfPower = new ArrayList<>();
        for (int i = 0; i<workings.legnth();i++){
            if(workings.charAt(i)=='^') {
                indexOfPower.add(i);
            }
        }
        formula = workings;
        tempFormula = workings;
        for (int i = 0; i< indexOfPower.length();i++){
            changeFormula(indexOfPower.get(i));
        }
    }
    private void changeFormula(int index){
        String numberLeft = "";
        String numberRight = "";

        for(int i=index+1; i<workings.length();i++){
            if(isNumeric(workings.charAt(i))==true){
                numberRight=numberRight+workings.charAt(i);
            }
            else
                break;
        }
        for(int i=index-1; i >= 0;i--){
            if(isNumeric(workings.charAt(i))==true){
                numberLeft=workings.charAt(i)+numberLeft;
            }
            else
                break;
        }
        String original = numberLeft+"^"+numberRight;
        String changed = "Math.pow("+numberLeft+","+numberRight+")";
        tempFormula = tempFormula.replace(original,changed);
    }


    public void clearOnClick(View view) {
        workingsTextView.setText("");
        workings = "";
        resultsTextView.setText("");
        leftBracket = true;
    }
    boolean leftBracket = true;

    public void bracketOnClick(View view){
        if(leftBracket){
            setWorkings("(");
            leftBracket = false;
        }
        else {
            setWorkings(")");
            leftBracket = true;
        }
    }

    private void setWorkings(String givenValue){
        workings=workings +givenValue;
        workingsTextView.setText(workings);
    }

    public void powerOfOnClick(View view){
        setWorkings("^");
    }
    public void divOnClick(View view){
        setWorkings("/");
    }
    public void addOnClick(View view){
        setWorkings("+");
    }
    public void subOnClick(View view){
        setWorkings("-");
    }
    public void mulOnClick(View view){
        setWorkings("*");
    }
    public void zeroOnClick(View view){
        setWorkings("0");
    }
    public void oneOnClick(View view){
        setWorkings("1");
    }
    public void twoOnClick(View view){
        setWorkings("2");
    }
    public void threeOnClick(View view){
        setWorkings("3");
    }
    public void fourOnClick(View view){
        setWorkings("4");
    }
    public void fiveOnClick(View view){
        setWorkings("5");
    }
    public void sixOnClick(View view){
        setWorkings("6");
    }
    public void sevenOnClick(View view){
        setWorkings("7");
    }
    public void eightOnClick(View view){
        setWorkings("8");
    }
    public void nineOnClick(View view){
        setWorkings("9");
    }
    public void dotOnClick(View view){
        setWorkings(".");
    }
}