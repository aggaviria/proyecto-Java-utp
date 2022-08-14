package Modelo.dao;
//import java.beans.Statement;
import java.sql.*;
import Utilidades.*;

public class ProyectoBancoDao {
    public static ResultSet consulta(String banco){
        Statement stmt=null;
        ResultSet rs = null; 
        
        try {
            var conn= JDBCUtilities.getConnection();

            String csql="SELECT ID_Proyecto as ID, Constructora, Ciudad, Proyecto.Clasificacion, Estrato, Nombre ||' '||Primer_Apellido ||' '|| Segundo_Apellido AS LIDER FROM Proyecto JOIN Tipo USING (ID_Tipo) JOIN Lider USING (ID_Lider) WHERE Banco_Vinculado = '"+banco+"' ORDER BY Fecha_Inicio DESC, Ciudad, Constructora;";
    
            stmt = (Statement) conn.createStatement();
            rs= ((java.sql.Statement) stmt).executeQuery(csql);
            

            //rs.close();
            //((ResultSet) stmt).close();
            //conn.close();
            
        } catch (Exception e) {
            System.out.println(e);
        }    
        return rs;

    }
}
