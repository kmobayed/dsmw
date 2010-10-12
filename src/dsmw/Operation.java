package fr.univ.nantes.dsmwawarness;

/**
 *
 * @author halaskaf
 */
public class Operation {
 private String opID;
 private String optype;
 private Patch patch;

public Operation (String ID, String type) {
    opID = ID;
    optype = type;
    }

    public String getOpID() {
        return opID;
    }

    public void setOpID(String opID) {
        this.opID = opID;
    }

    public String getOptype() {
        return optype;
    }

    public void setOptype(String optype) {
        this.optype = optype;
    }


    public Patch getPatch() {
        return patch;
    }

    public void setPatch(Patch p) {
        this.patch = p;
    }

}
