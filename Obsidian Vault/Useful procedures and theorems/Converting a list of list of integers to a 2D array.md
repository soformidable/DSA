


List\<List\<Integer>> ---> int\[]\[]

1. Using Stream:
```
		public int[][] convertListTo2DArray(List<List<Integer>> lst) {
		    return lst.stream()
              .map(innerList -> innerList.stream()
                                        .mapToInt(Integer::intValue)
                                        .toArray())
              .toArray(int[][]::new);
		}
```

2. Normal approach:
```
	public int[][] convertListTo2DArray(List<List<Integer>> lst) {
    // Create the outer array with the same size as the list
    int[][] result = new int[lst.size()][];
    
    for (int i = 0; i < lst.size(); i++) {
        List<Integer> innerList = lst.get(i);
        // Create the inner array with the same size as the inner list
        result[i] = new int[innerList.size()];
        
        for (int j = 0; j < innerList.size(); j++) {
            // Convert Integer to int (auto-unboxing)
            result[i][j] = innerList.get(j);
        }
    }
    
    return result;
}
```