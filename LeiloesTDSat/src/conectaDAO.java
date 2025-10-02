import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author Fernando Henry
 */
public class conectaDAO {

    /**
     * Método para estabelecer a conexão com o banco de dados.
     * @return Um objeto Connection se a conexão for bem-sucedida, ou null se falhar.
     */
    public Connection connectDB() {
        // 1. A variável 'conn' é do tipo Connection, então a inicializamos com 'null'.
        Connection conn = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            // Tenta estabelecer a conexão e atribui à variável 'conn'.
            String url = "jdbc:mysql://localhost:3306/UC11";
            String user = "root";
            String password = "";
            conn = DriverManager.getConnection(url, user, password);                                           
        } catch (ClassNotFoundException ex) {
            // Erro caso a biblioteca (driver .jar) não seja encontrada.
            JOptionPane.showMessageDialog(null, "Erro: Driver MySQL não encontrado!");
            
        } catch (SQLException ex) {
            // Erro de conexão (banco de dados offline, usuário/senha errada, etc.)
            JOptionPane.showMessageDialog(null, "Erro ao conectar ao banco de dados: " + ex.getMessage());
        }
        
        // 2. Retorna o objeto de conexão. Será 'null' se algum erro ocorreu.
        return conn;
    }
}