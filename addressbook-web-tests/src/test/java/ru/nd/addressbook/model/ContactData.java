package ru.nd.addressbook.model;

import org.hibernate.annotations.Type;
import ru.nd.addressbook.tests.TestBase;

import javax.persistence.*;
import java.io.File;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "addressbook")
public class ContactData extends TestBase{
    @Id
    @Column(name = "id")
    private int id = Integer.MAX_VALUE;
    @Column(name = "firstname")
    private String name;
    @Column(name = "lastname")
    private String lastname;
    @Column(name = "home")
    @Type(type = "text")
    private String telephoneHome;
    @Column(name = "mobile")
    @Type(type = "text")
    private String telephoneMobile;
    @Column(name = "work")
    @Type(type = "text")
    private String telephoneWork;
    @Transient
    private String allPhones;
    @Column(name = "photo")
    @Type(type = "text")
    private String photo;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "address_in_groups",
            joinColumns = @JoinColumn(name = "id"), inverseJoinColumns = @JoinColumn(name = "group_id"))
    private Set<GroupData> groups = new HashSet<>();

    public String getName() {
        return name;
    }

    public String getLastname() {
        return lastname;
    }

    public String getHomePhone() {
        return telephoneHome;
    }

    public String getMobilePhone() {
        return telephoneMobile;
    }

    public String getWorkPhone() {
        return telephoneWork;
    }

    public String getAllPhones() {
        return allPhones;
    }

    public int getId() {
        return id;
    }

    public File getPhoto() {
        return new File(photo);
    }

    public Groups getGroups() {
        return new Groups(groups);
    }

    public ContactData withPhoto(File photo) {
        this.photo = photo.getPath();

        return this;
    }

    public ContactData withName(String name) {
        this.name = name;

        return this;
    }

    public ContactData withLastname(String lastname) {
        this.lastname = lastname;

        return this;
    }

    public ContactData withHomePhone(String telephoneHome) {
        this.telephoneHome = telephoneHome;

        return this;
    }

    public ContactData withMobilePhone(String telephoneMobile) {
        this.telephoneMobile = telephoneMobile;

        return this;
    }

    public ContactData withWorkPhone(String telephoneWork) {
        this.telephoneWork = telephoneWork;

        return this;
    }

    public ContactData withAllPhones(String allPhones) {
        this.allPhones = allPhones;

        return this;
    }

    public ContactData withId(int id) {
        this.id = id;

        return this;
    }

    public ContactData inGroup(GroupData group) {
        groups.add(group);

        return this;
    }

    @Override
    public String toString() {
        return "ContactData{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", lastname='" + lastname + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ContactData contactData = (ContactData) o;

        if (id != contactData.id) return false;
        return name != null ? name.equals(contactData.name) : contactData.name == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }
}
