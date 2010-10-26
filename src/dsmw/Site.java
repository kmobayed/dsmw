
package dsmw;

import java.util.ArrayList;



/**
 *
 * @author halaskaf
 */
public class Site {
private String siteID;
private ArrayList<Document> docs;
private ArrayList<PushFeed> pushs;
private ArrayList<PullFeed> pulls;

public Site(String id) {
    siteID =id;
}

    public ArrayList<Document> getDocs() {
        return docs;
    }

    public void setDocs(ArrayList<Document> docs) {
        this.docs = docs;
    }

    public ArrayList<PullFeed> getPulls() {
        return pulls;
    }

    public void setPulls(ArrayList<PullFeed> pulls) {
        this.pulls = pulls;
    }

    public ArrayList<PushFeed> getPushs() {
        return pushs;
    }

    public void setPushs(ArrayList<PushFeed> pushs) {
        this.pushs = pushs;
    }

    public String getSiteID() {
        return siteID;
    }

    public void setSiteID(String siteID) {
        this.siteID = siteID;
    }

}
