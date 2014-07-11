package info.elfapp.testsystem.Maps;

/**
 * Created by sosnov on 25.04.14.
 */

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "user_ti_result")
@AssociationOverrides({
        @AssociationOverride(name = "pk.user",
                joinColumns = @JoinColumn(name = "user_id")),
        @AssociationOverride(name = "pk.ti",
                joinColumns = @JoinColumn(name = "ti_id")) })
public class UsersTI {
    @EmbeddedId
    private UserTIPK pk = new UserTIPK();
    @Column(name = "result", nullable = true, length = 5000)
    private String Result;
    @Temporal(TemporalType.DATE)
     @Column(name = "date", nullable = false)
    private Date Date;
    @Transient
	public TestInstance getTI() {
		return getPk().getTi();
	}

    @Transient
    public Users getUser() {
        return getPk().getUser();
    }

    public UsersTI() {
        Date=new Date();
    }

    public UsersTI(UserTIPK pk) {
        this.pk = pk;
         Date=new Date();
    }

    public UsersTI(Users usr, TestInstance ti) {
        this.pk = new UserTIPK(usr,ti);
        Date=new Date();
    }

    public UserTIPK getPk() {
        return pk;
    }

    public void setPk(UserTIPK pk) {
        this.pk = pk;
    }

    public String getResult() {
        return Result;
    }

    public void setResult(String result) {
        Result = result;
    }

    public Date getDate() {
        return Date;
    }

    public void setDate(Date date) {
        Date = date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UsersTI usersTI = (UsersTI) o;

        if (Date != null ? !Date.equals(usersTI.Date) : usersTI.Date != null) return false;
        if (Result != null ? !Result.equals(usersTI.Result) : usersTI.Result != null) return false;
        if (pk != null ? !pk.equals(usersTI.pk) : usersTI.pk != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = pk != null ? pk.hashCode() : 0;
        result = 31 * result + (Result != null ? Result.hashCode() : 0);
        result = 31 * result + (Date != null ? Date.hashCode() : 0);
        return result;
    }
}
