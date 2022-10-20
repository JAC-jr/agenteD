package com.example.agenteD.Extra;

public class ForJDBC {

    /* /////FOR JDBC//////

    public static void insertValue(Integer out1) throws SQLException {


        PreparedStatement stmt = (PreparedStatement) Connection.createStatement
                ("INSERT INTO" + columnName + "(out1)");

        String sql = "";
        stmt.executeUpdate(sql);

        return false;
    }

    public Compania readValue(Compania empresa) throws SQLException {

            stmt = Connection.createStatement();
            rs = stmt.executeQuery("SELECT * FROM tabla1;");

            if (rs == null){
                return null;
            }
            else {
                while (rs.next())
                    object objeto = new ClaseEjemplo (rs.getString("atributo1"));
                cerrarDatabase();
                // Se retorna toda la coleccion
                return objeto;
            }
    }

    /**
     * Ejemplo leer datos de una tabla
     * @param objeto - una referencia a un objeto donde guardar los datos
     * @return Un objeto con los datos ya ingresados en él



    /**
     * Ejemplo eliminar de una tabla
     * @param clave - un string con una clave de lo que queremos eliminar
     * @return Un boolean si se ingresaron los datos correctamente o no

    public boolean eliminarDatosBD(String clave) throws SQLException {
        String sql;
        if (c != null) {
            // Si se creo la conexion a la BD exitosamente se continua
            // Se crea una nueva sentencia SQL
            stmt = c.createStatement();
            sql = "DELETE FROM tabla1 WHERE (atributo1 = '" + clave + "');";
            stmt.executeUpdate(sql);
            stmt.close();
            cerrarDatabase();
            return true;
        } else
            return false;
        // Si no se pudo establecer la conexion a la BD se retorna null;
    }

    /**
     * Cierra la coneccion con la BD

    public void cerrarDatabase() throws SQLException {
        if(rs != null) rs.close();
        if(stmt != null) stmt.close();
        // Se cierra conexion a la BD
        c.close();
    }
*/

}
