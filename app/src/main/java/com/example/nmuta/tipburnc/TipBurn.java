package com.example.nmuta.tipburnc;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.EditText;
import java.text.NumberFormat;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.text.TextWatcher;
import android.text.Editable;

import org.w3c.dom.Text;

public class TipBurn extends AppCompatActivity {

    public EditText the_bill;
    public TextView the_total;
    public static NumberFormat currency_stuff = NumberFormat.getCurrencyInstance();
    public static NumberFormat percenti = NumberFormat.getPercentInstance();
    public SeekBar seekbar;
    public static double variable_tip = 0.18;
    public TextView variable_tip_label;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tip_burn);
        the_total = (TextView)findViewById(R.id.the_total);
        the_bill = (EditText)findViewById(R.id.the_bill);
        seekbar = (SeekBar)findViewById(R.id.seekBar);
        the_bill.addTextChangedListener(textwatch);
        variable_tip_label = (TextView)findViewById(R.id.variable_tip_label);
        seekbar.setOnSeekBarChangeListener(seekbarri);

    }

    public void doTotal(){
        double final_bill = 0.00;
        try{
            double adjusted = Double.parseDouble(the_bill.getText().toString());
             final_bill = adjusted + adjusted * variable_tip;
        } catch (NumberFormatException e){
             final_bill = 0.00;
        }
        the_total.setText(currency_stuff.format(final_bill));
    }

    private TextWatcher textwatch = new TextWatcher(){
        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count){
            doTotal();
        }

        @Override
        public void afterTextChanged(Editable s){

        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int before, int count){

        }

    };

    private OnSeekBarChangeListener seekbarri =  new OnSeekBarChangeListener() {
        @Override
        public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
            double ii = ((double)i)/100;
            variable_tip_label.setText(percenti.format(ii));
            variable_tip = ii ;
            doTotal();
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {

        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {

        }
    };


}
