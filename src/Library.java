
import java.sql.*;
import java.util.Scanner;

public class Library {
    Scanner scanner=new Scanner(System.in);
    private  String username="root";
    private String password="esragarip";
    private String db_name="librarysystem";
    private int numberofBooks=0;
    private String host="localhost";
    private int port=3306;
    private Connection con=null;
    private Statement statement=null;
    private PreparedStatement preparedStatement=null;

    public Library(){
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

    public void authorizedEnter(){
        String process="1-See the Books\n" +
                "2-Delete book record\n3-Add new book\n4-Update Book Info\n" +
                "5-See number of book\n6-Find book\n7-Quiet";
        System.out.println("Welcome to Authorized Enter");
        boolean enter=enterProcess();
        if(enter==true){
            System.out.println("************WELCOME TO LIBRARY SYSTEM ************");
          while (true){
              System.out.println(process+"\n" +
                      "Please choose an option you want to do: ");
              System.out.println("***********************************************");
                int answer=scanner.nextInt();
                scanner.nextLine();
              System.out.println("***********************************************");
                if (answer==1){
                    showBooks();
                    System.out.println("***********************************************");
                }
                else if (answer==2){
                    deleteData();
                    System.out.println("***********************************************");
                }
                else if (answer==3){
                    addBook();
                    System.out.println("***********************************************");
                }
                else if (answer==4) {
                    makeUpdate();
                    System.out.println("***********************************************");

                }
                else if (answer==5){
                    System.out.println("Number of Books: "+countofBooks());
                    System.out.println("***********************************************");
                }
                else if(answer==6){
                    findBook();
                    System.out.println("***********************************************");

                }
                else if (answer==6){
                    System.out.println("******************** logged OUT ***************************");
                    break;

                }
                else{
                    System.out.println("!!! Please enter valid input !!!");
                }
                }
        }
       else{
            System.out.println("invalid username or password Please try again");
        }
    }
         //*********FIRST METHOD**************
    public void showBooks(){
        try {
            statement=con.createStatement();
            String sorgu="Select* From books_database ";
            ResultSet rs=statement.executeQuery(sorgu);
            while(rs.next()){
                System.out.println("Book Name: "+rs.getNString("book_name")+" Book Writer "+rs.getNString("book_writer")+" Book Type: "
                        +rs.getNString("book_type")+
                        " Book Publisher "+rs.getNString("book_publisher"));
                System.out.println("--------------------------------------------------------------------");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
           //*************  END OF showBooks Method: *********************


         //**************second method**********************************

    public void findBook(){

        try {
            Scanner scanner=new Scanner(System.in);
            System.out.println("please enter book name that you want to find");
           String  wanted=scanner.nextLine();
            String sorgu="Select* From books_database where book_name like ? ";
            preparedStatement=con.prepareStatement(sorgu);
            preparedStatement.setString(1,wanted);
            ResultSet rs=preparedStatement.executeQuery();
           if (rs.next()){
                int id=rs.getInt("id");
                String name=rs.getNString("book_name");
                String writer=rs.getNString("book_writer");
                String publisher=rs.getNString("book_publisher");
               System.out.println("True Match-up\n" +
                       "*************BOOK INFORMATION****************");

                System.out.println("Book id: "+id+" book name: "+name+" book writer: "+writer+"Book Publisher: "+publisher);
            }
           else{
               System.out.println("there is no match-up");

           }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //*******************end of findBook method*****************




    //**********************countofBooks Method************************
    public int countofBooks(){

        String sorgu="Select* From books_database ";
        try {
            preparedStatement=con.prepareStatement(sorgu);
            ResultSet re=preparedStatement.executeQuery();
            while (re.next()){
                numberofBooks++;
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }

        return numberofBooks;
    }
   //****************end of method************************


   //*************************  enterprocess *****************
        public boolean enterProcess(){
            String query="Select* From admins Where username=? and password=?";
            Scanner scanner=new Scanner(System.in);
            System.out.println("username: ");
            String username=scanner.nextLine();
            System.out.println("password: ");
            String password=scanner.nextLine();
            try {
                preparedStatement=con.prepareStatement(query);
                preparedStatement.setString(1,username);
                preparedStatement.setString(2,password);
                ResultSet resultSet=preparedStatement.executeQuery();
                if(resultSet.next()==false){
                    return false;

                }
                else{


                    return true;

                }
            } catch (SQLException e) {
                System.out.println("invalid username or password please try again") ;
            }

            return false;
        }
        //*********************** end of method ***********************




    //***********addBook()*************
    public void addBook(){
        Scanner scanner=new Scanner(System.in);

        try {
            statement=con.createStatement();
            System.out.println("Please enter book name: ");
            String name_book=scanner.nextLine();
            System.out.println("Please enter name of book' writer: ");
            String name_writer=scanner.nextLine();
            System.out.println("Please enter type of book: ");
            String name_type=scanner.nextLine();
            System.out.println("Please enter name of publisher :");
            String name_publisher=scanner.nextLine();
            String Sorgu= String.format("Insert INTO books_database(book_name,book_writer,book_type,book_publisher) VALUES('%s','%s','%s','%s')",
                    name_book,name_writer,name_type,name_publisher);
            statement.executeUpdate(
                    Sorgu);


        } catch (SQLException e) {
            System.out.println("Not Comlete to Add Book Process ");
        }
    }

    //***********end of the method***********
    public void makeUpdate(){
        System.out.println("Please enter id which you want  to make change in it: ");
        int userID=scanner.nextInt();
        scanner.nextLine();

        System.out.println("old info: ");
        String sorgu="Select * From books_database where id=20";
        try {
            statement=con.createStatement();
            ResultSet rs=statement.executeQuery(sorgu);
            while(rs.next()){
                int id=rs.getInt("id");
                String name=rs.getNString("book_name");
                String writer=rs.getNString("book_writer");
                String type=rs.getNString("book_type");
                String publisher=rs.getNString("book_publisher");
                System.out.println("id: "+id+" Book name: "+name+" Book Writer: "+writer+" Book Type: "+type+" Book Publisher: "+publisher);
            }
            System.out.println(": ");
            System.out.println("1-Name of Book\n" +
                    "2-Name of Writer\n" +
                    "3-Type of Book\n" +
                    "4-Book of publisher");
            System.out.println("Please choose your process (enter like 1,2,3,4 )");
            int yanıt=scanner.nextInt();
            scanner.nextLine();
            if (yanıt==1){
                System.out.println("Please enter current name: ");
                String current_name=scanner.nextLine();
                updateBookname(current_name,userID);
            }
            if (yanıt==2) {
                System.out.println("Please enter new Writer name: ");
                String current_writer=scanner.nextLine();
                updateBookwriter(current_writer,userID);
            }
            if (yanıt==3){
                System.out.println("Please enter new type of book: ");
                String current_type=scanner.nextLine();
                updateBookType(current_type,userID);
            }
            if (yanıt==4){
                System.out.println("Please enter new book of publisher: ");
                String current_publisher=scanner.nextLine();
                updateBookPublisher(current_publisher,userID);

            }
            System.out.println("update process completed Succesfully");
        } catch (SQLException e) {
            System.out.println("not complete update process please check your inputs");
        }
    }



    public void updateBookname(String bookName,int uid){

        String sorgu="UPDATE books_database SET book_name=? WHERE id=?";
        try {
            preparedStatement=con.prepareStatement(sorgu);
            preparedStatement.setString(1,bookName);
            preparedStatement.setInt(2,uid);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {

            e.printStackTrace();
        }
    }
    public void updateBookwriter(String writer,int uid){

        String sorgu="UPDATE books_database SET book_writer=? WHERE id=?";
        try {
            preparedStatement=con.prepareStatement(sorgu);
            preparedStatement.setString(1,writer);
            preparedStatement.setInt(2,uid);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void updateBookType(String type,int uid){

        String sorgu="UPDATE books_database SET book_type=? WHERE id=?";
        try {
            preparedStatement=con.prepareStatement(sorgu);
            preparedStatement.setString(1,type);
            preparedStatement.setInt(2,uid);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void updateBookPublisher(String publisher,int uid){

        String sorgu="UPDATE books_database SET book_publisher=? WHERE id=?";
        try {
            preparedStatement=con.prepareStatement(sorgu);
            preparedStatement.setString(1,publisher);
            preparedStatement.setInt(2,uid);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

// delete function

    public void deleteData(){
        System.out.println("Please enter book name that you want to delete");
        String book_name=scanner.nextLine();
        deleteProcess(book_name);
    }

     public void deleteProcess(String name){

        boolean feedback=findBook(name);
        if (feedback==true){
            String sorgu ="Delete From books_database Where book_name=?";
            try {
                preparedStatement=con.prepareStatement(sorgu);
                preparedStatement.setString(1,name);
                preparedStatement.executeUpdate();


            } catch (SQLException e) {
                System.out.println("not completed to delete process ,Please check your inputs and try again");
            }

        }
        else{
            System.out.println("delete process not completed because book not found");
        }

     }
    public boolean findBook(String book_name){

        try {
            Scanner scanner=new Scanner(System.in);
            String sorgu="Select* From books_database where book_name like ? ";
            preparedStatement=con.prepareStatement(sorgu);
            preparedStatement.setString(1,book_name);
            ResultSet rs=preparedStatement.executeQuery();
            if (rs.next()==true){
                return  true;



            }
            else{
             return false;


            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }



}