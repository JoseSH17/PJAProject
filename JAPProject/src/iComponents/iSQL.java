/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iComponents;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Properties;
import javax.swing.JOptionPane;
import javax.swing.SwingWorker;

public final class iSQL {

    private String DATABASE_URL = "";
    private String USERNAME = "";
    private String PASSWORD = "";

    private Connection connection;
    private Properties properties;

    /**
     * Constructor de la clase SQL Crea una instancia a la base de datos.
     *
     * @param ip database host.
     * @param db database name
     * @param user db user
     * @param pass db password
     */
    public iSQL(String ip, String db, String user, String pass) {
        super();
        this.DATABASE_URL += "jdbc:mysql://" + ip + ":3306/" + db;
        this.USERNAME = user;
        this.PASSWORD = pass;

        this.connect();
    }

    public boolean isNumeric(Object element) {
        try {
            for (int i = 0; i < element.toString().length(); i++) {
                Integer.parseInt(String.valueOf(element.toString().charAt(i)));
            }
        } catch (NumberFormatException ex) {
            return false;
        }
        return true;
    }

    private Properties getProperties() {
        if (properties == null) {
            properties = new Properties();
            properties.setProperty("user", USERNAME);
            properties.setProperty("password", PASSWORD);
        }
        return properties;
    }

    /**
     * Crea la instancia a la base de datos, luego de cargar la libreria:
     * com.mysql.jdbc.Driver: sin esta libreria no funcionará.
     *
     * @return
     */
    public Connection connect() {
        SwingWorker sw = new SwingWorker<Boolean, Void>() {
            @Override
            protected Boolean doInBackground() throws Exception {
                if (connection == null) {
                    try {
                        Class.forName("com.mysql.jdbc.Driver");
                        connection = DriverManager.getConnection(DATABASE_URL, getProperties());
                    } catch (ClassNotFoundException | SQLException e) {
                        JOptionPane.showMessageDialog(null, e.getMessage());
                        System.exit(0);
                    }
                }
                return true;
            }
        };
        sw.execute();
        return connection;
    }

    /**
     * Retorna si una consulta (SELECT) tiene datos o bien si existe o no.
     *
     * @param rs
     * @return true si existe y-o tiene datos.
     */
    public boolean Exists(ResultSet rs) {
        if (connection == null)
            return false;
        try {
            return rs.isBeforeFirst();
        } catch (SQLException ex) {
            return false;
        }
    }

    public ArrayList<String> getColumnName(ResultSet rs) {
        try {
            ArrayList<String> cols = new ArrayList<>();
            ResultSetMetaData metaData = rs.getMetaData();

            for (int i = 1; i <= metaData.getColumnCount(); i++) {
                cols.add(metaData.getColumnLabel(i));
            }

            return cols;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return null;
        }
    }

    public ArrayList<Integer> getColumnType(ResultSet rs) {
        try {
            ArrayList<Integer> cols = new ArrayList<>();
            ResultSetMetaData metaData = rs.getMetaData();

            for (int i = 1; i <= metaData.getColumnCount(); i++) {
                cols.add(metaData.getColumnType(i));
            }

            return cols;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return null;
        }
    }

    /**
     * Recibe las sentencias que no son select: UPDATE, DELETE, INSERT, DROP ..
     *
     * @param preparedQuery
     * @param objs
     * @return
     */
    public boolean exec(String preparedQuery, ArrayList<Object> objs) {
        PreparedStatement ps;
        try {
            ps = this.connection.prepareStatement(preparedQuery);

            Iterator it = objs.iterator();
            int poc = 1;
            while (it.hasNext()) {
                Object element = it.next();
                if (this.isNumeric(element)) {
                    ps.setInt(poc, Integer.parseInt(element.toString()));
                } else {
                    ps.setString(poc, element.toString());
                }
                poc++;
            }

            ps.executeUpdate();
            ps.close();
            return true;

        } catch (SQLException | NumberFormatException ex) {
            iAlert iA = new iAlert(null, ex.getMessage());
            //JOptionPane.showMessageDialog(null, ex.getMessage());
            return false;
        }
    }

    /**
     * Sentencia ùnica para SELECT, retorna un ResultSet, que podrà ser
     * utilizado para crear tablas o manipular informaciòn.
     *
     * @param preparedQuery
     * @param objs
     * @return
     */
    public ResultSet SELECT(String preparedQuery, ArrayList<Object> objs) {
        PreparedStatement ps;
        try {
            if (connection != null)
            {
                ps = this.connection.prepareStatement(preparedQuery);

                Iterator it = objs.iterator();
                int poc = 1;
                while (it.hasNext()) {
                    Object element = it.next();
                    if (this.isNumeric(element)) {
                        ps.setInt(poc, Integer.parseInt(element.toString()));
                    } else {
                        ps.setString(poc, element.toString());
                    }
                    poc++;
                }

                ResultSet rs = ps.executeQuery();
                return rs;
            }
            else 
            {
                return null;
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
        return null;
    }

    public ResultSet SELECT(String preparedQuery) {
        PreparedStatement ps;
        try {
            ps = this.connection.prepareStatement(preparedQuery);
            ResultSet rs = ps.executeQuery();
            return rs;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
        return null;
    }
}
