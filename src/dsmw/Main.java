

package dsmw;


import edu.nyu.cs.javagit.api.JavaGitException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

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

       G.gitLogNoMerge(J);
       G.gitLogMerge(J);


        J.listSites();
        System.out.println("===========");
        J.listStatements();
        System.out.println("===========");
        ChangeSet CH=J.getFirstCS();
        System.out.println(CH.getChgSetID()+"\n"+CH.getDate());
        System.out.println(J.getNextCS(CH.getChgSetID()).getChgSetID());
        System.out.println(J.getNextCS("CS665cd66"));

        Date D=new Date();
        ArrayList <ChangeSet> AL=new ArrayList <ChangeSet>();
        AL=J.getNextCSbetween2Dates(D, D);
        for (ChangeSet o : AL)
        {
            System.out.println(o.getChgSetID()+"\t"+o.getDate());
        }
        J.close();
    }

}
