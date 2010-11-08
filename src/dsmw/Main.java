

package dsmw;


import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
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
        
//        G.gitLog(J);


        J.listSites();
        System.out.println("===========");
//        J.listStatements();
//        System.out.println("===========");

        ChangeSet FCS=J.getFirstCS();
        System.out.print("First CS: ");
        FCS.print();

        ArrayList <ChangeSet> AL1=new ArrayList <ChangeSet>();
        AL1=J.getNextCS(FCS.getChgSetID());
        System.out.println("Second CSs?: ");
        for (ChangeSet o:AL1)
        {
            o.print();
           
        }

        String date=FCS.getDate();
        Date D;
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
        sdf1.setTimeZone(TimeZone.getTimeZone("GMT"));
        D = sdf1.parse(date);
        Calendar cal = Calendar.getInstance();
        cal.setTimeZone(TimeZone.getTimeZone("GMT"));
        cal.setTime(D);

        ArrayList <ChangeSet> AL2=new ArrayList <ChangeSet>();
        boolean more=true;
        while (more)
        {
            AL2=J.getCStillDate(cal.getTime());
            System.out.println("ChangeSets generated before " + cal.getTime().toString());
            

            // calculate divergence between AL2
            for (ChangeSet o : AL2)
            {
                o.print();
                if (J.getNextCS(o.getChgSetID()).size()==2)
                {
                    System.out.println("push");
                }
                if (o.getPreviousChgSet().size()==2)
                {
                    System.out.println("pull");
                }

                if (J.getNextCS(o.getChgSetID()).isEmpty())
                {
                    more = false;
                }
            }

            cal.add(Calendar.SECOND, 1);

        }
        
        J.close();
    }

}
