
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
     *
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

    public ArrayList<ProdutosDTO> listarProdutos() {
        // Cria uma nova lista para armazenar os produtos do banco
        ArrayList<ProdutosDTO> listagem = new ArrayList<>();

        // 1. Comando SQL para selecionar todos os dados
        String sql = "SELECT * FROM produtos";

        try {
            prep = conn.prepareStatement(sql);

            // 2. Executa a consulta e armazena o resultado
            resultset = prep.executeQuery();

            // 3. Percorre o resultado da consulta linha por linha
            while (resultset.next()) {
                // Cria um objeto DTO para cada produto encontrado
                ProdutosDTO produto = new ProdutosDTO();

                // Pega os dados de cada coluna da linha atual e coloca no objeto
                produto.setId(resultset.getInt("id"));
                produto.setNome(resultset.getString("nome"));
                produto.setValor(resultset.getInt("valor"));
                produto.setStatus(resultset.getString("status"));

                // Adiciona o objeto preenchido na lista
                listagem.add(produto);
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao listar produtos: " + e.getMessage());
        }

        // 4. Retorna a lista preenchida (ou vazia, se não houver dados ou ocorrer erro)
        return listagem;
    }
}
