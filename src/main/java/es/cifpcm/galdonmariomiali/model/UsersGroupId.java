package es.cifpcm.galdonmariomiali.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import org.hibernate.Hibernate;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class UsersGroupId implements Serializable {
    private static final long serialVersionUID = -7190640577807361768L;
    @Column(name = "group_id", nullable = false)
    private Integer groupId;

    @Column(name = "user_name", nullable = false, length = 50)
    private String userName;

    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        UsersGroupId entity = (UsersGroupId) o;
        return Objects.equals(this.groupId, entity.groupId) &&
                Objects.equals(this.userName, entity.userName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(groupId, userName);
    }

}