package edu.sjsu.android.mortgagecalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {


    double interestRate;
    EditText borrowAmount;
    TextView textViewRate;
    SeekBar seekBarInterest;
    RadioGroup radioGroup;
    CheckBox checkBoxTax;
    TextView monthlyPayment;

    RadioButton radioButton15;
    RadioButton radioButton20;
    RadioButton radioButton30;



    public static double calculate(double principal, double interest, double years, boolean taxAndInsurance) {

        double J = interest / 1200;
        double N = years * 12;
        double T = (taxAndInsurance) ? principal * 0.1 / N : 0.0;

        if(interest == 0.0) return isZero( principal,  N,  T);
        else return notZero( principal,  J,  N,  T);
    }


    private static double isZero(double principal, double N, double T) {
        return principal / N + T;
    }


    private static double notZero(double principal, double J, double N, double T) {
        return (principal * (J / (1 - Math.pow((1 + J), -N))) + T);
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        borrowAmount = (EditText) findViewById(R.id.borrowAmount);
        textViewRate = (TextView) findViewById(R.id.textViewRate);
        seekBarInterest = (SeekBar) findViewById(R.id.seekBarInterest);
        radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        checkBoxTax = (CheckBox) findViewById(R.id.checkBoxTax);
        monthlyPayment = (TextView) findViewById(R.id.monthlyPayment);
        radioButton15 = (RadioButton) findViewById(R.id.radioButton15);
        radioButton20 = (RadioButton) findViewById(R.id.radioButton20);
        radioButton30 = (RadioButton) findViewById(R.id.radioButton30);


        seekBarInterest.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (fromUser) {


                    interestRate = seekBarInterest.getProgress();
                     textViewRate.setText(interestRate + "");
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }


        });
    }

    public void onClick(View view) {

        String input = borrowAmount.getText().toString();


        if(input.length() == 0) {
            Toast.makeText(this, "Please enter an amount", Toast.LENGTH_LONG).show();
            return;
        }
        double principle = Double.parseDouble(borrowAmount.getText().toString());

        double interest = seekBarInterest.getProgress() / 10.0;

        double years;
        if (radioButton15.isChecked())
            years = 15;
        else if (radioButton20.isChecked())
            years = 20;
        else if (radioButton30.isChecked())
            years = 30;
        else {
            Toast.makeText(this, "Please choose years option", Toast.LENGTH_LONG).show();
            return;
        }
        boolean taxAndInsurance = checkBoxTax.isChecked();

        double payment = calculate(principle, interest, years, taxAndInsurance);
        String output = String.format("$%.1f", payment);
        monthlyPayment.setText("Your Payment: " + output);
    }
}