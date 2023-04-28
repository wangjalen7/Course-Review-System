import java.sql.SQLException;

public class Test {
    @org.junit.Test
    public void main(){
        try {
            ReviewMngr rm = new ReviewMngr();
            rm.logout();
        }
        catch(SQLException s){

        }


    }
}
