
package dsmw;

/**
 *
 * @author halaskaf
 */
public class Document {
private String docID;
private Patch head;
private Site site;

public Document(String id) {
    docID=id;
}

    public String getDocID() {
        return docID;
    }

    public void setDocID(String docID) {
        this.docID = docID;
    }

    public Patch getHead() {
        return head;
    }

    public void setHead(Patch head) {
        this.head = head;
    }


    public Site getSite() {
        return site;
    }

    public void setSite(Site s) {
        this.site = s;
    }



}
