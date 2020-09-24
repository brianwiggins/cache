import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Test {
    //First simulate first level

    //Then second level


    public static void main(String args[]){

            double HR1=0;
            double HR2=0;
            double HR=0;//cache hit ratio
            int NH1=0;
            int NH2=0;
            int NH=0;//number of hits
            int NR1=0;
            int NR2=0;
            int NR=0;//number of references

            if(Integer.parseInt(args[0])==1){
                int cache1Size = Integer.parseInt(args[1]);
                Cache<String> cache1 = new Cache<String>(cache1Size);
                File file = new File(args[2]);
                try {
                   Scanner s = new Scanner(file);
                    String target;
                    String result;
                    while(s.hasNextLine()){
                        Scanner s2 = new Scanner(s.nextLine());
                        while(s2.hasNext()){
                            target = s2.next();
                            result = cache1.getObject(target);
                            NR++;
                            if(result == null){
                                cache1.addObject(target);
                            }
                            else{
                                cache1.removeObject(target);
                                cache1.addObject(target);
                                NH++;
                            }
                        }
                    }
                }catch(FileNotFoundException e){
                    System.out.println("The file couldn't be found or doesn't exist. Check your files and try again.");
                    System.exit(1);
                }
                System.out.println("First level cache with " + cache1Size + " entries has been created");

                HR= ((double) NH) / ((double) NR);
                System.out.println("The number of global references: " + NR );
                System.out.println("The number of global cache hits: " + NH);
                HR = ((double) NH) / ((double) NR);
                System.out.println("The global hit ratio: " + HR);
                System.exit(0);


            }
            else if(Integer.parseInt(args[0])==2){
                int cache1Size = Integer.parseInt(args[1]);
                Cache<String> cache1 = new Cache<String>(cache1Size);
                int cache2Size = Integer.parseInt(args[2]);
                Cache<String> cache2 = new Cache<String>(cache2Size);
                File file = new File(args[3]);
                try {
                    Scanner s = new Scanner(file);
                    String target;
                    String result;
                    while(s.hasNextLine()){
                        Scanner s2 = new Scanner(s.nextLine());
                        while(s2.hasNext()){
                            target = s2.next();
                            result = cache1.getObject(target);
                            NR1++;
                            if(result!=null){
                                cache1.removeObject(target);
                                cache1.addObject(target);
                                cache2.removeObject(target);
                                cache2.addObject(target);
                                NH1++;
                            }else{
                                cache1.addObject(target);
                                result = cache2.getObject(target);
                                NR2++;
                                if(result!=null){
                                    cache2.removeObject(target);
                                    cache2.addObject(target);
                                    NH2++;
                                }else {
                                    cache2.addObject(target);
                                }
                            }
                        }
                    }
                    s.close();
                }catch(FileNotFoundException e){
                    System.out.println("The file couldn't be found or doesn't exist. Check your files and try again.");
                    System.exit(1);
                }

                System.out.println("First level cache with " + cache1Size + " entries has been created");

                System.out.println("Second level cache with " + cache2Size + " entries has been created");

                NR = NR1;
                NH = NH1 + NH2;

                System.out.println("The number of global references: " + NR );
                System.out.println("The number of global cache hits: " + NH);
                HR = ((double) NH) / ((double) NR);
                System.out.println("The global hit ratio: " + HR);

                System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");

                System.out.println("The number of 1st-level references: " + NR1);
                System.out.println("The number of 1st level hits: " + NH1);
                HR1 = ((double) NH1) / ((double) NR1);
                System.out.println("The 1st-level cache hit ratio: " + HR1);

                System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");

                System.out.println("The number of 2nd-level references: " + NR2);
                System.out.println("The number of 2nd-level cache hits: " + NH2);
                HR2 = ((double) NH2) / ((double) NR2);
                System.out.println("The 2nd-level cache hit ratio "+ HR2);
                System.exit(0);
            }

    }


}
