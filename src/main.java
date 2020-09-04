

        import java.util.Scanner;

        public class main{
            public static void main(String[] args) {
                Scanner scanner=new Scanner(System.in);

                System.out.println("*************WELCOME TO LIBRARY MANAGEMENT SYSTEM\n" +
                        "1-Student Login\n2-Authorized Login\nPlease choose an option: "
                        );
          int answer=scanner.nextInt();
          if (answer==1){
              user user=new user();
              user.userProcess();


          }
        else if(answer==2){
              Library library=new Library();
              library.authorizedEnter();

          }
        else{
              System.out.println("*****invalid option Please enter valid option*******");
          }

            }
        }