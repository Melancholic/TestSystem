package info.elfapp.testsystem.Maps;

/**
 * Created by sosnov on 25.04.14.
 */
import javax.persistence.AssociationOverride;
import javax.persistence.AssociationOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
@Entity
@Table(name = "tests_qgroups")
@AssociationOverrides({
        @AssociationOverride(name = "pk.tests",
                joinColumns = @JoinColumn(name = "test_id")),
        @AssociationOverride(name = "pk.qgroups",
                joinColumns = @JoinColumn(name = "qgroups_id")) })
public class TestsQGroups {
    @EmbeddedId
    private TestsQGroupsID pk = new TestsQGroupsID();
    @Column(name = "size", nullable = false)
    private Integer Size;
     @Column(name = "number", nullable = false)
    private Integer Number;
    @Transient
	public Tests getTests() {
		return getPk().getTests();
	}

    @Transient
    public QuestionsGroups getQgroups() {
        return getPk().getQgroups();
    }

    public TestsQGroups() {
    }

    public TestsQGroups(TestsQGroupsID pk,int size, int num) {
        this.pk = pk;
        this.Size=size;
        this.Number=num;
    }

    public TestsQGroups(Tests tst, QuestionsGroups qgroups,int size, int num) {
        this.pk = new   TestsQGroupsID(tst,qgroups);
        this.Size=size;
        this.Number=num;
    }


    public TestsQGroupsID getPk() {
        return pk;
    }

    public void setPk(TestsQGroupsID pk) {
        this.pk = pk;
    }

    public Integer getSize() {
        return Size;
    }

    public void setSize(Integer size) {
        Size = size;
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

        TestsQGroups that = (TestsQGroups) o;

        if (pk != null ? !pk.equals(that.pk) : that.pk != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return pk != null ? pk.hashCode() : 0;
    }

}
