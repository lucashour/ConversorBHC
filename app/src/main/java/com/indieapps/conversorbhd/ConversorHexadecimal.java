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

public class ConversorHexadecimal extends Fragment implements View.OnClickListener {

    private	Button convertir;
    private RadioGroup opcionesDeConversion;
    private RadioButton dTOh;
    private RadioButton hTOd;
    private EditText entrada;
    private TextView salida;

    public ConversorHexadecimal(){

    }

    public void onClick(View view){

			/*Botón CONVERTIR*/

        if (view.getId() == R.id.convertHD){
            int opcion = DatosGlobales.getOption();
            String resultado = new String();
            String num = new String();
            num = getHexa();

            if (opcion == 1){
                boolean cumpleD = false;
                cumpleD = ProcesadorNumerico.verificarDecimal(num);
                if (cumpleD)
                    resultado = "" + ProcesadorNumerico.convertirDecimalHexadecimal(num);
                else
                    ((MainActivity) getActivity()).showMessage("Valor ingresado no válido.");
            }

            if (opcion == 2){
                boolean cumpleH = false;
                cumpleH = ProcesadorNumerico.verificarHexadecimal(num);
                if (cumpleH)
                    resultado = "" + ProcesadorNumerico.convertirHexadecimalDecimal(num);
                else
                    ((MainActivity) getActivity()).showMessage("Valor ingresado no válido.");
            }
            this.setResult(resultado);
        }

    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View v =  inflater.inflate(R.layout.fragment_hexa, container, false);

        if(v != null){

            LinearLayout linearLayout = (LinearLayout) v.findViewById(R.id.linearLayout);
            convertir = ((Button) v.findViewById(R.id.convertHD));
            opcionesDeConversion = ((RadioGroup) v.findViewById(R.id.numberOptions));
            dTOh = ((RadioButton) v.findViewById(R.id.dTOh));
            hTOd = ((RadioButton) v.findViewById(R.id.hTOd));
            entrada = ((EditText) v.findViewById(R.id.inputNumber));
            salida = ((TextView) v.findViewById(R.id.result));

            DatosGlobales.setOption(1);
        }
        return v;
    }

    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        convertir.setOnClickListener(this);

        opcionesDeConversion.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == dTOh.getId()) {
                    DatosGlobales.setOption(1);
                }
                if (checkedId == hTOd.getId()) {
                    DatosGlobales.setOption(2);
                }

            }
        });
    }

    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setHasOptionsMenu(false); //Indicamos que este Fragment no tiene su propio menu de opciones
    }

    public String getHexa(){
        String num = new String();
        if (this.entrada.getText().toString().isEmpty())
            num = "0";
        else
            num = this.entrada.getText().toString();
        return num;
    }

    public void setResult(String resultado) {
        this.salida.setText(resultado);
    }
}