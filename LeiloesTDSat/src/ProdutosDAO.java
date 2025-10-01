
import javax.swing.JOptionPane;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
/**
 *
 * @author FernandoHenry
 */

import java.sql.PreparedStatement;
import java.sql.Connection;
import javax.swing.JOptionPane;
import java.sql.ResultSet;
import java.util.ArrayList;

public class ProdutosDAO {

    // A conexão é estabelecida uma vez quando a classe é instanciada
    Connection conn;
    PreparedStatement prep;
    ResultSet resultset;

    // Construtor da classe para inicializar a conexão
    public ProdutosDAO() {
        conectaDAO dao = new conectaDAO();
        conn = dao.connectDB();
    }

    /**
     * Insere um novo produto no banco de dados.
     * @param produto O objeto ProdutosDTO com os dados a serem salvos.     
     */          

    public void cadastrarProduto(ProdutosDTO produto) {
        
        String sql = "INSERT INTO produtos (nome, valor, status) VALUES (?, ?, ?)";
        try {
            // 2. Prepara o comando com os dados do objeto recebido

            prep = conn.prepareStatement(sql);
            prep.setString(1, produto.getNome());
            prep.setInt(2, produto.getValor());
            prep.setString(3, produto.getStatus());

            // 3. Executa o comando SQL
            prep.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Produto cadastrado com sucesso! ");
            
        } catch (SQLException e) {
            // Informa sobre o erro
            JOptionPane.showMessageDialog(null, "Erro ao cadastrar produto: " + e.getMessage());

        }
    }   
}