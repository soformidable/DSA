

```
for(int i = 0; i < jobs.length ; i++){
 
int arr[] = new int[jobs[0].length]; 
for(int j = 0 ; j < jobs[0].length ; j++){

arr[j] = jobs[i][j]; } job_list.add(arr); 
} 

job_list.sort(Comparator.comparingInt((int[] a) -> a[1]).thenComparingInt(a -> -a[2]));
```

a -> -a\[2] (used to sort in reverse Order)
thenComparingInt is chained with comparingInt
this will sort the list with a\[1] ASC then a\[2] DESC
