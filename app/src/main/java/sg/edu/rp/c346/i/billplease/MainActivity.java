package sg.edu.rp.c346.i.billplease;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.ToggleButton;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

EditText etAmt;
EditText etPx;
EditText etDscnt;
ToggleButton btnSvs;
ToggleButton btnGst;
Button btnSPLIT;
Button btnRESET;
TextView txtBill;
TextView txtIndivudal;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etAmt = findViewById(R.id.etAmount);
        etPx = findViewById(R.id.etPax);
        etDscnt = findViewById(R.id.etDiscount);
        btnSvs = findViewById(R.id.btnSVS);
        btnGst = findViewById(R.id.btnGST);
        btnSPLIT = findViewById(R.id.btnSplit);
        btnRESET = findViewById(R.id.btnReset);
        txtBill = findViewById(R.id.txtTotalBill);
        txtIndivudal = findViewById(R.id.txtIndividualBill);

        if(btnSvs.isChecked()){
            btnSvs.setBackgroundColor(Color.GREEN);
        }else{
            btnSvs.setBackgroundColor(Color.RED);
        }

        if(btnGst.isChecked()){
            btnGst.setBackgroundColor(Color.GREEN);
        }else{
            btnGst.setBackgroundColor(Color.RED);
        }

        btnSvs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(btnSvs.isChecked()){
                    btnSvs.setBackgroundColor(Color.GREEN);
                }else{
                    btnSvs.setBackgroundColor(Color.RED);
                }
            }
        });

        btnGst.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(btnGst.isChecked()){
                    btnGst.setBackgroundColor(Color.GREEN);
                }else{
                    btnGst.setBackgroundColor(Color.RED);
                }

            }
        });

        btnSPLIT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(etAmt.getText().toString().trim().length() !=0 && etPx.getText().toString().trim().length() != 0){
                    double newAmt=0.0;

                    if(!btnSvs.isChecked() && !btnGst.isChecked()){
                        newAmt = Double.parseDouble(etAmt.getText().toString());
                    }else if(btnSvs.isChecked() && !btnGst.isChecked()){
                        newAmt = Double.parseDouble(etAmt.getText().toString()) / 100 * 110;
                        //newAmt = Double.parseDouble(etAmt.getText().toString()) * 1.1;

                    }else if(!btnSvs.isChecked() && btnGst.isChecked()){
                        newAmt = Double.parseDouble(etAmt.getText().toString()) / 100 * 107;
                        //newAmt = Double.parseDouble(etAmt.getText().toString()) * 1.07;
                    }else{
                        newAmt = Double.parseDouble(etAmt.getText().toString()) /100 * 117;
                        //newAmt = Double.parseDouble(etAmt.getText().toString()) * 1.17;
                    }

                    if(etDscnt.getText().toString().trim().length() != 0){
                        newAmt = newAmt / 100 * (100 - Double.parseDouble(etDscnt.getText().toString()));
                        //newAmt *= 1 - (Double.parseDouble(etDscnt.getText().toString()) / 100);
                    }

                    txtBill.setText(String.format("%.2f", newAmt));
                    int pax = Integer.parseInt(etPx.getText().toString());
                    if(pax != 1){
                        txtIndivudal.setText(String.format("%.2f", newAmt/pax));
                    }else{
                        txtIndivudal.setText(String.format("%.2f", newAmt));
                    }
                }else if(etAmt.getText().toString().trim().length() ==0 && etPx.getText().toString().trim().length() != 0){
                    etAmt.setError("Please fill in the amount");
                }else if(etAmt.getText().toString().trim().length() !=0 && etPx.getText().toString().trim().length() == 0){
                    etPx.setError("Please fill in the number of pax");
                }else if(etAmt.getText().toString().trim().length() ==0 && etPx.getText().toString().trim().length() == 0){
                    etAmt.setError("Please fill in the amount");
                    etPx.setError("Please fill in the number of pax");
                }
            }
        });

        btnRESET.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtBill.setText("00.00");
                txtIndivudal.setText("00.00");
                etAmt.setText("");
                etPx.setText("");
                etDscnt.setText("");
                btnSvs.setChecked(false);
                btnSvs.setBackgroundColor(Color.RED);
                btnGst.setChecked(false);
                btnGst.setBackgroundColor(Color.RED);
            }
        });



    }
}
