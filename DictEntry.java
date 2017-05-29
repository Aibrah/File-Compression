public class DictEntry
{
    private String key;
    private int code;
    public DictEntry next;
    
    public DictEntry(String key , int code)
    {
        this.key = key;
        this.code = code;
        next = null;
    }
    
    public String getKey()
    {
        return key;
    }
    
    public int getCode()
    {
        return code;
    }
}
