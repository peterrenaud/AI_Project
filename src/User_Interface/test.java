// build bazel //src/User_Interface:test
// bazel-bin/src/User_Interface/test
package src.User_Interface;

import src.Connect.test_DB;

public class test {
    public static void main(String[] args){
        test_Menu menu = new test_Menu();
        //test_DB.connectDB();
    }
}
