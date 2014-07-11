package info.elfapp.testsystem.Maps;

import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;
import java.io.Serializable;

/**
 * Created by sosnov on 19.04.14.
 */

@Embeddable
public class TestsQGroupsID implements Serializable {
    @ManyToOne
    private Tests tests;
    @ManyToOne
    private QuestionsGroups qgroups;

    public TestsQGroupsID(Tests tests, QuestionsGroups qgroups) {
        this.tests = tests;
        this.qgroups = qgroups;
    }

    public TestsQGroupsID() {
    }


    public Tests getTests() {
        return tests;
    }

    public void setTests(Tests tests) {
        this.tests = tests;
    }

    public QuestionsGroups getQgroups() {
        return qgroups;
    }

    public void setQgroups(QuestionsGroups qgroups) {
        this.qgroups = qgroups;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TestsQGroupsID that = (TestsQGroupsID) o;

        if (qgroups != null ? !qgroups.equals(that.qgroups) : that.qgroups != null) return false;
        if (tests != null ? !tests.equals(that.tests) : that.tests != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = tests != null ? tests.hashCode() : 0;
        result = 31 * result + (qgroups != null ? qgroups.hashCode() : 0);
        return result;
    }
}