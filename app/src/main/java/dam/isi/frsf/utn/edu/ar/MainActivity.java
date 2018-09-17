package dam.isi.frsf.utn.edu.ar;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import dam.isi.frsf.utn.edu.ar.modelo.Cliente;
import dam.isi.frsf.utn.edu.ar.modelo.PlazoFijo;



public class MainActivity extends AppCompatActivity {

    private PlazoFijo pf;
    private Cliente cliente;

    // widgets de la vista
    private EditText ingresarCorreoElectronico;
    private EditText ingresarCuilCuit;
    private RadioButton ingresarMoneda;
    private EditText ingresarMonto;
    private SeekBar ingresarDias;
    private SeekBar ingresarAvisarPorMail;
    private ToggleButton ingresarAcreditarEnCuenta;
    private CheckBox ingresarTerminosCondiciones;
    private Button botonIngresarHacerPlazoFijo;
    private TextView mostrarDiasSeleccionados;
    private TextView informacionFinal;
    int num1, num2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        pf = new PlazoFijo(getResources().getStringArray(R.array.tasas));
        cliente = new Cliente();

        TextView tvDiasSeleccionados;

        //EDICIÓN widgets de la vista
        ingresarCorreoElectronico = (EditText) findViewById(R.id.idIngCorreoElectronico);
        ingresarCuilCuit = (EditText) findViewById(R.id.idIngCuilCuit);
        ingresarMonto = (EditText) findViewById(R.id.idIngMontoAinvertir);
        ingresarAcreditarEnCuenta = (ToggleButton) findViewById(R.id.idAcreditarEnCuenta);
        ingresarTerminosCondiciones = (CheckBox) findViewById(R.id.idIngTerminosCondiciones);
        botonIngresarHacerPlazoFijo = (Button) findViewById(R.id.idHacerPlazoFijo);
        botonIngresarHacerPlazoFijo.setEnabled(false);
        ingresarDias = (SeekBar) findViewById(R.id.idIngDias);


        ingresarDias.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {

                // actualizar el textview de dias
                int diasSeleccionados = ingresarDias.getProgress();
                mostrarDiasSeleccionados.setText(diasSeleccionados);

                // setear los dias en el plazo fijo
                pf.setDias(diasSeleccionados);

                // actualizar el cálculo de los intereses pagados
                pf.intereses();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                // no hacer nada
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                // no hacer nada
            }

        });

        botonIngresarHacerPlazoFijo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                num1 = Integer.parseInt(ingresarMonto.getText().toString());
                num2 = ingresarDias.getProgress();

                if (ingresarCorreoElectronico.getText().toString().isEmpty() ||
                    ingresarCuilCuit.getText().toString().isEmpty() || num1 < 1  || num2 <11)
                {
                    Toast.makeText(getApplicationContext(),"Campo inválido",Toast.LENGTH_LONG).show();
                    informacionFinal.setText("Error de campo inválido");
                    informacionFinal.setTextColor(Color.parseColor("#ff0000"));
                }
                else {informacionFinal.setText(pf.toString());
                //FALTA LINEA PARA LETRA EN AZUL
                }

            };
          });
      }

}




