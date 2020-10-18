package ru.stqa.pft.addressbook.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.gson.annotations.Expose;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamOmitField;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@XStreamAlias("group")
@Entity
@Table(name = "group_list")
public class GroupData {

  //<editor-fold desc="Parameters">
  @XStreamOmitField
  @JsonIgnore
  @Id
  @Column(name = "group_id")
  private int id = Integer.MAX_VALUE;

  @Expose
  @Column(name = "group_name")
  private String name = "";

  @Expose
  @Column(name = "group_header")
  @Type(type = "text")
  private String header = "";

  @Expose
  @Column(name = "group_footer")
  @Type(type = "text")
  private String footer = "";

  @JsonIgnore
  @ManyToMany(mappedBy = "groups", fetch = FetchType.EAGER)
  private final Set<ContactData> contacts = new HashSet<>();

  @XStreamOmitField
  @JsonIgnore
  @Column(name = "deprecated", columnDefinition = "datetime")
  private final String deprecated = "0000-00-00 00:00:00";
  //</editor-fold>

  //<editor-fold desc="Getters">
  public int getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public String getHeader() {
    return header;
  }

  public String getFooter() {
    return footer;
  }

  public Contacts getContacts() {
    return new Contacts(contacts);
  }
  //</editor-fold>

  //<editor-fold desc="Setters">
  public GroupData withId(int id) {
    this.id = id;
    return this;
  }

  public GroupData withName(String name) {
    this.name = name;
    return this;
  }

  public GroupData withHeader(String header) {
    this.header = header;
    return this;
  }

  public GroupData withFooter(String footer) {
    this.footer = footer;
    return this;
  }
  //</editor-fold>

  @Override
  public String toString() {
    return "GroupData{" +
            "name='" + name + '\'' +
            ", header='" + header + '\'' +
            ", footer='" + footer + '\'' +
            '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    GroupData groupData = (GroupData) o;
    return id == groupData.id &&
            Objects.equals(name, groupData.name);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name);
  }
}