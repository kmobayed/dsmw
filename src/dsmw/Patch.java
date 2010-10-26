

package dsmw;

import java.util.ArrayList;


/**
 *
 * @author halaskaf
 */
public class Patch {
private String patchID;
private ArrayList<Operation> operations;
private Boolean published;
private Document doc;
private Patch previous;
private ChangeSet chgSet;


public Patch(String id) {
    patchID = id;
}

public Patch(String id,Boolean p) {
    patchID= id;
    published =p;
}

public Patch(String id, Document mydoc,Boolean p) {
    patchID= id;
    published =p;
    doc = mydoc;
}

    public void setChgSet(ChangeSet C)
    {
        chgSet=C;
    }

    public ChangeSet getChgSet()
    {
        return chgSet;
    }
public Patch(String id, Document mydoc, Patch prev, Boolean p) {
    patchID= id;
    published =p;
    doc = mydoc;
    previous =prev;
}

    public Document getDoc() {
        return doc;
    }

    public void setDoc(Document doc) {
        this.doc = doc;
    }



    public Boolean getPublished() {
        return published;
    }

    public void setPublished(Boolean published) {
        this.published = published;
    }


    public ArrayList<Operation> getOperations() {
        return operations;
    }

    public void setOperations(ArrayList<Operation> operations) {
        this.operations = operations;
    }

    public String getPatchID() {
        return patchID;
    }

    public Patch getPrevious() {
        return previous;
    }

    public void setPatchID(String patchID) {
        this.patchID = patchID;
    }

   
    
}
