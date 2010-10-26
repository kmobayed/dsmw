

package dsmw;


import edu.nyu.cs.javagit.api.JavaGitException;
import java.io.IOException;

/**
 *
 * @author klm
 */
public class Main {

        public static void main( String[] args ) throws JavaGitException, IOException, Exception
    {
        if (args.length<2)
        {
                System.err.println("Usage: java -jar loadData <TDB folder> <ontology file> ");
                System.exit(0);
        }

        String DBdirectory = args[0] ;
        String ontoFile = "file:"+args[1];
        Git G = new Git("/Users/klm/code/project2");
        Jena J= new Jena(DBdirectory,ontoFile);
//         G.getVersion();
//        G.getLog();
        //String cs=G.gitGetFirstCS();
        //G.gitLogNoMerge(J);
        G.gitLogMerge(J);

        //System.out.println("1st CS= "+cs);
        //System.out.println(G.gitGetCSdata(cs).getChgSetID());


//        String page="http://en.wikipedia.org/w/";
//        Wikipedia W= new Wikipedia(page);
//        W.getPage();
//
//        
//
//        Site MySite = new Site("Site1");
//
//        
//
//        J.addSite(MySite);
//
        J.listSites();
//        //System.out.println("===========");
//        //J.listStatements();
        J.close();
    }

}
