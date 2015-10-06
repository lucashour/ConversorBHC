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

public class ConversorBinario extends Fragment implements View.OnClickListener {

    private Button convertir;
    private RadioGroup opcionesDeConversion;
    private RadioButton dTOb;
    private RadioButton bTOd;
    private EditText entrada;
    private TextView salida;

    public ConversorBinario(){

    }

    public void onClick(View view){

			/*Botón CONVERTIR*/

        if (view.getId() == R.id.convertBD){
            long num = getNumber();
            int opcion = DatosGlobales.getOption();
            boolean cumple = false;
            String resultado = new String();

            if (opcion == 1){
                resultado = "" + ProcesadorNumerico.convertirDecimalBinario(num);
            }

            if (opcion == 2){
                cumple = ProcesadorNumerico.verificarBinario(num);
                if (cumple)
                    resultado = "" + ProcesadorNumerico.convertirBinarioDecimal(num);
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
        View v =  inflater.inflate(R.layout.fragment_bin, container, false);

        if(v != null){

            LinearLayout linearLayout = (LinearLayout) v.findViewById(R.id.linearLayout);
            convertir = ((Button) v.findViewById(R.id.convertBD));
            opcionesDeConversion = ((RadioGroup) v.findViewById(R.id.numberOptions));
            dTOb = ((RadioButton) v.findViewById(R.id.dTOb));
            bTOd = ((RadioButton) v.findViewById(R.id.bTOd));
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
                if (checkedId == dTOb.getId()){
                    DatosGlobales.setOption(1);
                }
                if (checkedId == bTOd.getId()){
                    DatosGlobales.setOption(2);
                }

            }
        });
    }

    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setHasOptionsMenu(false); //Indicamos que este Fragment no tiene su propio menu de opciones
    }

    public long getNumber(){
        long num;

        if (this.entrada.getText().toString().isEmpty())
            num = 0;
        else
            num = Long.parseLong(this.entrada.getText().toString());
        return num;
    }

    public void setResult(String resultado) {
        this.salida.setText(resultado);
    }
}