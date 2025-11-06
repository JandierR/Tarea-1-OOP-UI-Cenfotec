package cr.ac.ucenfotec.rojas.jandier.tl;

import cr.ac.ucenfotec.rojas.jandier.UI.UI;
import cr.ac.ucenfotec.rojas.jandier.bl.entities.Cliente;
import cr.ac.ucenfotec.rojas.jandier.bl.entities.Cuenta;
import cr.ac.ucenfotec.rojas.jandier.bl.logic.GestorCliente;
import cr.ac.ucenfotec.rojas.jandier.bl.logic.GestorCuenta;

import java.io.IOException;
import java.util.ArrayList;

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
            case 0 -> interfaz.imprimirMensajeLn("Saliendo del programa...");

        }
    }

    public void registrarCliente() throws IOException {
        interfaz.imprimirMensaje("Digite el nombre del cliente: ");
        String nombre = interfaz.leerTexto();

        interfaz.imprimirMensaje("Digite la cedula del cliente: ");
        String cedula = interfaz.leerTexto();

        String resultado = gestorCliente.registrarCliente(cedula, nombre);
        interfaz.imprimirMensajeLn(resultado);
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

        ArrayList<Cuenta> listaCuentas = gestorCliente.listarCuentasPorCliente(cedula);

        if (listaCuentas == null || listaCuentas.isEmpty()) {
            interfaz.imprimirMensajeLn("No existe cliente o cliente no tiene cuentas");
            return;
        }
        interfaz.imprimirMensajeLn(String.valueOf(listaCuentas));

    }

    public void listarClientes() {
        ArrayList<Cliente> clientes = gestorCliente.getClientes();

        if (clientes == null || clientes.isEmpty()) {
            interfaz.imprimirMensajeLn("No hay clientes para listar!");
            return;
        }

        interfaz.imprimirMensajeLn("Lista de clientes:");
        for (Cliente cliente : clientes) {
            interfaz.imprimirMensajeLn(cliente.toString());
        }
    }

    public void depositar() throws IOException {
        interfaz.imprimirMensaje("Digite el numero de cuenta a depositar: ");
        String numCuenta = interfaz.leerTexto();

        interfaz.imprimirMensaje("Digite el monto a depositar: ");
        double monto = Double.parseDouble(interfaz.leerTexto());

        String resultado = gestorCuenta.depositarEnCuenta(monto, numCuenta);
        interfaz.imprimirMensajeLn(resultado);
    }

    public void retirar() throws IOException {
        interfaz.imprimirMensaje("Digite el numero de cuenta a retirar: ");
        String numCuenta = interfaz.leerTexto();

        interfaz.imprimirMensaje("Digite el monto a retirar: ");
        double monto = Double.parseDouble(interfaz.leerTexto());

        String resultado = gestorCuenta.retirarEnCuenta(monto, numCuenta);
        interfaz.imprimirMensajeLn(resultado);

    }

    public void listarSaldosPorCliente() throws IOException {
        interfaz.imprimirMensaje("Digite la cedula del cliente: ");
        String cedula = interfaz.leerTexto();
        ArrayList<Cuenta> cuentas = gestorCliente.listarCuentasPorCliente(cedula);

        if (cuentas == null || cuentas.isEmpty()) {
            interfaz.imprimirMensajeLn("Error --> Cliente no tiene cuenta o cuenta no existe");
            return;
        }

        interfaz.imprimirMensajeLn("Saldos de cuentas de cliente #" + cedula);

        for (Cuenta cuenta : cuentas) {
            interfaz.imprimirMensajeLn("Saldo de cuenta #" + cuenta.getNumCuenta() + " --> $" + cuenta.getSaldo());
        }

    }
}
