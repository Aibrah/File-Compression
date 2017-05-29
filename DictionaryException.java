public class DictionaryException extends Exception
{
   private String message;
    
   public DictionaryException(String message)
   {
       super(message);
       this.message = message;
   }
   
   public DictionaryException(Throwable cause)
   {
       super(cause);
   }
    
   public String getMessage()
    {
        return message;
    }
}
