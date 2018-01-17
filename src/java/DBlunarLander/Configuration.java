/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DBlunarLander;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Fabian
 */
@Entity
@Table(name = "configuration")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Configuration.findAll", query = "SELECT c FROM Configuration c")
    , @NamedQuery(name = "Configuration.findById", query = "SELECT c FROM Configuration c WHERE c.id = :id")
    , @NamedQuery(name = "Configuration.findByUserId", query = "SELECT c FROM Configuration c WHERE c.userId = :userId")
    , @NamedQuery(name = "Configuration.findByDifId", query = "SELECT c FROM Configuration c WHERE c.difId = :difId")
    , @NamedQuery(name = "Configuration.findByNaveId", query = "SELECT c FROM Configuration c WHERE c.naveId = :naveId")
    , @NamedQuery(name = "Configuration.findByLunaId", query = "SELECT c FROM Configuration c WHERE c.lunaId = :lunaId")})
public class Configuration implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "user_id")
    private int userId;
    @Basic(optional = false)
    @Column(name = "dif_id")
    private int difId;
    @Basic(optional = false)
    @Column(name = "nave_id")
    private int naveId;
    @Basic(optional = false)
    @Column(name = "luna_id")
    private int lunaId;

    public Configuration() {
    }

    public Configuration(Integer id) {
        this.id = id;
    }

    public Configuration(Integer id, int userId, int difId, int naveId, int lunaId) {
        this.id = id;
        this.userId = userId;
        this.difId = difId;
        this.naveId = naveId;
        this.lunaId = lunaId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getDifId() {
        return difId;
    }

    public void setDifId(int difId) {
        this.difId = difId;
    }

    public int getNaveId() {
        return naveId;
    }

    public void setNaveId(int naveId) {
        this.naveId = naveId;
    }

    public int getLunaId() {
        return lunaId;
    }

    public void setLunaId(int lunaId) {
        this.lunaId = lunaId;
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
        if (!(object instanceof Configuration)) {
            return false;
        }
        Configuration other = (Configuration) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "DBlunarLander.Configuration[ id=" + id + " ]";
    }

}
