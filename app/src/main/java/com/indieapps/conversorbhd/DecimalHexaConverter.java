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

public class DecimalHexaConverter extends Fragment implements View.OnClickListener {

    private	Button convert;
    private RadioGroup conversionOptions;
    private RadioButton dTOh;
    private RadioButton hTOd;
    private EditText input;
    private TextView output;

    public DecimalHexaConverter(){

    }

    public void onClick(View view){
		if (view.getId() == R.id.convertHD){
            int option = GlobalData.getOption();
            String result = new String();
            String num = getHexa();

            if (option == 1){
                if (NumericProcessor.verifyDecimal(num))
                    result = "" + NumericProcessor.castDecimalToHexa(num);
                else
                    ((MainActivity) getActivity()).showMessage("Valor ingresado no válido.");
            }

            if (option == 2){
                if (NumericProcessor.verifyHexa(num))
                    result = "" + NumericProcessor.castHexaToDecimal(num);
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
        View view = inflater.inflate(R.layout.fragment_hexa, container, false);

        if(view != null){
            LinearLayout linearLayout = (LinearLayout) view.findViewById(R.id.linearLayout);
            convert           = ((Button) view.findViewById(R.id.convertHD));
            conversionOptions = ((RadioGroup) view.findViewById(R.id.numberOptions));
            dTOh              = ((RadioButton) view.findViewById(R.id.dTOh));
            hTOd              = ((RadioButton) view.findViewById(R.id.hTOd));
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
                if (checkedId == dTOh.getId())
                    GlobalData.setOption(1);
                if (checkedId == hTOd.getId())
                    GlobalData.setOption(2);
            }
        });
    }

    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setHasOptionsMenu(false);
    }

    public String getHexa(){
        if (this.input.getText().toString().isEmpty())
            return "0";
        else
            return this.input.getText().toString();
    }

    public void setResult(String result) {
        this.output.setText(result);
    }
}