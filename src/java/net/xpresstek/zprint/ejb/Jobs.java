/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package net.xpresstek.zprint.ejb;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author alex
 */
@Entity
@Table(name = "Jobs")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Jobs.findAll", query = "SELECT j FROM Jobs j"),
    @NamedQuery(name = "Jobs.findById", query = "SELECT j FROM Jobs j WHERE j.id = :id"),
    @NamedQuery(name = "Jobs.findByHost", query = "SELECT j FROM Jobs j WHERE j.host = :host"),
    @NamedQuery(name = "Jobs.findByTimeOfEntry", query = "SELECT j FROM Jobs j WHERE j.timeOfEntry = :timeOfEntry")})
public class Jobs implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id", nullable = false)
    private Integer id;
    @Lob
    @Column(name = "raw_data")
    private byte[] rawData;
    @Size(max = 255)
    @Column(name = "host", length = 255)
    private String host;
    @Basic(optional = false)
    @NotNull
    @Column(name = "time_of_entry", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date timeOfEntry;

    public Jobs() {
    }

    public Jobs(Integer id) {
        this.id = id;
    }

    public Jobs(Integer id, Date timeOfEntry) {
        this.id = id;
        this.timeOfEntry = timeOfEntry;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public byte[] getRawData() {
        return rawData;
    }

    public void setRawData(byte[] rawData) {
        this.rawData = rawData;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public Date getTimeOfEntry() {
        return timeOfEntry;
    }

    public void setTimeOfEntry(Date timeOfEntry) {
        this.timeOfEntry = timeOfEntry;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Jobs)) {
            return false;
        }
        Jobs other = (Jobs) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "net.xpresstek.zprint.ejb.Jobs[ id=" + id + " ]";
    }
    
}
