
```
List<List<Integer> lst = new ArrayList<List<Integer>>();
lst.add(new ArrayList<Integer>());
lst.get(0).add(0,1);
lst.get(0).add(1,2);
lst.add(new ArrayList<Integer>());
lst.get(1).add(0,3);
lst.get(1).add(1,4);

Elements can also be added with
lst.add(Arrays.asList(new Integer[]{1,2,3,4}))
// Use Integer instead of int

```
