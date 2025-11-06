package cr.ac.ucenfotec.rojas.jandier.tl;

import cr.ac.ucenfotec.rojas.jandier.UI.UI;
import cr.ac.ucenfotec.rojas.jandier.bl.entities.Cliente;
import cr.ac.ucenfotec.rojas.jandier.bl.entities.Cuenta;
import cr.ac.ucenfotec.rojas.jandier.bl.logic.GestorCliente;
import cr.ac.ucenfotec.rojas.jandier.bl.logic.GestorCuenta;

import java.io.IOException;

public class Controller {
    GestorCliente gestorCliente = new GestorCliente();
    GestorCuenta gestorCuenta = new GestorCuenta(gestorCliente);
    UI interfaz = new UI();

    public Controller() {
    }

    public void start() throws IOException {
        int opcion;

        do {
            interfaz.imprimirMenu();
            opcion = interfaz.leerOpcion();
            procesarOpcion(opcion);

        } while (opcion != 0);
    }

    public void procesarOpcion(int opcion) throws IOException {
        switch (opcion) {
            case 1 -> registrarCuenta();
            case 2 -> registrarCliente();
            case 3 -> listarCuentasPorCliente();
            case 4 -> listarClientes();
            case 5 -> listarSaldosPorCliente();
            case 6 -> depositar();
            case 7 -> retirar();
            case 0 -> System.exit(0);

        }
    }

    public void registrarCliente() throws IOException {
        interfaz.imprimirMensaje("Digite el nombre del cliente: ");
        String nombre = interfaz.leerTexto();

        interfaz.imprimirMensaje("Digite la cedula del cliente: ");
        String cedula = interfaz.leerTexto();

        gestorCliente.registrarCliente(cedula, nombre);
    }

    public void registrarCuenta() throws IOException {
        interfaz.imprimirMensaje("Digite el nombre completo del cliente: ");
        String nombreCliente = interfaz.leerTexto();

        interfaz.imprimirMensaje("Digite el numero de cuenta: ");
        String numCuenta = interfaz.leerTexto();

        interfaz.imprimirMensaje("Digite la cedula del cliente: ");
        String cedulaCliente = interfaz.leerTexto();

        interfaz.imprimirMensaje("Digite el monto inicial de su cuenta: ");
        double montoInicial = Double.parseDouble(interfaz.leerTexto());

        String registroDeCuenta = gestorCuenta.registrarCuenta(nombreCliente, cedulaCliente, montoInicial, numCuenta);
        interfaz.imprimirMensajeLn(registroDeCuenta);
    }

    public void listarCuentasPorCliente() throws IOException {
        interfaz.imprimirMensaje("Digite la cedula del cliente: ");
        String cedula = interfaz.leerTexto();

        for (Cuenta cuenta : gestorCliente.listarCuentasPorCliente(cedula)) {
            interfaz.imprimirMensajeLn(cuenta.toString());
        }
    }

    public void listarClientes() {
        for (Cliente cliente : gestorCliente.getClientes()) {
            interfaz.imprimirMensajeLn(cliente.toString());
        }
    }

    public void depositar() throws IOException {
        interfaz.imprimirMensaje("Digite el numero de cuenta a depositar: ");
        String numCuenta = interfaz.leerTexto();

        interfaz.imprimirMensaje("Digite el monto a depositar: ");
        double monto = Double.parseDouble(interfaz.leerTexto());

        gestorCuenta.depositarEnCuenta(monto, numCuenta);
    }

    public void retirar() throws IOException {
        interfaz.imprimirMensaje("Digite el numero de cuenta a retirar: ");
        String numCuenta = interfaz.leerTexto();

        interfaz.imprimirMensaje("Digite el monto a retirar: ");
        double monto = Double.parseDouble(interfaz.leerTexto());

        gestorCuenta.retirarEnCuenta(monto, numCuenta);
    }

    public void listarSaldosPorCliente() throws IOException {
        interfaz.imprimirMensaje("Digite la cedula del cliente: ");
        String cedula = interfaz.leerTexto();

        gestorCliente.listarSaldoCuentas(cedula);
    }
}
