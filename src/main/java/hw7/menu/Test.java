package hw7.menu;

import java.sql.SQLException;

public class Test {

    public void main(){
        try {
            ReviewMngr rm = new ReviewMngr();
            rm.logout();
        }
        catch(SQLException s){

        }


    }
}
