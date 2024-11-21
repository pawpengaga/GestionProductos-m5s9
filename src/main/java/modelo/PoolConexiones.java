package modelo;

// Prestar atencion a los nombre extactos
import org.apache.tomcat.dbcp.dbcp2.BasicDataSource;
import javax.sql.DataSource;

public class PoolConexiones {

  // Vamos a crear una constante perteneciente a tomcat
  private static final BasicDataSource dataSource = new BasicDataSource();

  // Vamos a llamar el dataSource en un metodo
  static {
    // Ponemos los datos regulares de la db
    dataSource.setUrl("jdbc:postgresql:m5_gestion");
    // dataSource.setUrl("jdbc:postgresql:m5_gestion?user=postgres&password=12345678");
    dataSource.setUsername("postgres");
    dataSource.setPassword("12345678");
    dataSource.setDriverClassName("org.postgresql.Driver");

    // Configurar el pool de conexiones
    dataSource.setInitialSize(5); // Las conexiones al inicializar el programa son 5
    dataSource.setMaxTotal(20); // El maximo de conexiones permitidas en la app
    dataSource.setMaxIdle(10); // El maximo de conexiones en espera, que esten dentro del pool sin usarse
    dataSource.setMinIdle(5); // El minimo de conexiones inactivas, suele ser el mismo numero que las iniciales
  }

  // Hacemos un singleton para el pool
  public static DataSource getDataSource(){
    return dataSource;
  }

}
