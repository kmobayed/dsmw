
package dsmw;


import com.hp.hpl.jena.query.QueryExecution;
import com.hp.hpl.jena.query.QueryExecutionFactory;
import com.hp.hpl.jena.query.QuerySolution;
import com.hp.hpl.jena.query.ResultSet;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.Property;
import com.hp.hpl.jena.rdf.model.RDFNode;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.rdf.model.Statement;
import com.hp.hpl.jena.rdf.model.StmtIterator;
import com.hp.hpl.jena.tdb.TDBFactory;
import java.util.ArrayList;
import java.util.Date;


/**
 *
 * @author klm
 */
public class Jena {
    private String DBdirectory;
    private Model data;
    private String ontoFile;
    
    public static final String rdfUri  = "http://www.w3.org/1999/02/22-rdf-syntax-ns#";
    public static final String rdfsUri = "http://www.w3.org/2000/01/rdf-schema#";
    public static final String dsmwUri = "http://www.semanticweb.org/ontologies/2009/4/MS2W.owl#";
    public static final String owlUri  = "http://www.w3.org/2002/07/owl#";
    public static final String xsdUri  = "http://www.w3.org/2001/XMLSchema#";
    public static final String foafUri = "http://xmlns.com/foaf/0.1/";
    public static final String queryPrefix ="prefix rdfs: <http://www.w3.org/2000/01/rdf-schema#> "
			+"prefix rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#> "
                        +"prefix xsd: <http://www.w3.org/2001/XMLSchema#>"
                        +"prefix owl: <http://www.w3.org/2001/XMLSchema#>"
                        +"prefix foaf: <http://xmlns.com/foaf/0.1/>"
			+"prefix MS2W: <http://www.semanticweb.org/ontologies/2009/4/MS2W.owl#> ";

    public Jena(String DB, String onto)
    {
        DBdirectory=DB;
        ontoFile=onto;
        data = TDBFactory.createModel(DBdirectory);
        //data.read(ontoFile,"RDF/XML");
    }

    public void close()
    {
      data.close(); 
    }

    public void addStatement(String s, String p, String o)
    {
      Resource subject=data.createResource(s);
      Property predicate = data.createProperty(p);
      Resource object=data.createResource(o);
      Statement st=data.createStatement(subject,predicate,object);
      data.add(st);
    }

    public void addSite(Site S)
    {
        this.addStatement(dsmwUri+S.getSiteID(), rdfUri+"type", dsmwUri+"WikiSite");
    }

    public void addDocument(Document D)
    {
        this.addStatement(dsmwUri+D.getDocID(), rdfUri+"type", dsmwUri+"Document");
        this.addStatement(dsmwUri+D.getDocID(), dsmwUri+"head", dsmwUri+D.getHead().getPatchID());
        this.addStatement(dsmwUri+D.getDocID(), dsmwUri+"onSite", dsmwUri+D.getSite().getSiteID());

    }

    public void addOperation(Operation O)
    {
        this.addStatement(dsmwUri+O.getOpID(), rdfUri+"type", dsmwUri+"Operation");
        this.addStatement(dsmwUri+O.getPatch().getPatchID(), dsmwUri+"hasOperation", dsmwUri+O.getOpID());
    }

    public void addPatch(Patch P)
    {
        this.addStatement(dsmwUri+P.getPatchID(), rdfUri+"type", dsmwUri+"Patch");
        this.addStatement(dsmwUri+P.getPatchID(), dsmwUri+"onPage", dsmwUri+P.getDoc().getDocID());
        this.addStatement(dsmwUri+P.getPatchID(), dsmwUri+"previous", dsmwUri+P.getPrevious().getPatchID());
        this.addStatement(dsmwUri+P.getChgSet().getChgSetID(), dsmwUri+"hasPatch", dsmwUri+P.getPatchID());
    }

    public void addChangeSet(ChangeSet C)
    {
        this.addStatement(dsmwUri+C.getChgSetID(), rdfUri+"type", dsmwUri+"ChangeSet");
        for(Object object : C.getPreviousChgSet())
        {
            String PCS = (String) object;
            if ((!PCS.isEmpty())) this.addStatement(dsmwUri+C.getChgSetID(), dsmwUri+"previousChangeSet", dsmwUri+PCS);
        }
        this.addStatement(dsmwUri+C.getChgSetID(), dsmwUri+"date", "\""+C.getDate()+"\""); //  ^^"+xsdUri+"dateTime");
    }

    public void addPullFeed(PullFeed PF)
    {
        this.addStatement(dsmwUri+PF.getPullFeedID(), rdfUri+"type", dsmwUri+"PullFeed");
        this.addStatement(dsmwUri+PF.getPullFeedID(), dsmwUri+"hasPullHead", dsmwUri+PF.getHeadPullFeed());
      //  this.addStatement(dsmwUri+PF.getPullFeedID(), dsmwUri+"hasRelatedPush", dsmwUri+PF.getRelatedPushFeed().getPushFeedID());
        this.addStatement(dsmwUri+PF.getSite(), dsmwUri+"hasPull", dsmwUri+PF.getPullFeedID());

    }

    public void addPushFeed(PushFeed PF)
    {
        this.addStatement(dsmwUri+PF.getPushFeedID(), rdfUri+"type", dsmwUri+"PushFeed");
        this.addStatement(dsmwUri+PF.getPushFeedID(), dsmwUri+"hasPushHead", dsmwUri+PF.getHasPushHead().getChgSetID());
        this.addStatement(dsmwUri+PF.getSite().getSiteID(), dsmwUri+"hasPush", dsmwUri+PF.getPushFeedID());
    }



    public void loadDataFile(String dataFile)
    {
		System.out.print("Loading the data ... ");
		data.read(dataFile,"N3");
		System.out.println("\tDONE");
    }

    public void listSites()
    {
        String query1;
        QueryExecution qe1;
        query1=queryPrefix +
			"SELECT DISTINCT ?site WHERE { "
			+"{?site a MS2W:WikiSite} "
			+"}";

        qe1 = QueryExecutionFactory.create(query1, data);
        for (ResultSet rs1 = qe1.execSelect() ; rs1.hasNext() ; )
        {
            QuerySolution binding1 = rs1.nextSolution();
            Resource patch1=((Resource) binding1.get("site"));
            System.out.print(patch1.getURI()+"\n");
        }
    }

    public void listStatements()
    {
        StmtIterator iter = data.listStatements();
        while (iter.hasNext()) {
            Statement stmt      = iter.nextStatement();  
            Resource  subject   = stmt.getSubject();     
            Property  predicate = stmt.getPredicate();   
            RDFNode   object    = stmt.getObject();      

            System.out.print(subject.toString());
            System.out.print(" " + predicate.toString() + " ");
            if (object instanceof Resource) {
               System.out.print(object.toString());
            } else {
                System.out.print(" \"" + object.toString() + "\"");
            }
            System.out.println(" .");
        }
    }

    public ChangeSet getFirstCS()
    {
        ChangeSet CS=null;
        String query1;
        QueryExecution qe1;
        query1=queryPrefix +
			"SELECT DISTINCT ?cs ?date WHERE { "
			+"{?cs a MS2W:ChangeSet ."
                        + "?cs MS2W:date ?date ."
                        + "} "
                        +"OPTIONAL { ?cs MS2W:previousChangeSet ?pcs . }"
                        +"FILTER(!bound(?pcs))"
			+"}";

        qe1 = QueryExecutionFactory.create(query1, data);
        for (ResultSet rs1 = qe1.execSelect() ; rs1.hasNext() ; )
        {
            QuerySolution binding1 = rs1.nextSolution();
            Resource chgSet=((Resource) binding1.get("cs"));
            Resource chgSetdate=((Resource) binding1.get("date"));
            CS=new ChangeSet(chgSet.getLocalName());
            CS.setDate(chgSetdate.toString());
        }
        return CS;
    }

    public ChangeSet getNextCS(String CS)
    {
        ChangeSet NCS=null;
        String query1;
        QueryExecution qe1;
        query1=queryPrefix +
			"SELECT DISTINCT ?cs  ?date WHERE { "
			+" ?cs a MS2W:ChangeSet ."
                        +" ?cs MS2W:date ?date ."
                        +" ?cs MS2W:previousChangeSet MS2W:"+ CS +" . "
			+" }";
        qe1 = QueryExecutionFactory.create(query1, data);
        for (ResultSet rs1 = qe1.execSelect() ; rs1.hasNext() ; )
        {
            QuerySolution binding1 = rs1.nextSolution();
            Resource chgSet=((Resource) binding1.get("cs"));
            Resource chgSetdate=((Resource) binding1.get("date"));
            
            NCS=new ChangeSet (chgSet.getLocalName());
            NCS.addPreviousChgSet(CS);
            NCS.setDate(chgSetdate.toString());

        }
        return NCS;
    }


    public ArrayList <ChangeSet> getNextCSbetween2Dates(Date D1, Date D2)
    {
        ArrayList <ChangeSet> NCS= new ArrayList<ChangeSet>();
        ChangeSet CS;
        String query1;
        String date = new java.text.SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'").format(D1);
        QueryExecution qe1;
        query1=queryPrefix +
			"SELECT DISTINCT ?cs ?date ?pcs WHERE { "
			+"{?cs a MS2W:ChangeSet . "
                        +" ?cs MS2W:previousChangeSet ?pcs . "
                        +" ?cs MS2W:date ?date . }"
                     //   +" FILTER (?date < \""+date +"\"^^xsd:dateTime )"
			+" }";
        System.out.println(query1);
        qe1 = QueryExecutionFactory.create(query1, data);
        for (ResultSet rs1 = qe1.execSelect() ; rs1.hasNext() ; )
        {
            QuerySolution binding1 = rs1.nextSolution();
            Resource chgSet=((Resource) binding1.get("cs"));
            Resource chgSetdate=((Resource) binding1.get("date"));
            Resource chgSetPrev=((Resource) binding1.get("pcs"));

            CS=new ChangeSet (chgSet.getLocalName());
            CS.addPreviousChgSet(chgSetPrev.getLocalName());
            CS.setDate(chgSetdate.toString());
            NCS.add(CS);

        }
        return NCS;
    }
    
}
