package info.elfapp.testsystem.Maps;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by sosnov on 19.04.14.
 */
@Entity
@Table(name = "qgroups")
public class QuestionsGroups {
    @Id
    @Column(name = "qgroup_id", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long QGroupID;
    @Column(name = "qgroup_name", unique = true, nullable = false, length = 45)
    private String QGroupName;
    @Column(name = "qgroup_descript", unique = false, nullable = true, length = 2000)
    private String QGroupDescript;
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "Group",orphanRemoval = true, cascade = CascadeType.ALL)
    private Set<Questions> Quests;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "pk.qgroups",orphanRemoval = true/*, cascade=CascadeType.ALL*/)
    public Set<TestsQGroups> TestQGroups=new HashSet<>();

    public QuestionsGroups() {
        Quests = new HashSet<Questions>(0);
    }

    public QuestionsGroups(String QGroupName, String QGroupDescript) {
        Quests = new HashSet<Questions>(0);
        this.QGroupName = QGroupName;
        this.QGroupDescript = QGroupDescript;
    }

    public QuestionsGroups(String QGroupName) {
        Quests = new HashSet<Questions>(0);
        this.QGroupName = QGroupName;
    }

    public Long getQGroupID() {
        return QGroupID;
    }

    public void setQGroupID(Long QGroupID) {
        this.QGroupID = QGroupID;
    }

    public String getQGroupName() {
        return QGroupName;
    }

    public void setQGroupName(String QGroupName) {
        this.QGroupName = QGroupName;
    }

    public String getQGroupDescript() {
        return QGroupDescript;
    }

    public void setQGroupDescript(String QGroupDescript) {
        this.QGroupDescript = QGroupDescript;
    }

    public Set<Questions> getQuests() {
        return Quests;
    }

    public void setQuests(Set<Questions> quests) {
        Quests = quests;
    }

    public Set<TestsQGroups> getTestQGroups() {
        return TestQGroups;
    }

    public void setTestQGroups(Set<TestsQGroups> testQGroups) {
        TestQGroups = testQGroups;
    }
}

/*
EXAMPLES!

       Stock stock = new Stock();   // one instance
    stock.setStockCode("7052");     // set his propeties
    stock.setStockName("PADINI");

    Category category1 = new Category("CONSUMER", "CONSUMER COMPANY");    // two instance
    //new category, need save to get the id first
    session.save(category1);      // save 2 instance

    StockCategory stockCategory = new StockCategory();    //  create link
    stockCategory.setStock(stock);                       //set 1 instance
    stockCategory.setCategory(category1);                 //set 2 instance
    stockCategory.setCreatedDate(new Date()); //extra column
    stockCategory.setCreatedBy("system"); //extra column

    stock.getStockCategories().add(stockCategory);      //save link

      session.save(stock);       // save 1 instance


 */
