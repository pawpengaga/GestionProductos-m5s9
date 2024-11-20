import java.sql.Connection;

import modelo.ConexionDB;

public class Main {

	public static void main(String[] args) {

		Connection prueba = ConexionDB.getConnect();
		if (prueba != null) {
			System.out.println("Se conecto");
		}

	}

}
