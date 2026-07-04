

In Java, the **computeIfAbsent()*** method of the HashMap class is used to compute and insert a value for a specific key to a HashMap only if the key does not already have a value.

**Example 1**: Here, the computeIfAbsent() _****adds a value for a key if it is not present in the map****_ and does nothing if the key is present in the map.


```
    public static void main(String[] args) {
      
        HashMap<String, Integer> hm = new HashMap<>();

        // Adding initial values to the map
        hm.put("A", 1);
        hm.put("B", 2);

        System.out.println("Initial Map: " + hm);

            // Using computeIfAbsent()
            Integer i
            = hm.computeIfAbsent("C", key -> key.length());
        Integer j
            = hm.computeIfAbsent("A", key -> key.length());

        // the key "C" is not present in the
        //  map the value is computed
        System.out.println("Value for C: " + i);

        // the key "A" is present in the 
        // map the value is not recomputed
        System.out.println("Value for A: " + j);
        System.out.println("Updated Map: " + hm);
    }
```