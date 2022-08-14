package Modelo.dao;
import java.sql.*;
import Utilidades.JDBCUtilities;

public class DeudasPorProyectoDao {
    
    public static ResultSet consulta(Double limiteInferior){
        Statement stmt=null;
        ResultSet rs = null; 
        try {
            var conn= JDBCUtilities.getConnection();
            
            String csql="SELECT p.ID_Proyecto,SUM(c.Cantidad * mc.Precio_Unidad) AS VALOR FROM Proyecto p JOIN Compra c on p.ID_Proyecto = c.ID_Proyecto JOIN MaterialConstruccion mc ON c.ID_MaterialConstruccion =mc.ID_MaterialConstruccion WHERE c.Pagado = 'No' GROUP BY p.ID_Proyecto HAVING VALOR > "+limiteInferior+" ORDER BY VALOR DESC;";

            stmt = conn.createStatement();
            rs= stmt.executeQuery(csql);

            
        } catch (Exception e) {
            
            System.out.println(e);
        }
        return rs;
    }
}
