package cr.ac.ucenfotec.rojas.jandier.UI;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class UI {
    private BufferedReader in;

    public UI() {
        in = new BufferedReader(new InputStreamReader(System.in));
    }

    public void imprimirMensajeLn(String mensaje) {
        System.out.println(mensaje);
    }
    public void imprimirMensaje(String mensaje) {
        System.out.print(mensaje);
    }

    public int leerOpcion() throws IOException {
        return Integer.parseInt(in.readLine());
    }

    public String leerTexto() throws IOException {
        return in.readLine();
    }

    public void imprimirMenu() {
        imprimirMensajeLn("-".repeat(50));
        imprimirMensajeLn("""
                -1. Registrar cuenta
                -2. Registrar cliente
                -3. Listar cuentas de cliente
                -4. Listar clientes
                -5. Listar saldos de cuentas cliente
                -6. Realizar deposito
                -7. Realizar retiro
                -8. Obtener operaciones
                -0. Salir""");
        imprimirMensajeLn("-".repeat(50));
    }
}
