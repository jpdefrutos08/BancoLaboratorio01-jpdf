package dam.isi.frsf.utn.edu.ar.modelo;

import android.util.Log;

import java.util.Arrays;

public class PlazoFijo {
    private Integer dias;
    private Double monto;
    private Boolean avisarVencimiento;
    private Boolean renovarAutomaticamente;
    private Moneda moneda;
    private String[] tasas;
    private Cliente cliente;

    @Override
    public String toString() {
        return "PlazoFijo{" +
                "dias=" + dias +
                ", monto=" + monto +
                ", avisarVencimiento=" + avisarVencimiento +
                ", renovarAutomaticamente=" + renovarAutomaticamente +
                ", moneda=" + moneda +
                ", tasas=" + Arrays.toString(tasas) +
                ", cliente=" + cliente +
                '}';
    }

    public Integer getDias() {
        return dias;
    }

    public void setDias(Integer dias) {
        this.dias = dias;
    }

    public Double getMonto() {
        return monto;
    }

    public void setMonto(Double monto) {
        this.monto = monto;
    }

    public Boolean getAvisarVencimiento() {
        return avisarVencimiento;
    }

    public void setAvisarVencimiento(Boolean avisarVencimiento) {
        this.avisarVencimiento = avisarVencimiento;
    }

    public Boolean getRenovarAutomaticamente() {
        return renovarAutomaticamente;
    }

    public void setRenovarAutomaticamente(Boolean renovarAutomaticamente) {
        this.renovarAutomaticamente = renovarAutomaticamente;
    }

    public Moneda getMoneda() {
        return moneda;
    }

    public void setMoneda(Moneda moneda) {
        this.moneda = moneda;
    }

    public String[] getTasas() {
        return tasas;
    }

    public void setTasas(String[] tasas) {
        this.tasas = tasas;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public PlazoFijo(String[] tasas) {
        Log.d("APP_01", tasas + "");
        Log.d("APP_01",
                Arrays.toString(tasas));
        this.tasas = tasas;
        this.monto = 0.0;
        this.dias = 0;
        this.avisarVencimiento = false;
        this.renovarAutomaticamente = false;
        this.moneda = Moneda.PESO;

    }

    private Double tasas() {
        if (dias < 30 && monto <= 5000) {
            return (Double.valueOf(tasas[0]));
        }
        if (dias >= 30 && monto <= 5000) {
            return Double.valueOf(tasas[1]);
        }
        if (dias < 30 && monto >= 5000 && monto <= 99999) {
            return (Double.valueOf(tasas[2]));
        }
        if (dias >= 30 && monto >= 5000 && monto <= 99999) {
            return Double.valueOf(tasas[3]);
        }
        if (dias < 30 && monto > 99999) {
            return Double.valueOf(tasas[4]);
        }
        if (dias >= 30 && monto > 99999) {
            return Double.valueOf(tasas[5]);
        }

        return 0.0;
    }

    public Double intereses() {
        Double interes;
        Double total;
        //total = monto + monto*(Math.pow(tasas[],1)); ESTA ES LA CORRECTA. USO LINEA DE ABAJO PARA NO FRENARME
        total = monto + monto*(Math.pow(0.3,1));
        interes = (total - monto)*(dias/30);

        return interes;
    }
}

