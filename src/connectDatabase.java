import java.sql.*;
import java.util.Scanner;

public class connectDatabase extends Library {

  String username="root";
    private String password="esragarip";

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDb_name() {
        return db_name;
    }

    public void setDb_name(String db_name) {
        this.db_name = db_name;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    private String db_name="librarysystem";

    private String host="localhost";
    private int port=3306;
    private Connection con=null;
    private PreparedStatement preparedStatement=null;

     public void preparedCalisanlariGetir(){

         try {
             statement=con.createStatement();
             String sorgu="Select * FROM admins Where username like 'e%'";
             //Selecti kullandıgımız icin Resulseti kulllandık
              ResultSet rs=statement.executeQuery(sorgu);
              while (rs.next()){
                  System.out.println("name: "+rs.getString("username"));

              }


         } catch (SQLException e) {
             e.printStackTrace();
         }
     }

    //statement objesi sql sorgularını calıştırmamızı saglae
    private Statement statement=null;


    public connectDatabase(){
        String url="jdbc:mysql://"+host+":"+port+"/"+db_name;
        try{
            //driveri cagrıyoruz:
            Class.forName("com.mysql.cj.jdbc.Driver");
           // System.out.println("baglantı basarılı");

        } catch (ClassNotFoundException e) {
            System.out.println("Driver bulunamadı..");
        }
        try {
            con= DriverManager.getConnection(url,username,password);
        } catch (SQLException e) {
            System.out.println("veritabanına baglanılmadı...");
        }


    }


}
