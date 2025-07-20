

Sorting by arr\[i]\[0]


1. Using  Custom Comparator and Arrays.sort:
```
		Arrays.sort(intervals , Comparator.comparingInt(a -> a[0]));
```
2. Using Lambda and Arrays.sort
```
//Descecnding order
			Arrays.sort(arr, (a, b) -> Integer.compare(a[0], b[0]));
			Arrays.sort(arr, (a, b) -> Integer.compare(b[0], a[0]));
			// or
			Arrays.sort(arr, Comparator.comparingInt(a -> a[0]).reversed());
```



*MULTI COLUMN SORTING*

```
Arrays.sort(arr, (a, b) -> {
    if (a[0] != b[0]) {
        return Integer.compare(a[0], b[0]);
    } else {
        return Integer.compare(a[1], b[1]);
    }
});
```