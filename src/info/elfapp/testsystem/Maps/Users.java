package info.elfapp.testsystem.Maps;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by sosnov on 19.04.14.
 */
@Entity
@Table(name = "users")
public class Users {
    @Id
    @Column(name = "user_id", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idUsers;
    @Column(name = "user_name", unique = true, nullable = false, length = 45)
    private String UserName;
    @Column(name = "user_session", unique = false, nullable = false, length = 100)
    private String Session;
    @ManyToOne(fetch = FetchType.EAGER/*, cascade = CascadeType.ALL*/)
    @JoinColumn(name = "ugroup_id")
    private UsersGroups Group;
    @Column(name = "admin", unique = false, nullable = true, length = 100)
    private boolean Admin;
    @Column(name = "user_passwd", unique = false, nullable = false, length = 45)
    private String userPass;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "pk.user", cascade=CascadeType.ALL)
    public Set<UsersQuests> Quests=new HashSet<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "pk.user", cascade=CascadeType.ALL)
    public Set<UsersTI> Results=new HashSet<>();

    public Users() {
    }

    public Users(String uName, String uSession) {
        this.UserName = uName;
        this.Session = uSession;
        this.userPass ="qwerty2";
    }

    public Users(String uName, String uSession, UsersGroups grp) {
        this.UserName = uName;
        this.Session = uSession;
        this.Group = grp;
        this.userPass ="qwerty2";
    }

    public Long getIdUsers() {
        return idUsers;
    }

    public String getUserName() {
        return UserName;
    }

    public String getSession() {
        return Session;
    }

    public UsersGroups getGroup() {
        return Group;
    }

    public boolean isAdmin() {
        return Admin;
    }

    public String getUserPass() {
        return userPass;
    }

    public void setIdUsers(Long idUsers) {
        this.idUsers = idUsers;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public void setSession(String session) {
        Session = session;
    }

    public void setGroup(UsersGroups group) {
        Group = group;
    }

    public void setAdmin(boolean admin) {
        Admin = admin;
    }

    public void setUserPass(String userPass) {
        this.userPass = userPass;
    }

    public Set<UsersQuests> getQuests() {
        return Quests;
    }

    public void setQuests(Set<UsersQuests> quests) {
        Quests = quests;
    }

    public Set<UsersTI> getResults() {
        return Results;
    }

    public void setResults(Set<UsersTI> results) {
        Results = results;
    }
}
