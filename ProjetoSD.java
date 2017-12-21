
package CalculadorDeExpressões.Sistema;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class ProjetoSD {

    static double res;
    static int id ;

    public ProjetoSD() {
    }
    public static Connection con = null;

    public static void Conectar() {
        System.out.println("Conectando ao banco...");
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://127.0.0.1/sist_dist", "root", "123456");
            System.out.println("Conectado.");
        } catch (ClassNotFoundException ex) {
            System.out.println("Classe não encontrada, adicione o driver nas bibliotecas.");
           Logger.getLogger(ProjetoSD.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException erro) {
            System.out.println("Erro: " + erro);
            throw new RuntimeException(erro);
        }
    }

    public String retiraExpr() {
        Conectar();
        
        String nome = "David e Tergina";
        String expr = null;
        List<String> x = new ArrayList<>();
        try {
            PreparedStatement ps = con.prepareStatement("{call retira_exp(?)}");
            ps.setString(1, nome);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
               x.add(rs.getString(2));
               id = Integer.parseInt(rs.getString(1));
               
            }
           // for (String exp : x) {
           // }
        } catch (SQLException e) {
            System.out.println("Erro: " + e);
        }
        expr = x.get(0);
        
        
        return expr;
        
    }


    public static double Calc() throws ScriptException {
        ProjetoSD obj = new ProjetoSD();
        String expr = obj.retiraExpr();
        System.out.println("Expressão Retirada: " + expr);
        
       //double a = Double.parseDouble(expr);
        ScriptEngineManager factory = new ScriptEngineManager();
                ScriptEngine engine = factory.getEngineByName("JavaScript");
                
        double result = Double.parseDouble(engine.eval(expr).toString());
                    String sql = "CALL entrega_res('  " + 1 + "  ',' " + result + "'); ";
                    
        

        
        
        return result;
    }

    public void Resultado() throws ScriptException {
        
        Double result = Calc();
        System.out.println("Resultado Devolvido: " + result);
        try {
            PreparedStatement pstm = con.prepareStatement("{call entrega_res(?,?)}");
            pstm.setInt(1, id);
            pstm.setDouble(2, result);
            pstm.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Erro: " + e);
            
             {
                try {    
                    PreparedStatement dead = con.prepareStatement("{call libera_deadlocks(?)}");
                } catch (SQLException ex) {
                    Logger.getLogger(ProjetoSD.class.getName()).log(Level.SEVERE, null, ex);
                }
        }
    }
    }

    public static void main(String[] args) throws ScriptException {
       //SD.Conectar();
       
        ProjetoSD sd = new ProjetoSD();
        int contador = 0;
        while (contador < 1) {
            sd.Resultado();
            
       }

        
        
		
           }
            
         }
    
    