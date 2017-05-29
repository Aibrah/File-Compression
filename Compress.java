import java.io.*;

public class Compress
{
    public static void main(String[] args)
    {
        String inputFile , outputFile;
        BufferedInputStream in;
        BufferedOutputStream out;
        Dictionary dict = new Dictionary(4099);
        MyOutput toFile = new MyOutput(); 
        if(args.length > 0)
        {
            inputFile = args[0];
          //  outputFile = inputFile.substring(0 , inputFile.indexOf('.')) + ".zzz";
            try{
                in = new BufferedInputStream(new FileInputStream(inputFile));
                out = new BufferedOutputStream(new FileOutputStream(args[0] + ".zzz"));
                String temp1 = "";
                char ch = (char) in.read();
                temp1 += ch;
                while(in.available() != 0)
                {
                    while((in.available() != 0) && dict.find(temp1) != null)
                        temp1 += (char) in.read();
                    if(dict.find(temp1) == null)
                    {
                        toFile.output(dict.find(temp1.substring(0 , temp1.length() - 1)).getCode() , out);
                        dict.insert(new DictEntry(temp1 , dict.numElements()));
                        temp1 = Character.toString(temp1.charAt(temp1.length() - 1)); 
                    }
                }
                toFile.output(dict.find(temp1).getCode() , out);
                toFile.flush(out);
                in.close();
                out.close();
            }catch(DictionaryException e){
                e.getMessage();}   
            catch(FileNotFoundException e){
                e.getMessage();}
            catch(IOException e){
                e.getMessage();}
        }
        else
            System.out.println("File not given for the operation");
    } 
    
}
