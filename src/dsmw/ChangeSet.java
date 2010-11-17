
package dsmw;

import java.util.ArrayList;

/**
 *
 * @author halaskaf
 */
public class ChangeSet {
    private String chgSetID;
    private ArrayList<Patch> patches;
    private String PushFeed;
    private String PullFeed;
    private ArrayList<String> previousChgSetID;
    private String diff;
    private String message;
    private String date;
    private String site;
    private boolean published;

    public ChangeSet(String id) {
        chgSetID =id;
        previousChgSetID= new ArrayList<String>();
        published = false;
    }

    public ChangeSet() {
        chgSetID ="";
    }

    public ChangeSet(String id, String pu) {
        chgSetID =id;
        PushFeed =pu;
    }

    public ArrayList<String> getPreviousChgSet() {
        return previousChgSetID;
    }

    public void addPreviousChgSet(String previousChgSet) {
        this.previousChgSetID.add(previousChgSet);
    }

    public void print()
    {
        if (this!=null){
            System.out.print("ID= "+chgSetID);
            for (String p:previousChgSetID)
            {
                System.out.print("\t previous= "+p);
            }
            System.out.print("\t date= "+date);
            System.out.println("\t published= "+published);
            System.out.println();
        }
        else
        {
            System.out.println("null");
        }
    }

    public String getChgSetID() {
        return chgSetID;
    }

    public boolean isPublished()
    {
        return published;
    }

    public void publish()
    {
        published=true;
    }
    
    public void setDiff(String df) {
        diff=df;
    }

    public String getDiff() {
        return diff;
    }

    public void setMessage(String str) {
        message=str;
    }

    public String getMessage() {
        return message;
    }

    public void setSite(String str) {
        site=str;
    }

    public String getSite() {
        return site;
    }

    public void setDate(String str) {
        date=str;
    }

    public String getDate() {
        return date;
    }

    public void setChgSetID(String chgSetID) {
        this.chgSetID = chgSetID;
    }

    public String getInPullFeed() {
        return PullFeed;
    }

    public void setInPullFeed(String inPullFeed) {
        this.PullFeed = inPullFeed;
    }

    public String getInPushFeed() {
        return PushFeed;
    }

    public void setInPushFeed(String inPushFeed) {
        this.PushFeed = inPushFeed;
    }

    public ArrayList<Patch> getPatches() {
        return patches;
    }

    public void setPatches(ArrayList<Patch> patches) {
        this.patches = patches;
    }


//public void push(ArrayList<Patch> patches, Document doc) {
//
//    // push patches and make them as published
//    for (Patch a : patches)
//     if (!a.getPublished())
//         if (a.getDoc().getDocID().equals(doc.getDocID())) {
//           this.patches.add(a);
//           a.setPublished(Boolean.TRUE);
//         }
//  this.getInPushFeed().setHasPushHead(this);
//  this.setPreviousChgSet(this.getInPushFeed().getHasPushHead());
//
//}

}
