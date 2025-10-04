

### Converting a HashSet\<Integer> to an int\[] Array in Java

Here are several ways to convert a `HashSet<Integer>` to a primitive `int[]` array in Java:

## Method 1: Using Java 8 Streams (most modern approach)
```java
HashSet<Integer> set = new HashSet<>(Arrays.asList(1, 2, 3, 4, 5));
int[] array = set.stream().mapToInt(Integer::intValue).toArray();
```

## Method 2: Traditional loop approach
```java
HashSet<Integer> set = new HashSet<>(Arrays.asList(1, 2, 3, 4, 5));
int[] array = new int[set.size()];
int index = 0;
for (Integer num : set) {
    array[index++] = num;
}
```

## Method 3: Using Apache Commons Lang (if available)
```java
import org.apache.commons.lang3.ArrayUtils;

HashSet<Integer> set = new HashSet<>(Arrays.asList(1, 2, 3, 4, 5));
int[] array = ArrayUtils.toPrimitive(set.toArray(new Integer[0]));
```

### Notes:
- The Streams approach (Method 1) is the most concise and modern way in Java 8+
- If you need the array to be ordered, consider using a `TreeSet` instead of `HashSet` or sort the array afterwards
- All methods handle the autounboxing from `Integer` to `int` automatically