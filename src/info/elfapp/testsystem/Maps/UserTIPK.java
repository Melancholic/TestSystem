package info.elfapp.testsystem.Maps;

import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;
import java.io.Serializable;

/**
 * Created by sosnov on 19.04.14.
 */

@Embeddable
public class UserTIPK implements Serializable {
    @ManyToOne
    private Users user;
    @ManyToOne
    private TestInstance ti;

    public UserTIPK(Users user, TestInstance ti) {
        this.user = user;
        this.ti = ti;
    }

    public UserTIPK() {
    }

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }

    public TestInstance getTi() {
        return ti;
    }

    public void setTi(TestInstance ti) {
        this.ti = ti;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserTIPK userTIPK = (UserTIPK) o;

        if (ti != null ? !ti.equals(userTIPK.ti) : userTIPK.ti != null) return false;
        if (user != null ? !user.equals(userTIPK.user) : userTIPK.user != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = user != null ? user.hashCode() : 0;
        result = 31 * result + (ti != null ? ti.hashCode() : 0);
        return result;
    }
}