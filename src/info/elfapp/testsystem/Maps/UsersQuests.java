package info.elfapp.testsystem.Maps;

/**
 * Created by sosnov on 25.04.14.
 */

import javax.persistence.*;

@Entity
@Table(name = "user_quests")
@AssociationOverrides({
        @AssociationOverride(name = "pk.user",
                joinColumns = @JoinColumn(name = "user_id")),
        @AssociationOverride(name = "pk.quest",
                joinColumns = @JoinColumn(name = "quest_id")),
        @AssociationOverride(name = "pk.ti",
                joinColumns = @JoinColumn(name = "ti_id")) })
public class UsersQuests {
    @EmbeddedId
    private UserQuestsPK pk = new UserQuestsPK();
    @Column(name = "answer", nullable = true, length = 5000)
    private String Answer;
     @Column(name = "number", nullable = false)
    private Integer Number;

    public UsersQuests() {
    }

    public UsersQuests(UserQuestsPK pk) {
        this.pk = pk;
    }

    public UsersQuests(Users u, Questions q, TestInstance ti) {
        this.pk = new UserQuestsPK(u, q,ti);
    }


    @Transient
    public TestInstance getTI() {
		return getPk().getTi();
	}

    @Transient
    public Users getUser() {
        return getPk().getUser();
    }

    @Transient
    public Questions getQuestion() {
        return getPk().getQuest();
    }

    public UserQuestsPK getPk() {
        return pk;
    }

    public void setPk(UserQuestsPK pk) {
        this.pk = pk;
    }

    public String getAnswer() {
        return Answer;
    }

    public void setAnswer(String answer) {
        Answer = answer;
    }

    public Integer getNumber() {
        return Number;
    }

    public void setNumber(Integer number) {
        Number = number;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UsersQuests that = (UsersQuests) o;

        if (Answer != null ? !Answer.equals(that.Answer) : that.Answer != null) return false;
        if (Number != null ? !Number.equals(that.Number) : that.Number != null) return false;
        if (pk != null ? !pk.equals(that.pk) : that.pk != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = pk != null ? pk.hashCode() : 0;
        result = 31 * result + (Answer != null ? Answer.hashCode() : 0);
        result = 31 * result + (Number != null ? Number.hashCode() : 0);
        return result;
    }
}
