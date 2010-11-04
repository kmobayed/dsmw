

package dsmw;


import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.TimeZone;

/**
 *
 * @author klm
 */
public class Main {

        public static void main( String[] args ) throws IOException, Exception
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
        
        G.gitLog(J);


        J.listSites();
        System.out.println("===========");
        J.listStatements();
        System.out.println("===========");

        ChangeSet CH=J.getFirstCS();
        System.out.print("First CS: ");
        CH.print();
        System.out.println();

        ArrayList <ChangeSet> AL1=new ArrayList <ChangeSet>();
        AL1=J.getNextCS(CH.getChgSetID());
        System.out.println("Second CSs?: ");
        for (ChangeSet o:AL1)
        {
            o.print();
            System.out.println();
        }

        AL1=J.getNextCS("CS665cd66");
        for (ChangeSet o:AL1)
        {
            System.out.println("Next to Last? ");
            o.print();
            System.out.println();
        }
        
        Date D=new Date();
        ArrayList <ChangeSet> AL2=new ArrayList <ChangeSet>();
        AL2=J.getCStillDate(D);
        System.out.println("ChangeSets generated before " + D.toString());
        for (ChangeSet o : AL2)
        {
            o.print();
            System.out.println();

        }
        
        J.close();
    }

}
