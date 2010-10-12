package fr.univ.nantes.dsmwawarness;
import fr.univ.nantes.dsmwawarness.Document;
import fr.univ.nantes.dsmwawarness.Patch;
import fr.univ.nantes.dsmwawarness.PullFeed;
import fr.univ.nantes.dsmwawarness.PushFeed;
import java.util.Collection;
/**
 *
 * @author halaskaf
 */
public class ChangeSet {
    private String chgSetID;
    private Collection<Patch> patches;
    private PushFeed inPushFeed;
    private PullFeed inPullFeed;
    private ChangeSet previousChgSet;

    public ChangeSet(String id) {
        chgSetID =id;
    }

    public ChangeSet(String id, PushFeed pu) {
        chgSetID =id;
        inPushFeed =pu;
    }

    public ChangeSet getPreviousChgSet() {
        return previousChgSet;
    }

    public void setPreviousChgSet(ChangeSet previousChgSet) {
        this.previousChgSet = previousChgSet;
    }


    public String getChgSetID() {
        return chgSetID;
    }

    public void setChgSetID(String chgSetID) {
        this.chgSetID = chgSetID;
    }

    public PullFeed getInPullFeed() {
        return inPullFeed;
    }

    public void setInPullFeed(PullFeed inPullFeed) {
        this.inPullFeed = inPullFeed;
    }

    public PushFeed getInPushFeed() {
        return inPushFeed;
    }

    public void setInPushFeed(PushFeed inPushFeed) {
        this.inPushFeed = inPushFeed;
    }

    public Collection<Patch> getPatches() {
        return patches;
    }

    public void setPatches(Collection<Patch> patches) {
        this.patches = patches;
    }
public void push(Collection<Patch> patches, Document doc) {

    // push patches and make them as published
    for (Patch a : patches)
     if (!a.getPublished())
         if (a.getDoc().getDocID().equals(doc.getDocID())) {
           this.patches.add(a);
           a.setPublished(Boolean.TRUE);
         } 
  this.getInPushFeed().setHasPushHead(this);
  this.setPreviousChgSet(this.getInPushFeed().getHasPushHead());

}

}
