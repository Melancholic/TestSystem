package info.elfapp.testsystem.Maps;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by sosnov on 19.04.14.
 */
@Entity
@Table(name = "tests", uniqueConstraints = {
        @UniqueConstraint(columnNames = "test_id")})
public class Tests implements Serializable{
    @Id
    @Column(name = "test_id", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long TestID;
    @Column(name = "test_name", unique = true, nullable = false, length = 45)
    private String TestName;
    @Column(name = "test_propeties", unique = false, nullable = true, length = 4500)
    private String TestProp;
    @Column(name = "test_descript", unique = false, nullable = true, length = 2000)
    private String TestDescript;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "pk.tests", cascade=CascadeType.ALL)
    public Set<TestsQGroups> TestQGroups=new HashSet<>();
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "Test", cascade = CascadeType.ALL)
    private Set<TestInstance> Instances=new  HashSet<>();

    public Tests() {
    }

    public Tests(String tName) {
        this.TestName = tName;
    }

    public Tests(String tName, String tProp) {
        this.TestName = tName;
        this.TestProp =tProp;
    }

    public Long getTestID() {
        return TestID;
    }

    public void setTestID(Long testID) {
        TestID = testID;
    }

    public String getTestName() {
        return TestName;
    }

    public void setTestName(String testName) {
        TestName = testName;
    }

    public String getTestProp() {
        return TestProp;
    }

    public void setTestProp(String testProp) {
        TestProp = testProp;
    }

    public String getTestDescript() {
        return TestDescript;
    }

    public void setTestDescript(String testDescript) {
        TestDescript = testDescript;
    }

    public Set<TestsQGroups> getTestQGroups() {
        return TestQGroups;
    }

    public void setTestQGroups(Set<TestsQGroups> testQGroups) {
        TestQGroups = testQGroups;
    }

    public Set<TestInstance> getInstances() {
        return Instances;
    }

    public void setInstances(Set<TestInstance> instances) {
        Instances = instances;
    }
}
