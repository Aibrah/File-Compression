public class Dictionary implements DictionaryADT
{
    private final int TABLE_SIZE;
    private int size; 
    private DictEntry[] table;
    
    public Dictionary(int size)
    {
        this.TABLE_SIZE = size;
        table = new DictEntry[TABLE_SIZE];
        this.size = 0;
        for (int i = 0; i < TABLE_SIZE; i++)
        {
            if(i < 256)
            {
                DictEntry temp = new DictEntry(Character.toString((char)i) , i);
            try{
                insert(temp);
            } catch (DictionaryException e) {
                e.getMessage();
            }
                size++;
            }
            
        }
    }
    
    int hashCode(String key){
		int hashVal = (int)key.charAt(key.length() - 1);
		for(int i = 0; i<key.length()-1; i++){
			hashVal = (37*hashVal + (int)key.charAt(i))% 4099;
		}
		return hashVal;
	}
    
    public int insert(DictEntry pair) throws DictionaryException
    {
        if(size < 4096)
        {
            int hash = hashCode(pair.getKey());
            if (table[hash] == null)
            {
                table[hash] = pair;
                size++;
                return 0;
            }
            else 
            {
                DictEntry entry = table[hash];
                DictEntry pre = new DictEntry("x" , 0);
                while (entry != null && !entry.getKey().equals(pair.getKey()))
                {
                    pre = entry;
                    entry = entry.next;
                }
                if ( entry != null && entry.getKey().equals(pair.getKey()))
                    throw new DictionaryException("KEY ALREADY EXISTS");
                if(entry == null)
                    pre.next = pair;
                size++;
                return 1;
            }
        }
        return 0;
    }
    
    public int numElements()
    {
        return size;
    }
    
    public DictEntry find(String key)
    {
        int hash = hashCode(key);
        if(table[hash] == null)
            return null;
        else
        {
            DictEntry entry = table[hash];
            while (entry != null && !entry.getKey().equals(key))
                entry = entry.next;
            if (entry != null && entry.getKey().equals(key))
                return entry;
            else
                return null;
        }
    }
    
    public void remove (String key) throws DictionaryException
    {
        int hash = hashCode(key);
        if(table[hash] != null)
        {
           DictEntry prevEntry = null;
           DictEntry entry = table[hash];
           while (entry != null && !entry.getKey().equals(key)) 
           {
               prevEntry = entry;
               entry = entry.next;
           }
           if (entry.getKey().equals(key)) 
           {
               if (prevEntry == null)
                   table[hash] = entry.next;
               else
                   prevEntry.next = entry.next;
               size--;
           }
       }
       else
        throw new DictionaryException("KEY DOESN'T EXIST IN DICTIONARY");
    }
    
   
    
}
