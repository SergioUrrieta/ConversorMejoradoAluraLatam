import java.util.List;

public class Moneda {
    private String nombre;
    private String codigo;
    private double valor;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }
    @Override
    public String toString() {
        return "ListaMonedas{" +
                "Moneda='" + nombre + '\'' +
                ", date='" + valor + '\'' +
                ", rates=" + codigo +
                '}';
    }

}