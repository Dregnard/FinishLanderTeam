/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DBlunarLander;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Fabian
 */
@Entity
@Table(name = "score")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Score.findAll", query = "SELECT s FROM Score s")
    , @NamedQuery(name = "Score.findById", query = "SELECT s FROM Score s WHERE s.id = :id")
    , @NamedQuery(name = "Score.findByConfId", query = "SELECT s FROM Score s WHERE s.confId = :confId")
    , @NamedQuery(name = "Score.findBySpeed", query = "SELECT s FROM Score s WHERE s.speed = :speed")
    , @NamedQuery(name = "Score.findByFuel", query = "SELECT s FROM Score s WHERE s.fuel = :fuel")
    , @NamedQuery(name = "Score.findByIniTime", query = "SELECT s FROM Score s WHERE s.iniTime = :iniTime")
    , @NamedQuery(name = "Score.findByEndTime", query = "SELECT s FROM Score s WHERE s.endTime = :endTime")})
public class Score implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "conf_id")
    private int confId;
    @Basic(optional = false)
    @Column(name = "speed")
    private float speed;
    @Basic(optional = false)
    @Column(name = "fuel")
    private float fuel;
    @Basic(optional = false)
    @Column(name = "iniTime")
    @Temporal(TemporalType.TIME)
    private Date iniTime;
    @Basic(optional = false)
    @Column(name = "endTime")
    @Temporal(TemporalType.TIME)
    private Date endTime;

    public Score() {
    }

    public Score(Integer id) {
        this.id = id;
    }

    public Score(Integer id, int confId, float speed, float fuel, Date iniTime, Date endTime) {
        this.id = id;
        this.confId = confId;
        this.speed = speed;
        this.fuel = fuel;
        this.iniTime = iniTime;
        this.endTime = endTime;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getConfId() {
        return confId;
    }

    public void setConfId(int confId) {
        this.confId = confId;
    }

    public float getSpeed() {
        return speed;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }

    public float getFuel() {
        return fuel;
    }

    public void setFuel(float fuel) {
        this.fuel = fuel;
    }

    public Date getIniTime() {
        return iniTime;
    }

    public void setIniTime(Date iniTime) {
        this.iniTime = iniTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
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
        if (!(object instanceof Score)) {
            return false;
        }
        Score other = (Score) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "DBlunarLander.Score[ id=" + id + " ]";
    }
    
}
