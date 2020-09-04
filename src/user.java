import java.sql.*;
import java.util.Scanner;

public class user {

    /*
    kitap sorgula
    kayıt ol
    Kitap al
     */
    private Connection con=null;
    private PreparedStatement preparedStatement=null;
    Statement statement=null;
    Library library=new Library();
    Scanner scanner=new Scanner(System.in);
    connectDatabase connectDatabase=new connectDatabase();

            public user() {
                String url = "jdbc:mysql://" + connectDatabase.getHost() + ":" + connectDatabase.getPort() + "/" + connectDatabase.getDb_name();
                try {
                    //driveri cagrıyoruz:
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    //System.out.println("baglantı basarılı");

                } catch (ClassNotFoundException e) {
                    System.out.println("Driver bulunamadı..");
                }
                try {
                    con = DriverManager.getConnection(url, connectDatabase.getUsername(), connectDatabase.getPassword());
                } catch (SQLException e) {
                    System.out.println("veritabanına baglanılmadı...");
                }
            }



            public void userProcess(){
                System.out.println("Welcome to our Library Sytstem");
                System.out.println("1-Login\n" +
                        "2-Registry");
                System.out.println("Please choose an option to make operation in the System: ");
                int answer=scanner.nextInt();
                scanner.nextLine();
                if (answer==1){
                    boolean enter=login();
                    if (enter==true){

                    String process="1-See Options\n2-See the books in the library\n3-Find the book\n4-Borrow the book \n5-Quiet ";

                    while (true){
                        System.out.println(process);
                        System.out.println("Please Choose process you want to do");
                       int answer1=scanner.nextInt();
                       scanner.nextLine();
                        if (answer1==1){
                            System.out.println(process);
                        }
                        else if(answer1==2){
                            library.showBooks();

                        }
                        else if(answer1==3){
                            library.findBook();
                        }
                        else if(answer1==4){
                            borrowBook();

                        }
                        else if(answer1==4){


                        }
                        else if(answer1==5){
                            System.out.println("Are you sure to exit?  (yes or no)");
                            String answer_1= scanner.nextLine();
                            if (answer_1.equals("yes")){
                               break;
                            }
                            else if (answer_1.equals("no")){
                                System.out.println(process);
                            }


                        }

                    }

                }}
                else if(answer==2){
                    newRegistery();
                    String process="1-See Options\n2-See the books in the library\n3-Find the book\n4-Borrow the book \n5-Quiet ";
                    while (true){
                        System.out.println(process);
                        System.out.println("Please Choose process you want to do");
                        int answer1=scanner.nextInt();
                        scanner.nextLine();
                        if (answer1==1){
                            System.out.println("____________________________________________");
                            System.out.println(process);
                            System.out.println("_______________________________________________");
                        }
                        else if(answer1==2){
                            library.showBooks();
                            System.out.println("__________________________________________");

                        }
                        else if(answer1==3){
                            library.findBook();
                            System.out.println("__________________________________________________");
                        }
                        else if(answer1==4){
                            borrowBook();
                            System.out.println("___________________________________________________");

                        }
                        else if(answer1==4){


                        }
                        else if(answer1==5){
                            System.out.println("Are you sure to exit?  (yes or no)");
                            String answer_1= scanner.nextLine();
                            if (answer_1.equals("yes")){
                                break;
                            }
                            else if (answer_1.equals("no")){
                                System.out.println(process);
                                System.out.println("______________________________________________");
                            }


                        }

                    }

                }
                else{
                    System.out.println("Please enter valid input check your information and try again\n" +
                            "*************************************");


                }

            }

            public boolean login(){

                    String query="Select* From user_database Where userrname=? and password=?";
                    Scanner scanner=new Scanner(System.in);
                    System.out.println("username: ");
                     String  username=scanner.nextLine();
                    System.out.println("password: ");
                      String password=scanner.nextLine();
                    try {
                        preparedStatement=con.prepareStatement(query);
                        preparedStatement.setString(1,username);
                        preparedStatement.setString(2,password);
                        ResultSet resultSet=preparedStatement.executeQuery();
                        if(resultSet.next()==false){
                            System.out.println("invalid username or password Please check your information");
                            System.out.println("******************************************************************");
                            System.out.println("Please enter your information again: ");



                            return false;


                        }
                        else{
                            System.out.println("*****************  welcome to Library System: ************************\n" +
                                    "____________________________________________________________________________________ ");


                            return true;

                        }
                    } catch (SQLException e) {

                    }

                    return false;
                }
                //new user

                public void newRegistery(){

                        Scanner scanner=new Scanner(System.in);

                        try {
                            statement=con.createStatement();
                            System.out.println("Please enter choose valid username and password to register the System: ");
                            System.out.print("username : ");
                            String username=scanner.nextLine();
                            System.out.print(" password: ");
                            String password=scanner.nextLine();
                            String Sorgu= String.format("Insert INTO user_database(userrname,password) VALUES('%s','%s')",username,password);
                            statement.executeUpdate(
                                    Sorgu);
                            System.out.println("****************** Welcome to our Library ************************ ");
                        } catch (SQLException e) {

                            System.out.println("Not Comlete to Add Book Process ");
                        }
                }



                //*****************Borrow Book**************************
                public void borrowBook() {
                    System.out.println("Do you want to see  books in Library?\n" +
                            "yes or no");
                    String answer=scanner.nextLine();
                    if (answer.equals("yes")){
                        Library library=new Library();
                        library.showBooks();
                    }

                    else{
                        System.out.println("Enter the book you want to borrow from our library? ");
                        String book=scanner.nextLine();
                         boolean feedback=findBook(book);
                        if (feedback==true){
                            System.out.println("Do you want to take this book? ");
                            String answer2=scanner.nextLine();
                            if (answer2.equals("yes")){
                                System.out.println("Please enter your username and password: ");
                                System.out.print( "username: ");
                                String username=scanner.nextLine();
                                System.out.print( "password: ");
                                String password=scanner.nextLine();
                                updateBookname(book,username,password);

                            }

                        }
                        else {
                            System.out.println("");
                        }

                    }

                }
                //****end of the method****
                public boolean findBook(String bookName){

                    try {
                        Scanner scanner=new Scanner(System.in);
                       // System.out.println("please enter book name that you want to find");
                        //String  wanted=scanner.nextLine();
                        String sorgu="Select* From books_database where book_name like ? ";
                        preparedStatement=con.prepareStatement(sorgu);
                        preparedStatement.setString(1,bookName);
                        ResultSet rs=preparedStatement.executeQuery();
                        if (rs.next()==true){
                            System.out.println("***Borrow process completed successfully " +
                                    "Have a good reading***");
                            //int id=rs.getInt("id");
                            //String name=rs.getNString("book_name");
                            //String writer=rs.getNString("book_writer");
                            // String publisher=rs.getNString("book_publisher");
                            // System.out.println("True Match-up\n" +
                                  //  "*************BOOK INFORMATION****************");

                          //  System.out.println("Book id: "+id+" book name: "+name+" book writer: "+writer+"Book Publisher: "+publisher);
                            return true;
                        }
                        else{
                            System.out.println("Invalid book name Please check your input and try again :(");
                            return false
                                    ;

                        }

                    }
                    catch (SQLException e) {
                        e.printStackTrace();
                    }
                    return false;

                }




                  public void updateBookname(String bookname,String username1,String password1){

                        String sorgu="UPDATE user_database SET borrow_book=? WHERE userrname=? and password=?";
                        try {
                            preparedStatement=con.prepareStatement(sorgu);
                            preparedStatement.setString(1,bookname);
                            preparedStatement.setString(2,username1);
                            preparedStatement.setString(3,password1);
                            preparedStatement.executeUpdate();
                        } catch (SQLException e) {

                            e.printStackTrace();
                        }
                    }




                }

