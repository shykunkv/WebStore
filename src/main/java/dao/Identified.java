package dao;

import java.io.Serializable;

public interface Identified<PK extends Serializable> {

    /**
     * Return unique object identifier
     **/
    PK getId();
}