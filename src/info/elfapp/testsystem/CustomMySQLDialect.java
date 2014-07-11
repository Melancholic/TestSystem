package info.elfapp.testsystem;

import org.hibernate.dialect.MySQL5InnoDBDialect;

/**
 * Created by sosnov on 19.05.14.
 */
public class CustomMySQLDialect extends MySQL5InnoDBDialect {


    public String getTableTypeString(){
        return " ENGINE=InnoDB DEFAULT CHARSET=utf8";
    }
}
