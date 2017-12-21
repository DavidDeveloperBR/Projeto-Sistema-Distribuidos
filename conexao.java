
package CalculadorDeExpressões.Sistema;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class conexao {
    public Connection conexao;

  public void openConexao() throws SQLException {
    
    String driver_file = "com.mysql.jdbc.Driver";
    String driver = "mysql";
    String server = "localhost";
    String database = "sist_dist";
    String port = "3306";
    String user = "root";
    String pass = "123456";

   
    try {
        Class.forName(driver_file);
    } catch(ClassNotFoundException ex){
        System.out.println("Ops!!! Algo não está certo" + ex);
    }
    
    
    String jdbcUrl = "jdbc:"+ driver + "://" + server + ":" + port + "/" + database + "?user=" + user + "&password=" + pass;
    
    conexao = DriverManager.getConnection(jdbcUrl);
    
    conexao.setAutoCommit(true);

  } 
}    
    