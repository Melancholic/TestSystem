package info.elfapp.testsystem.Maps;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by sosnov on 19.04.14.
 */
@Entity
@Table(name = "tinstance")
public class TestInstance implements Serializable {
    @Id
    @Column(name = "ti_id", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
     @Temporal(TemporalType.DATE)
    @Column(name = "ti_date", unique = false, nullable = false, length = 45)
    private Date Date;
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "test_id")
    private Tests Test;
    @Column(name = "ti_is_active", unique = false, nullable = true)
    private boolean TIActive;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "pk.ti", cascade=CascadeType.ALL)
    public Set<UsersQuests> Quests=new HashSet<>();
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "pk.ti", cascade=CascadeType.ALL)
    public Set<UsersTI> Results=new HashSet<>();


    public TestInstance() {
    }

    public TestInstance(Tests test) {
        Test = test;
        Date=new Date();
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public Date getDate() {
        return Date;
    }

    public void setDate(Date date) {
        Date = date;
    }

    public Tests getTest() {
        return Test;
    }

    public void setTest(Tests test) {
        Test = test;
    }

    public boolean isTIActive() {
        return TIActive;
    }

    public void setTIActive(boolean TIActive) {
        this.TIActive = TIActive;
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
