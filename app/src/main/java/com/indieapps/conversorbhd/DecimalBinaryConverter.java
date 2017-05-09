package com.indieapps.conversorbhd;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class DecimalBinaryConverter extends Fragment implements View.OnClickListener {

    private Button convert;
    private RadioGroup conversionOptions;
    private RadioButton dTOb;
    private RadioButton bTOd;
    private EditText input;
    private TextView output;

    public DecimalBinaryConverter(){

    }

    public void onClick(View view){
        if (view.getId() == R.id.convertBD){
            long num = getNumber();
            int option = GlobalData.getOption();
            String result = new String();

            if (option == 1)
                result = "" + NumericProcessor.castDecimaltoToBinary(num);

            if (option == 2){
                if (NumericProcessor.verifyBinary(num))
                    result = "" + NumericProcessor.castBinaryToDecimal(num);
                else
                    ((MainActivity) getActivity()).showMessage("Valor ingresado no válido.");
            }

            this.setResult(result);
        }

    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_bin, container, false);

        if(view != null){
            LinearLayout linearLayout = (LinearLayout) view.findViewById(R.id.linearLayout);
            convert           = ((Button) view.findViewById(R.id.convertBD));
            conversionOptions = ((RadioGroup) view.findViewById(R.id.numberOptions));
            dTOb              = ((RadioButton) view.findViewById(R.id.dTOb));
            bTOd              = ((RadioButton) view.findViewById(R.id.bTOd));
            input             = ((EditText) view.findViewById(R.id.inputNumber));
            output            = ((TextView) view.findViewById(R.id.result));

            GlobalData.setOption(1);
        }

        return view;
    }

    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        convert.setOnClickListener(this);

        conversionOptions.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == dTOb.getId())
                    GlobalData.setOption(1);

                if (checkedId == bTOd.getId())
                    GlobalData.setOption(2);

            }
        });
    }

    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setHasOptionsMenu(false);
    }

    public long getNumber(){
        if (this.input.getText().toString().isEmpty())
            return 0;
        else
            return Long.parseLong(this.input.getText().toString());
    }

    public void setResult(String result) {
        this.output.setText(result);
    }
}