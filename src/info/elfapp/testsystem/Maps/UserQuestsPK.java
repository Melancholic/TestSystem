package info.elfapp.testsystem.Maps;

import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;
import java.io.Serializable;

/**
 * Created by sosnov on 19.04.14.
 */

@Embeddable
public class UserQuestsPK implements Serializable {
    @ManyToOne
    private Users user;
    @ManyToOne
    private Questions quest;
    @ManyToOne
    private TestInstance ti;

    public UserQuestsPK() {
    }

    public UserQuestsPK(Users user, Questions quest, TestInstance ti) {
        this.user = user;
        this.quest = quest;
        this.ti = ti;
    }

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }

    public Questions getQuest() {
        return quest;
    }

    public void setQuest(Questions quest) {
        this.quest = quest;
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

        UserQuestsPK that = (UserQuestsPK) o;

        if (quest != null ? !quest.equals(that.quest) : that.quest != null) return false;
        if (ti != null ? !ti.equals(that.ti) : that.ti != null) return false;
        if (user != null ? !user.equals(that.user) : that.user != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = user != null ? user.hashCode() : 0;
        result = 31 * result + (quest != null ? quest.hashCode() : 0);
        result = 31 * result + (ti != null ? ti.hashCode() : 0);
        return result;
    }
}