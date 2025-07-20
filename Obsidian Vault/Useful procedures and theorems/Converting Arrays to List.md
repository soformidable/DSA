


List\<Integer> lst = Arrays.asList(nums);

In Java 8 and above, use streams

return Arrays.stream(nums).boxed().collect(Collectors.toList());

List\<Integers> lst = Arrays.stream(nums).boxed().collect(Collectors.toList());

