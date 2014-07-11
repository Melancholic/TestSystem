package info.elfapp.testsystem.Maps;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by sosnov on 19.04.14.
 */
@Entity
@Table(name = "ugroups")
public class UsersGroups {
    @Id
    @Column(name = "ugroup_id", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long UGroupID;
    @Column(name = "ugroup_name", unique = true, nullable = false, length = 45)
    private String UGroupName;
    @Column(name = "ugroup_descript", unique = false, nullable = true, length = 2000)
    private String UGroupDescript;
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "Group", cascade = CascadeType.ALL)
    private Set<Users> Usrs;

    public UsersGroups() {
        Usrs = new HashSet<Users>(0);
    }

    public UsersGroups(String UGroupName, String UGroupDescript) {
        Usrs = new HashSet<Users>(0);
        this.UGroupName = UGroupName;
        this.UGroupDescript = UGroupDescript;
    }

    public UsersGroups(String UGroupName) {
        Usrs = new HashSet<Users>(0);
        this.UGroupName = UGroupName;
    }

    public Long getUGroupID() {
        return UGroupID;
    }

    public String getUGroupName() {
        return UGroupName;
    }

    public String getUGroupDescript() {
        return UGroupDescript;
    }

    public Set<Users> getUsrs() {
        return Usrs;
    }

    public void setUGroupID(Long UGroupID) {
        this.UGroupID = UGroupID;
    }

    public void setUGroupName(String UGroupName) {
        this.UGroupName = UGroupName;
    }

    public void setUGroupDescript(String UGroupDescript) {
        this.UGroupDescript = UGroupDescript;
    }

    public void setUsrs(Set<Users> usrs) {
        Usrs = usrs;
    }
}
