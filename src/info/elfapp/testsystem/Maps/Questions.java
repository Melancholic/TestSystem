package info.elfapp.testsystem.Maps;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by sosnov on 19.04.14.
 */
@Entity
@Table(name = "questions")
public class Questions {
    @Id
    @Column(name = "quest_id", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long QuestId;
    @Column(name = "quest_txt", unique = false, nullable = false, length = 4500)
    private String QuestTxt;
    @Column(name = "quest_answer", unique = true, nullable = false, length = 4500)
    private String QuestAnswr;
    @ManyToOne(fetch = FetchType.EAGER/*, cascade = CascadeType.ALL*/)
    @JoinColumn(name = "qgroup_id")
    private QuestionsGroups Group;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "pk.quest", orphanRemoval = true/*,cascade=CascadeType.ALL*/)
    public Set<UsersQuests> UserQuests=new HashSet<>();

    public Questions() {
    }

    public Questions(String qName, String qAnswr) {
        this.QuestTxt = qName;
        this.QuestAnswr =qAnswr;
    }

    public Questions(String qName, String qAnswr, QuestionsGroups grp) {
        this.QuestTxt = qName;
        this.QuestAnswr =qAnswr;
        Group=grp;
    }

    public Long getQuestId() {
        return QuestId;
    }

    public void setQuestId(Long questId) {
        QuestId = questId;
    }

    public String getQuestTxt() {
        return QuestTxt;
    }

    public void setQuestTxt(String questTxt) {
        QuestTxt = questTxt;
    }

    public String getQuestAnswr() {
        return QuestAnswr;
    }

    public void setQuestAnswr(String questAnswr) {
        QuestAnswr = questAnswr;
    }

    public QuestionsGroups getGroup() {
        return Group;
    }

    public void setGroup(QuestionsGroups group) {
        Group = group;
    }

    public Set<UsersQuests> getUserQuests() {
        return UserQuests;
    }

    public void setUserQuests(Set<UsersQuests> userQuests) {
        UserQuests = userQuests;
    }
}
